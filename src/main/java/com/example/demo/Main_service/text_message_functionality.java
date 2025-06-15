package com.example.demo.Main_service;

import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.io.entity.StringEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


@Service
public class text_message_functionality {

    private static final String SMS_API_URL = "http://65.108.110.234:5663/api/SendSMS";

    public void sendSms(String apiId, String apiPassword, String smsType, String encoding,
                        String senderId, String phoneNumber, String textMessage) throws IOException {

        // Build JSON payload
        String jsonPayload = String.format(
                "{" +
                        "\"api_id\":\"%s\"," +
                        "\"api_password\":\"%s\"," +
                        "\"sms_type\":\"%s\"," +
                        "\"encoding\":\"%s\"," +
                        "\"sender_id\":\"%s\"," +
                        "\"phonenumber\":\"%s\"," +
                        "\"textmessage\":\"%s\"" +
                        "}",
                apiId, apiPassword, smsType, encoding, senderId, phoneNumber, textMessage
        );

        // Create HTTP client and POST request
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(SMS_API_URL);
            post.setHeader("Content-Type", "application/json");
            post.setEntity(new StringEntity(jsonPayload, ContentType.APPLICATION_JSON.withCharset(StandardCharsets.UTF_8)));

            // Send request and handle response
            try (CloseableHttpResponse response = client.execute(post)) {
                int statusCode = response.getCode();
                System.out.println("Response Code: " + statusCode);
                response.getEntity().writeTo(System.out);
            }
        }
    }
}
