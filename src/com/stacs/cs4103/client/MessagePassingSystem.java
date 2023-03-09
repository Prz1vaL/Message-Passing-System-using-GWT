package com.stacs.cs4103.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.*;
import com.stacs.cs4103.shared.Message;
import java.util.Map;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MessagePassingSystem implements EntryPoint {
    /**
     * This is the entry point method.
     */
    private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);



    // CREATE A MESSAGE PASSING SYSTEM
    private final VerticalPanel mainPanel = new VerticalPanel();
    private final TextBox messageLabelTextBox = new TextBox();
    private final TextBox yourProcessIdTextBox = new TextBox();
    private final TextBox senderProcessIdTextBox = new TextBox();
    private final TextArea messageTextArea = new TextArea();
    private final Button sendButton = new Button("Send");
    private final Button receiveButton = new Button("Receive");


    private Integer processID = 100;

    private int lamportClock = 0;

    private Integer senderProcessID = 0;


    public void onModuleLoad() {

        // Every node maintains a Lamport clock and increments it.
        lamportClock++;
        int onModuleLoadClock = lamportClock;
        Window.alert("Initial Local Clock :" + onModuleLoadClock);

        // Set the process - ID of the client.
        greetingService.addOne(processID, new AsyncCallback<Integer>() {
            @Override
            public void onFailure(Throwable caught) {
                Window.alert("Error: " + caught.getMessage());
            }

            @Override
            public void onSuccess(Integer result) {
                yourProcessIdTextBox.setText(result.toString());
                processID = result;

            }
        });


        // Styling and HTML

        mainPanel.add(new Label("Message Passing System"));
        mainPanel.addStyleName("mainPanel");

        mainPanel.add(new Label("Your Process ID:"));
        yourProcessIdTextBox.addStyleName("yourProcessIdTextBox");
        mainPanel.add(yourProcessIdTextBox);

        mainPanel.add(new Label("Sender Process ID:"));
        senderProcessIdTextBox.addStyleName("senderProcessIdTextBox");
        mainPanel.add(senderProcessIdTextBox);

        mainPanel.add(new Label("Message:"));
        messageTextArea.addStyleName("messageTextArea");
        mainPanel.add(messageTextArea);

        sendButton.addStyleName("sendButton");
        mainPanel.add(sendButton);

        receiveButton.addStyleName("receiveButton");
        mainPanel.add(receiveButton);

        RootPanel.get().add(mainPanel);

        // Focus the cursor on the name field when the app loads
        messageTextArea.setFocus(true);
        senderProcessIdTextBox.setFocus(true);
        yourProcessIdTextBox.setFocus(true);


        // Local Nodes and Buttons
        sendButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                // Every node maintains a Lamport clock and increments it.
                int sendButtonClock = lamportClock;
                Window.alert(String.valueOf(sendButtonClock));
                lamportClock++;
                if(senderProcessIdTextBox.getText().isEmpty()){
                    Window.alert("Please enter a sender process ID");
                }
                try{
                    senderProcessID = Integer.parseInt(senderProcessIdTextBox.getText());

                } catch (NumberFormatException e ) {
                    Window.alert("Please enter a valid number");
                }
                serverLamport();

                Message message = new Message(lamportClock, senderProcessID, processID,messageTextArea.getText());
                greetingService.sendMessage(message, new AsyncCallback<String>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        Window.alert("Error: " + caught.getMessage());
                    }

                    @Override
                    public void onSuccess(String result) {
                        Window.alert("Message Sent: " + result);
                    }
                });

            }
        });

        receiveButton.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                // Every node maintains a Lamport clock and increments it.
                int receiveButtonClock = lamportClock;
                Window.alert(String.valueOf(receiveButtonClock));
                // When a node receives a message '(t', m)' over the network link,
                // update its local clock variable 't' to the maximum value of 't'
                // received so far and increment it by 1.
                serverLamport();
                lamportClock++;

                    greetingService.receiveMessage(processID, new AsyncCallback<Map<String, Message>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            Window.alert("Error: " + caught.getMessage());
                        }

                        @Override
                        public void onSuccess(Map<String, Message> result) {
                            int messageTimeStamp = 0;
                            String senderID = "";
                            String messageContent = "";
                            for (Map.Entry<String, Message> entry : result.entrySet()) {
                                messageTimeStamp = entry.getValue().getLamportTime();
                                senderID = String.valueOf(entry.getValue().getProcessID());
                                messageContent = entry.getValue().getMessage();
                            }
                            Window.alert("Message Received: "+"<"+ messageTimeStamp +"> : " + senderID + " : " + messageContent);
                        }
                    });
            }
        });
    }

    private void serverLamport() {
        greetingService.globalClock(lamportClock, new AsyncCallback<Integer>() {
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

}