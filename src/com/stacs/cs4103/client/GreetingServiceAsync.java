package com.stacs.cs4103.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.stacs.cs4103.shared.Message;

import java.util.ArrayList;
import java.util.Map;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

 void addOne(int input, AsyncCallback<Integer> callback);


 void globalClock(int clock, AsyncCallback<Integer> callback);


 void sendMessage(Message message, AsyncCallback<String> async);


 void receiveMessage(int processID, AsyncCallback<ArrayList<Message>> async);
}
