package org.notima.api.fortnox.oauth2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.ArrayList;
import java.util.List;

import org.notima.api.fortnox.clients.FortnoxCredentials;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

public class CredentialsFile {

    public static final Type KEYS_TYPE = new TypeToken<List<FortnoxCredentials>>() {}.getType();
    public static final String CREDENTIALS_FILE_PROPERTY = "FortnoxCredentialsFile";
    public static final String DEFAULT_CREDENTIALS_FILE_NAME="FortnoxCredentials.json";
	
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private File file;
    private List<FortnoxCredentials> keyList;
    private String fileName;
    

    public CredentialsFile() throws IOException {
    	this(defaultFile());
    }

    /**
     * Uses the given file instead of the one named by the system property {@value #CREDENTIALS_FILE_PROPERTY}.
     * A new file is created readable and writable by the owner only (where the file system supports it).
     * 
     * @param credentialsFile	The file.
     * @throws IOException		If the file can't be created.
     */
    public CredentialsFile(File credentialsFile) throws IOException {
    	file = credentialsFile;
    	fileName = file.getPath();
        if(!file.exists()) {
        	if (file.getParentFile()!=null) {
        		Files.createDirectories(file.getParentFile().toPath());
        	}
            Files.createFile(file.toPath());
            restrictToOwner(file);
        }
    }

    private static File defaultFile() {
    	String name = System.getProperty(CREDENTIALS_FILE_PROPERTY);
    	if (name==null) {
    		name = System.getProperty("user.home") + File.separator + DEFAULT_CREDENTIALS_FILE_NAME;
    	}
    	return new File(name);
    }

    /**
     * Makes the file readable and writable by the owner only, where the file system supports
     * POSIX permissions. On other file systems (Windows) the file keeps the permissions of the
     * directory (the user profile).
     */
    public static void restrictToOwner(File f) {
    	try {
    		Files.setPosixFilePermissions(f.toPath(), PosixFilePermissions.fromString(f.isDirectory() ? "rwx------" : "rw-------"));
    	} catch (UnsupportedOperationException | IOException e) {
    		// Not a POSIX file system
    	}
    }

    public List<FortnoxCredentials> getKeyList() throws FileNotFoundException {
        JsonReader reader = new JsonReader(new FileReader(file.getPath()));
        keyList = gson.fromJson(reader, KEYS_TYPE);
        if (keyList == null) {
        	keyList = new ArrayList<FortnoxCredentials>();
        }
        return keyList;
    }
    
    public String getFilePath() {
    	return file.getAbsolutePath();
    }
    
    public void writeToFile() throws JsonIOException, IOException {
    	FileWriter fileWriter = new FileWriter(file.getPath());
        gson.toJson(keyList, fileWriter);
        fileWriter.close();
    }
    
}
