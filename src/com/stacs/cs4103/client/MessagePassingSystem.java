package com.stacs.cs4103.client;

import com.google.gwt.event.dom.client.*;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class MessagePassingSystem implements EntryPoint {
  /**
   * The message displayed to the user when the server cannot be reached or
   * returns an error.
   */
  private static final String SERVER_ERROR = "An error occurred while "
      + "attempting to contact the server. Please check your network "
      + "connection and try again.";

  /**
   * Create a remote service proxy to talk to the server-side Greeting service.
   */
  private final GreetingServiceAsync greetingService = GWT.create(GreetingService.class);


  /**
   * This is the entry point method.
   */


  // CREATE A MESSAGE PASSING SYSTEM
  private final VerticalPanel mainPanel = new VerticalPanel();
  private final TextBox messageLabelTextBox = new TextBox();
  private final TextBox yourProcessIdTextBox = new TextBox();
  private final TextBox senderProcessIdTextBox = new TextBox();
  private final TextArea messageTextArea = new TextArea();
  private final Button sendButton = new Button("Send");
private final Button receiveButton = new Button("Receive");


  public void onModuleLoad() {
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

    sendButton.addStyleName("sendButton");
    sendButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        sendMessage();
      }
    });
    mainPanel.add(sendButton);


    receiveButton.addStyleName("receiveButton");
    receiveButton.addClickHandler(new ClickHandler() {
      public void onClick(ClickEvent event) {
        receiveMessage();
      }
    });
    mainPanel.add(receiveButton);

    RootPanel.get().add(mainPanel);

    // Focus the cursor on the name field when the app loads
    messageTextArea.setFocus(true);
    senderProcessIdTextBox.setFocus(true);
    yourProcessIdTextBox.setFocus(true);

  }


        public void sendMessage () {
          String messageLabel = messageLabelTextBox.getText().trim().toUpperCase();
          String yourProcessId = yourProcessIdTextBox.getText().trim();
          String senderProcessId = senderProcessIdTextBox.getText().trim().toUpperCase();
          int senderProcessIdLength = senderProcessId.length();
          String message = messageTextArea.getText().trim().toUpperCase();

          if (messageLabel.isEmpty()) {
            Window.alert("Please enter a valid message label");
            messageLabelTextBox.setFocus(true);
            return;
          }

          if(message.isEmpty()) {
            Window.alert("Please enter a valid message");
            messageTextArea.setFocus(true);
            return;
          }
          if(senderProcessId.isEmpty()) {
            Window.alert("Please enter a valid sender process ID");
            senderProcessIdTextBox.setFocus(true);
            return;
          }
          if (!senderProcessId.matches("[0-9]+") ) {
            Window.alert("Please enter a valid sender process ID");
            senderProcessIdTextBox.selectAll();
            return;
          }


        }

        public void receiveMessage () {
      Window.alert("Receive button clicked");

        }
      }
