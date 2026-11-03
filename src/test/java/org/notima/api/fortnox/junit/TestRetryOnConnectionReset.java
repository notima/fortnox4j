package org.notima.api.fortnox.junit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.notima.api.fortnox.FortnoxClient3;
import org.notima.api.fortnox.FortnoxCredentialsProvider;
import org.notima.api.fortnox.clients.FortnoxCredentials;

/**
 * A local server that resets the first connections (TCP RST) while sending the reply, then answers. No Fortnox account needed.
 */
public class TestRetryOnConnectionReset {

	private ServerSocket server;
	private Thread thread;
	private final AtomicInteger connections = new AtomicInteger();
	private volatile int resets;

	@Before
	public void start() throws IOException {
		server = new ServerSocket(0, 10, InetAddress.getLoopbackAddress());
		thread = new Thread(() -> {
			while (!server.isClosed()) {
				try (Socket s = server.accept()) {
					BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream(), StandardCharsets.US_ASCII));
					String line;
					int length = 0;
					while ((line = in.readLine()) != null && !line.isEmpty()) {
						if (line.toLowerCase().startsWith("content-length:"))
							length = Integer.parseInt(line.substring(15).trim());
					}
					for (int i = 0; i < length; i++) in.read();
					byte[] body = "<ok/>".getBytes(StandardCharsets.US_ASCII);
					OutputStream out = s.getOutputStream();
					out.write(("HTTP/1.1 200 OK\r\nContent-Type: application/xml\r\nContent-Length: " + body.length
							+ "\r\nConnection: close\r\n\r\n").getBytes(StandardCharsets.US_ASCII));
					if (connections.incrementAndGet() <= resets) {
						// As in production: the reply has started, then the connection is reset while the
						// client reads the body. close() with linger 0 sends RST.
						out.write(body, 0, 2);
						out.flush();
						Thread.sleep(50);
						s.setSoLinger(true, 0);
						continue;
					}
					out.write(body);
					out.flush();
				} catch (IOException | InterruptedException e) {
					// server closed
				}
			}
		});
		thread.setDaemon(true);
		thread.start();
	}

	@After
	public void stop() throws Exception {
		server.close();
		thread.join(5000);
	}

	private FortnoxClient3 client() {
		FortnoxClient3 c = new FortnoxClient3(new FortnoxCredentialsProvider("556000-0001") {
			public FortnoxCredentials getCredentials() { return null; }
			public List<FortnoxCredentials> getAllCredentials() { return null; }
			public void setCredentials(FortnoxCredentials credentials) {}
			public void removeCredential(FortnoxCredentials removeThis) {}
			public void removeAllCredentials() {}
			public int removeCredentials(List<FortnoxCredentials> removeThese) { return 0; }
		});
		c.setBaseUrl("http://127.0.0.1:" + server.getLocalPort());
		c.setMinMillisBetweenCalls(0);
		c.setRetryDelayMillis(10);
		return c;
	}

	@Test
	public void getIsRetried() throws Exception {
		resets = 2;
		StringBuffer reply = client().getFortnox("/customers", null);
		assertTrue(reply.toString().contains("<ok/>"));
		assertEquals(3, connections.get());
	}

	@Test
	public void getGivesUpAfterMaxAttempts() throws Exception {
		resets = 5;
		try {
			client().getFortnox("/customers", null);
			fail("No exception");
		} catch (IOException e) {
			assertEquals(3, connections.get());
		}
	}

	@Test
	public void postIsNotRetriedAfterItWasSent() throws Exception {
		resets = 1;
		try {
			client().postFortnox("/invoices", new StringBuffer("<Invoice/>"));
			fail("No exception");
		} catch (IOException e) {
			assertEquals(1, connections.get());
		}
	}

	@Test
	public void postIsRetriedWhenTheConnectionCouldNotBeOpened() throws Exception {
		FortnoxClient3 c = client();
		int port = server.getLocalPort();
		server.close();
		thread.join(5000);
		long t = System.currentTimeMillis();
		try {
			c.postFortnox("/invoices", new StringBuffer("<Invoice/>"));
			fail("No exception");
		} catch (java.net.ConnectException e) {
			// 3 attempts: waits 10 + 20 ms
			assertTrue(System.currentTimeMillis() - t >= 30);
		}
		assertTrue(port > 0);
	}
}
