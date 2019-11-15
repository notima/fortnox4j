package org.notima.api.fortnox.entities3;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;

public class WriteOffs {

	private List<WriteOff>	writeOff;

	@XmlElement(name="WriteOff")
	public List<WriteOff> getWriteOff() {
		return writeOff;
	}

	public void setWriteOff(List<WriteOff> writeOff) {
		this.writeOff = writeOff;
	}
	
	
	
}
