package com.stacs.cs4103.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stacs.cs4103.client.GreetingService;
import com.stacs.cs4103.shared.Message;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * The server-side implementation of the RPC service.
 */

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private static Map<String, Message> messageContainer = new ConcurrentHashMap<>();

    private static List<Message> messageList = new ArrayList<>();
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
        return returnProcessID;

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
      //  messageContainer.put(String.valueOf(message.getLamportTime()), message);
        messageList.add(message);
        return "Server has received the message.";
    }

    @Override
    public ArrayList<Message> receiveMessage(int processID) {

        ArrayList<Message> receiveMessageContainer = new ArrayList<>();
       // Iterator<Message> iterator = messageList.iterator();
        for (Message message : messageList) {
            if (message.getReceiverProcessId() == processID) {
                receiveMessageContainer.add(message);
            }
        }
        messageList.removeAll(receiveMessageContainer);
//        while (iterator.hasNext()) {
//            Message message = iterator.next();
//            if (message.getSenderProcessId() == processID) {
//                receiveMessageContainer.add(message);
//                iterator.remove();
//            }
//        }
        return receiveMessageContainer;
    }
}
