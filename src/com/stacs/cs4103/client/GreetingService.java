package com.stacs.cs4103.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import com.stacs.cs4103.shared.Message;

import java.util.Map;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {


    int addOne(int n);

    int globalClock(int clientLamportTime);

    String sendMessage(Message message);


    Map<String, Message> receiveMessage(Integer processID);
}
