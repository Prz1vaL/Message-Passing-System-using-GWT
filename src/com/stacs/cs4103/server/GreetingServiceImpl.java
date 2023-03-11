package com.stacs.cs4103.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stacs.cs4103.client.GreetingService;
import com.stacs.cs4103.shared.Message;

import java.util.ArrayList;
import java.util.List;


/**
 * The server-side implementation of the RPC service.
 */

public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {


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

    /**
     * In this implementation, the globalClock method has been updated to be more robust.
     * It simply updates the server's Lamport time to be the maximum of the client's Lamport time and the server's current Lamport time plus one.
     * This ensures that the server's Lamport time is always increasing, even if a client sends a message with a Lamport time that is higher than the current server Lamport time.
     * @param clientLamportTime The client's Lamport time.
     * @return The server's Lamport time.
     */
    @Override
    public int globalClock(int clientLamportTime) {
        // Update the server's Lamport time
        serverLamportTime = Math.max(serverLamportTime, clientLamportTime) + 1;
        return serverLamportTime;
    }

    @Override
    public String sendMessage(Message message) {
        // Update the message's Lamport time with the server's Lamport time
        message.setLamportTime(globalClock(message.getLamportTime()));
        System.out.println("Message received from client: " + message.getMessage());
        messageList.add(message);
        return "Server has received the message.";
    }

    @Override
    public ArrayList<Message> receiveMessage(int processID) {

        ArrayList<Message> receiveMessageContainer = new ArrayList<>();
        for (Message message : messageList) {
            if (message.getReceiverProcessId() == processID) {
                receiveMessageContainer.add(message);
            }
        }
        messageList.removeAll(receiveMessageContainer);
        return receiveMessageContainer;
    }
}
