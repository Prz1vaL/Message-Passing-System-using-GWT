<p> In the field of distributed systems, a logical clock is a mechanism that tracks the number of events that have occurred and is designed to capture causal dependencies between those events. 
Specifically, if event 'e1' happens before event 'e2', then the logical clock should reflect that relationship:</br>

  <center> <code> <em> (e1 -> e2) -> (T (e1) < T (e2)) </em> </code> </center> </p> </center>
  
  ---
  
<p> The Lamport Clock Algorithm is a particular implementation of a logical clock that follows the following steps: </br>

<strong> Step 1 </strong> : Upon initialization, set each node's local clock variable 't' to 0. </br>,center>
  <code> <em> ( t = 0)     …. (1) </em> </code> </p></center>

<p> <strong> Step 2 </strong> : For every local event, increment the value of 't' by 1.  </br><center>
<code> <em> ( t = t + 1)     …. (2) </em> </code> </p></center>

<p> <strong> Step 3 </strong> : When a message 'm' is sent from a node, increment the value of 't' by 1 and attach the new value of 't' to the message. 
            The message is then sent over the network link.</br><center>
<code> <em> (t = t + 1)  … (3)
  Send (t, m) ... (4)</em> </code></p></center>
  
<p> <strong> Step 4 </strong> : When a node receives a message '(t', m)' over the network link, update its local clock variable 't' to the maximum value of 't' received so far and increment it by 1. 
The node then delivers the message 'm' to the application.</br> <center>
  <code> <em> Receive (t’, m) … (5)
    Do t: max (t, t’) + 1 … (6) </em> </code> </br></center>
Were,</br>
 1.  <em> t’ = time stamp attached to the message.</em> </br>
 2.  <em> t: max(t, t’)  = greatest time stamp ( either local or server time stamp)end on. </em> </p>

---

In this algorithm, every node maintains a counter 't', which is incremented for every local event. The value of 't' after an event 'e' occurs is denoted by 'L(e)'. Every message sent over the network is attached with the current value of 't'.

When a recipient receives a message, it moves its clock forward to the timestamp in the message (if it is greater than the local counter) and increments it.

---

<strong> Properties :  </strong>
1) If event a occurs before event b,  then, 
<code> <em> a -> b then L(a) < L(b). </em> </code>
  2) However, <code> <em> L (a) < L (b) </em> </code> does not imply  <code> <em> a -> b</em></code>.
  3) It is possible that <code> <em> L (a) = L (b) </em> </code> for <code> <em> a  ≠ b </em> </code>.
