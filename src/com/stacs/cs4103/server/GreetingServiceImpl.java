package com.stacs.cs4103.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.stacs.cs4103.client.GreetingService;
import com.stacs.cs4103.client.Message;

import java.util.Map;

/**
 * The server-side implementation of the RPC service.
 */
public class GreetingServiceImpl extends RemoteServiceServlet implements GreetingService {

    private static Map<String, Message> messages;

    @Override
    public int addOne(int n) {
        try {
            Thread.sleep(100);
            n++;
        } catch (InterruptedException e) {
            //
        }
        return n;
    }

    public String sendMessage(String lamportTime, String messageLabel, String yourProcessId, String senderProcessId, String message) {
        messages.put(senderProcessId, new Message(lamportTime, messageLabel, yourProcessId, senderProcessId, message));
        return "Message sent successfully";
    }

}

