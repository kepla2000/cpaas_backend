package com.example.demo.Main_model;

public class whatsapp_sender_model {


    private String sender_number;

    private String receiver_number;


    public whatsapp_sender_model () {}
    public whatsapp_sender_model(String sender_number, String receiver_number) {
        this.sender_number = sender_number;
        this.receiver_number = receiver_number;
    }

    public void setSender_number(String sender_number) {
        this.sender_number = sender_number;
    }

    public void setReceiver_number(String receiver_number) {
        this.receiver_number = receiver_number;
    }

    public String getSender_number() {
        return sender_number;
    }

    public String getReceiver_number() {
        return receiver_number;
    }
}
