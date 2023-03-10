package com.stacs.cs4103.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Message implements IsSerializable {


    int lamportTime;
    int receiverProcessId;

    int processID;
    String message;


    public Message(int lamportTime, int receiverProcessId, int processID, String message) {
        this.lamportTime = lamportTime;
        this.receiverProcessId = receiverProcessId;
        this.processID = processID;
        this.message = message;

    }

    public Message() {
    }

    public int getLamportTime() {
        return lamportTime;
    }

    public void setLamportTime(int lamportTime) {
        this.lamportTime = lamportTime;
    }

    public int getReceiverProcessId() {
        return receiverProcessId;
    }

    public void setReceiverProcessId(int receiverProcessId) {
        this.receiverProcessId = receiverProcessId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getProcessID() {
        return processID;
    }

    public void setProcessID(int processID) {
        this.processID = processID;
    }


}
