package com.stacs.cs4103.client;

public class Message {
    String messageLabel;
    String senderProcessId;
    String yourProcessId;
    String message;


public Message(String messageLabel, String senderProcessId, String yourProcessId, String message) {
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



}
