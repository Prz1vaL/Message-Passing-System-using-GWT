package com.stacs.cs4103.client;

public class Message {


    String lamportTime;
    String messageLabel;
    String senderProcessId;
    String yourProcessId;
    String message;


    public Message(String LamportTime, String messageLabel, String senderProcessId, String yourProcessId, String message) {
        this.lamportTime = LamportTime;
        this.messageLabel = messageLabel;
        this.senderProcessId = senderProcessId;
        this.yourProcessId = yourProcessId;
        this.message = message;
    }


    public String getMessageLabel() {
        return messageLabel;
    }

    public void setMessageLabel(String messageLabel) {
        this.messageLabel = messageLabel;
    }

    public String getSenderProcessId() {
        return senderProcessId;
    }

    public void setSenderProcessId(String senderProcessId) {
        this.senderProcessId = senderProcessId;
    }

    public String getYourProcessId() {
        return yourProcessId;
    }

    public void setYourProcessId(String yourProcessId) {
        this.yourProcessId = yourProcessId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getLamportTime() {
        return lamportTime;
    }

    public void setLamportTime(String lamportTime) {
        this.lamportTime = lamportTime;
    }
}
