package org.notima.api.fortnox.oauth2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
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
    	fileName = System.getProperty(CREDENTIALS_FILE_PROPERTY);
    	if (fileName==null) {
    		fileName = System.getProperty("user.home") + File.separator + DEFAULT_CREDENTIALS_FILE_NAME;
    	}
        file = new File(fileName);
        if(!file.exists()) {
            Files.createFile(file.toPath());
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
