package com.stacs.cs4103.shared;

import com.google.gwt.user.client.rpc.IsSerializable;

public class Message implements IsSerializable {


    int lamportTime;
    int senderProcessId;



    int processID;
    String message;


    public Message(int LamportTime, int senderProcessId, int ProcessID,String message) {
        this.lamportTime = LamportTime;
        this.senderProcessId = senderProcessId;
        this.processID = ProcessID;
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

    public int getSenderProcessId() {
        return senderProcessId;
    }

    public void setSenderProcessId(int senderProcessId) {
        this.senderProcessId = senderProcessId;
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
