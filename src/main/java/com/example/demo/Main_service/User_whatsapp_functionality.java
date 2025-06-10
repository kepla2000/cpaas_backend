package com.example.demo.Main_service;

import org.springframework.stereotype.Service;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Base64;

@Service
public class User_whatsapp_functionality {

    public String template_value;

    public void whatsapp_shoot(){

        return;
    }


    public void template_create(String value_value){

        template_value = value_value;

        return;
    }

    public void ai_create(){


    }

    public void whatsapp_send_function(String sender, String receiver) {
        try {
            String accountSid = "US738b05f951ff4a7565c0358229bff7da";
            String authToken = "testkey123";

            String to = "whatsapp:+233552654024";
            String from = "whatsapp:+233552654029";
            String body = template_value;

            String data = "To=" + java.net.URLEncoder.encode(to, "UTF-8")
                    + "&From=" + java.net.URLEncoder.encode(from, "UTF-8")
                    + "&Body=" + java.net.URLEncoder.encode(body, "UTF-8");

            URL url = new URL("https://api.twilio.com/2010-04-01/Accounts/" + accountSid + "/Messages.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            String basicAuth = Base64.getEncoder().encodeToString((accountSid + ":" + authToken).getBytes());
            conn.setRequestProperty("Authorization", "Basic " + basicAuth);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            try (OutputStream os = conn.getOutputStream()) {
                os.write(data.getBytes());
                os.flush();
            }

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);

            conn.getInputStream().transferTo(System.out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
