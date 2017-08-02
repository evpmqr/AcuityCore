package com.acuity.db.arango_monitor;

import com.acuity.db.util.DBAccess;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Zachary Herridge on 8/2/2017.
 */
public class ArangoMonitor implements Runnable{

    private static final Gson gson = new Gson();

    private String server;
    private String db;
    private String username, password;

    private boolean running = true;

    private long lastTick = -1;
    private String jwt = null;

    private long pollTimeMS = 300;

    public ArangoMonitor(String server, String db, String username, String password) {
        this.server = server;
        this.db = db;
        this.username = username;
        this.password = password;
    }

    @Override
    public void run() {
        while (running){
            try {
                if (jwt == null && username != null && password != null){
                    System.out.println("a");
                    authenticate();
                }
                else if (lastTick == -1){
                    System.out.println("ri");
                    requestInitialState();
                }
                else {
                    follow();
                }

                try {
                    Thread.sleep(pollTimeMS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void follow() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(server + "/_db/" + db + "/_api/replication/logger-follow?from=" + lastTick).openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "bearer " + jwt);

        int responseCode = connection.getResponseCode();
        if (responseCode != 204 && responseCode != 200) throw new IllegalStateException("Response code = " + responseCode + ".");

        String response = readInput(connection);

        String headerField = connection.getHeaderField("x-arango-replication-lastincluded");
        if (headerField == null) return;

        long lastTick = Long.parseLong(headerField);
        if (lastTick == 0) return;
        this.lastTick = lastTick;

        for (String s : response.substring(1, response.length() - 1).split("}\\{")) {
            System.out.println(s);
        }
    }

    @SuppressWarnings("unchecked")
    private void requestInitialState() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(server + "/_db/" + db + "/_api/replication/logger-state").openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "bearer " + jwt);

        int responseCode = connection.getResponseCode();
        if (responseCode != 200) throw new IllegalStateException("Response code = " + responseCode + ".");

        String response = readInput(connection);

        HashMap hashMap = gson.fromJson(response, HashMap.class);
        lastTick = Long.parseLong((String) ((Map<String, Object>) hashMap.get("state")).get("lastLogTick"));
    }

    private void authenticate() throws IOException {
        HttpURLConnection connection = (HttpURLConnection) new URL(server + "/_open/auth").openConnection();

        connection.setDoOutput(true);
        connection.setRequestProperty("Accept-Charset", "UTF-8");
        connection.setRequestProperty("Content-Type", "application/json;charset=" + "UTF-8");

        try (OutputStream output = connection.getOutputStream()) {
            output.write(("{\"username\": \"" + username + "\", \"password\": \"" + password + "\"}").getBytes("UTF-8"));
        }

        String response = readInput(connection);
        HashMap hashMap = gson.fromJson(response, HashMap.class);
        jwt = (String) hashMap.get("jwt");
    }

    private String readInput(HttpURLConnection connection) throws IOException {
        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))){
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
        }
        return response.toString();
    }

    public static void main(String[] args) {
        ArangoMonitor arangoMonitor = new ArangoMonitor("http://127.0.0.1:8529", "_system", DBAccess.getUsername(), DBAccess.getPassword());
        new Thread(arangoMonitor).start();
    }
}
