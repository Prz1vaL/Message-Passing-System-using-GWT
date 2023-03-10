package com.stacs.cs4103.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.stacs.cs4103.shared.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {


    int addOne(int n);

    int globalClock(int clientLamportTime);

    String sendMessage(Message message);


    ArrayList<Message> receiveMessage(int processID);
}
