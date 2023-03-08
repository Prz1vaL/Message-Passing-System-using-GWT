package com.stacs.cs4103.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client-side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
    int addOne(int n);

    String sendMessage(String lamportTime, String messageLabel, String yourProcessId, String senderProcessId, String message);

}
