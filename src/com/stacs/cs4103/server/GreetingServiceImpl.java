package com.stacs.cs4103.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stacs.cs4103.client.GreetingService;
import com.stacs.cs4103.shared.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * The server-side implementation of the RPC service.
 */

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private static Map<String, Message> messageContainer = new HashMap<>();


    private static int serverLamportTime = 0;

    private static int serverProcessIDCount = 0;


    @Override
    public int addOne(int n) {
        int returnProcessID = 0;
        if (n == serverProcessIDCount) {
            serverProcessIDCount++;
            returnProcessID = serverProcessIDCount;
        } else if (n > serverProcessIDCount) {
            serverProcessIDCount = n;
            serverProcessIDCount++;
            returnProcessID = serverProcessIDCount;
        } else if (n < serverProcessIDCount) {
            serverProcessIDCount++;
            returnProcessID = serverProcessIDCount;
        }
        return serverProcessIDCount;

    }

    @Override
    public int globalClock(int clientLamportTime) {
        int returnClockTime = 0;
        if (clientLamportTime == serverLamportTime) {
            serverLamportTime++;
            returnClockTime = serverLamportTime;
            // Send the message
        } else if (clientLamportTime > serverLamportTime) {
            serverLamportTime = clientLamportTime;
            serverLamportTime++;
            returnClockTime = serverLamportTime;
            // Send the message
        } else if (clientLamportTime < serverLamportTime) {
            serverLamportTime++;
            returnClockTime = serverLamportTime;
            // Send the message
        }
        return returnClockTime;
    }

    @Override
    public String sendMessage(Message message) {
        System.out.println("Message received from client: " + message.getMessage());
        messageContainer.put(String.valueOf(message.getLamportTime()), message);
        return "Server has received";

    }

    @Override
    public Map<String, Message> receiveMessage(Integer processID) {
        Map<String, Message> receiveMessageContainer = new HashMap<>();
        for (Map.Entry<String, Message> entry : messageContainer.entrySet()) {
            if (entry.getValue().getSenderProcessId() == processID) {
                receiveMessageContainer.put(entry.getKey(), entry.getValue());
                messageContainer.remove(entry.getKey());
            }
        }
        return receiveMessageContainer;
    }
}
