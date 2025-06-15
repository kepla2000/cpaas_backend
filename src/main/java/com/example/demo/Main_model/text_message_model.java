package com.example.demo.Main_model;




public class text_message_model {

    private String apiId;
    private String apiPassword;
    private String smsType;
    private String encoding;
    private String senderId;
    private String phoneNumber;
    private String textMessage;

    public text_message_model(String apiId, String apiPassword, String smsType, String encoding, String senderId, String phoneNumber, String textMessage) {
        this.apiId = apiId;
        this.apiPassword = apiPassword;
        this.smsType = smsType;
        this.encoding = encoding;
        this.senderId = senderId;
        this.phoneNumber = phoneNumber;
        this.textMessage = textMessage;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiPassword() {
        return apiPassword;
    }

    public void setApiPassword(String apiPassword) {
        this.apiPassword = apiPassword;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getEncoding() {
        return encoding;
    }

    public void setEncoding(String encoding) {
        this.encoding = encoding;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTextMessage() {
        return textMessage;
    }

    public void setTextMessage(String textMessage) {
        this.textMessage = textMessage;
    }
}
