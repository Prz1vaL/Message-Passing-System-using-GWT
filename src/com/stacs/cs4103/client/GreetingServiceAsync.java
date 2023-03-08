package com.stacs.cs4103.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {

 void addOne(int input, AsyncCallback<Integer> callback);


 void globalClock(int clock, AsyncCallback<Integer> callback);


}
