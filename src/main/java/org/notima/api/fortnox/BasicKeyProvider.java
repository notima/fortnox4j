package org.notima.api.fortnox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.stream.JsonReader;

import org.notima.api.fortnox.clients.FortnoxApiKey;

public class BasicKeyProvider extends FortnoxKeyProvider {

    public BasicKeyProvider(String orgNo) {
        super(orgNo);
    }

    private  String keyDir = "keys";

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public FortnoxApiKey getKey() {
        File keyFile = new File(keyDir + File.separator + orgNo + ".json");
        try {
            JsonReader jsonReader = new JsonReader(new FileReader(keyFile));
            return gson.fromJson(jsonReader, FortnoxApiKey.class);
        } catch (FileNotFoundException e) {
            return null;
        }
    }

    @Override
    public void setKey(FortnoxApiKey key) throws JsonIOException, IOException {
        File dir = new File(keyDir);
        File keyFile = new File(dir + File.separator + orgNo + ".json");
        if(!dir.exists()) {
            dir.mkdirs();
        }
        FileWriter writer = new FileWriter(keyFile);
        gson.toJson(key, writer);
        writer.flush();
        writer.close();
    }

    public void setKeyDir(String keyDir) {
        this.keyDir = keyDir;
    }
    
}
