package com.stacs.cs4103.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.stacs.cs4103.server.GreetingServiceImpl;

import java.util.HashMap;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MessagePassingSystem implements EntryPoint {
    /**
     * This is the entry point method.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);

  //  private final GreetingServiceImpl greetingServiceImpl = new GreetingServiceImpl();

    // CREATE A MESSAGE PASSING SYSTEM
    private final VerticalPanel mainPanel = new VerticalPanel();
    private final TextBox messageLabelTextBox = new TextBox();
    private final TextBox yourProcessIdTextBox = new TextBox();
    private final TextBox senderProcessIdTextBox = new TextBox();
    private final TextArea messageTextArea = new TextArea();
    private final Button sendButton = new Button("Send");
    private final Button receiveButton = new Button("Receive");

    private final Button getProcessID = new Button("Get Process ID");

    private Map<String, Message> messageContainer = new HashMap<String, Message>();

    private Map<String, Message> receivedMessageContainer = new HashMap<String, Message>();

    private Integer processID = 100;

    private int lamportClock = 0;


    public void onModuleLoad() {
        // Every node maintains a Lamport clock and increments it.
        int onModuleLoadClock = 1;
        lamportClock++;
        Window.alert(String.valueOf(onModuleLoadClock));

        mainPanel.add(new Label("Message Passing System"));
        mainPanel.addStyleName("mainPanel");

        mainPanel.add(new Label("Message Label:"));
        messageLabelTextBox.addStyleName("messageLabelTextBox");
        mainPanel.add(messageLabelTextBox);

        mainPanel.add(new Label("Your Process ID:"));
        yourProcessIdTextBox.addStyleName("yourProcessIdTextBox");
        mainPanel.add(yourProcessIdTextBox);

        mainPanel.add(new Label("Sender Process ID:"));
        senderProcessIdTextBox.addStyleName("senderProcessIdTextBox");
        mainPanel.add(senderProcessIdTextBox);

        mainPanel.add(new Label("Message:"));
        messageTextArea.addStyleName("messageTextArea");
        mainPanel.add(messageTextArea);

        getProcessID.addStyleName("getProcessID");
        mainPanel.add(getProcessID);

        sendButton.addStyleName("sendButton");
        mainPanel.add(sendButton);

        receiveButton.addStyleName("receiveButton");
        mainPanel.add(receiveButton);

        RootPanel.get().add(mainPanel);

        // Focus the cursor on the name field when the app loads
        messageTextArea.setFocus(true);
        senderProcessIdTextBox.setFocus(true);
        yourProcessIdTextBox.setFocus(true);


        getProcessID.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Every node maintains a Lamport clock and increments it.
                int getProcessIDClock = lamportClock;
                lamportClock++;
               // processID ++;
                    greetingService.addOne(processID, new AsyncCallback<Integer>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Error: " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Integer result) {
                            Window.alert("Process ID: " + result);
                            yourProcessIdTextBox.setText(result.toString());
                            processID = result;

                        }
                    });
                }

        });

        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Every node maintains a Lamport clock and increments it.
                int sendButtonClock = lamportClock;
                Window.alert(String.valueOf(sendButtonClock));
                lamportClock++;

                greetingService.globalClock(lamportClock,new AsyncCallback<Integer>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(Integer result) {
                        Window.alert("Global Clock: " + result);
                        lamportClock = result;
                    }
                });
                }
            });



        receiveButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Every node maintains a Lamport clock and increments it.
                int receiveButtonClock = lamportClock;
                lamportClock++;
                Window.alert(String.valueOf(receiveButtonClock));
            }
        });


    }

}