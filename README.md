# Message Passing System using GWT

## DESCRIPTION
<p>
This project is a distributed system built using the <strong> Google Web Toolkit (GWT) </strong> technology. 
The purpose of this project is to showcase how to implement a message-passing system using <strong> Lamport clock time </strong>
and total ordering of events, with communication among browser windows acting as independent processes. </p>

---

## Running the Application 
<ol>
 <li> To integrate the files with suitable IDE's follow the <code> GWT_README </code> text file. 
  <li> <p> After doing so , you can either attach an ANT build script to the <code> source </code> folder (or) open 
    terminal in the folder location and <code> ant clean </code> to clean any residual build files adn <code> ant devmode </code> to run the program in developer mode. </p>

---
    
## Functionality : 
    
#### Client Code
    In the client code, each node maintains a local clock using the variable <code>'lamportClock'</code>. The initial value of the clock is 0, and every time an event occurs, the node increments its local clock value by 1.

The Lamport clock algorithm is implemented in two places in the client code: when sending a message and when receiving a message.

When sending a message, the local clock value is used as the timestamp of the message. Before sending the message, the <code> serverLamport() </code> method is called, which updates the local clock value based on the global clock value received from the server.

This ensures that the timestamp of the message is greater than any previously sent message. The message is then sent using the GWT RPC mechanism.

When receiving a message, the local clock value is updated based on the global clock value received from the server using the <code> serverLamport() </code> method. This ensures that the local clock value is synchronized with the global clock value. The local clock value is then incremented by 1, and the message is processed.

In addition to the local clock value, each node maintains a process ID, which is used to identify the sender and receiver of the message. The process IDs are used as the second component of the timestamp.

#### Server Code

In the server code, the Lamport clock algorithm is implemented in two methods:

##### globalClock(int clientLamportTime)
This method takes an integer clientLamportTime as input and returns the server's Lamport time. It updates the server's Lamport time to be the maximum of the client's Lamport time and the server's current Lamport time plus one.

 ##### sendMessage(Message message)
This method takes a Message object as input and returns a string. It updates the message's Lamport time with the server's Lamport time, adds the message to the server's message list, and returns a confirmation string.
    
 ---

## CONTRIBUTION GUIDELINES :
<ol>
<li> Clone the repository from GitHub.
<li> Create a new branch for your feature or bug fix.
<li> Implement your feature or bug fix.
<li> Commit your changes and push to your branch on the GitHub server.
<li> Submit a merge request to merge your changes into the main branch.
</li>
</ol>

---
## LICENSE :
This project is done part of coursework of <strong> CS4103 - Distributed Systems </strong> of the <strong> Computer Science Dept of University of St.Andrews.</strong>
<p> This project is licensed under the MIT License - see the <code> LICENSE </code> file for details. </p>

    
    

   
    
