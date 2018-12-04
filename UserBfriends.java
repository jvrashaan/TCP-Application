//package broadcast;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class UserBfriends extends Thread {

 // The user socket
 private static Socket userSocket = null;
 // The output stream
 private static PrintStream output_stream = null;
 // The input stream
 private static BufferedReader input_stream = null;

 private static BufferedReader inputLine = null;
 private static String userInput = "";
 private static boolean closed = false;
 private boolean left = false;
 private boolean flag = false;
 private boolean neww = false;
 private boolean update = false;
 private boolean posted = false;
 private boolean busy = false;
 private boolean welcome = false;
 private boolean newfriend = false;
 private boolean request = false;
 private boolean term = false;
 private boolean reject = false;
 private boolean sent = false;
 private String temp = "";
 //private static boolean semi = false;
 private static boolean first = true;
 private static boolean firstt = true;
 private int x = 0;
 private int y = 0;
 private int z = 0;
 private int a = 0;
 private int b = 0;
 private int c = 0;
 private int d = 0;
 private int e = 0;
 private int f = 0;
 private int g = 0;
 private int h = 0;
 private int s = 0;
 private int r = 0;

 public static void main(String[] args) {

  // The default port.
  int portNumber = 8000;
  // The default host.
  String host = "localhost";

  if (args.length < 2) {
   System.out
   .println("Usage: java User <host> <portNumber>\n"
     + "Now using host=" + host + ", portNumber=" + portNumber);
  } else {
   host = args[0];
   portNumber = Integer.valueOf(args[1]).intValue();
  }

  /*
   * Open a socket on a given host and port. Open input and output streams.
   */
  try {
   userSocket = new Socket(host, portNumber);
   inputLine = new BufferedReader(new InputStreamReader(System.in));
   output_stream = new PrintStream(userSocket.getOutputStream());
   input_stream = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
  } catch (UnknownHostException e) {
   System.err.println("Don't know about host " + host);
  } catch (IOException e) {
   System.err.println("Couldn't get I/O for the connection to the host "
     + host);
  }

  /*
   * If everything has been initialized then we want to write some data to the
   * socket we have opened a connection to on port portNumber.
   */
  if (userSocket != null && output_stream != null && input_stream != null) {
   try {                
    /* Create a thread to read from the server. */
    new Thread(new UserBfriends()).start();

    // Get user name and join the social net

    while (!closed) {
     if(first){
        System.out.println("Enter <username>"); 
        userInput = inputLine.readLine().trim();
        output_stream.println("#join " + userInput);
        first = false;
     } // Read user input and send protocol message to server
     else {
       while(firstt){};
       System.out.println("Enter #newStatus <text> to update status, #friendme <username> to send a friend request, #friend <username> to accept a friend request, #DenyFriendRequest to deny a friend request, #unfriend <username> to end a friendship, or enter #Bye to exit: "); 
       userInput = inputLine.readLine().trim();
       output_stream.println(userInput);
       if(userInput == "#Bye"){
         break;
       }
     }  
    }
    /*
     * Close the output stream, close the input stream, close the socket.
     */
   } catch (IOException e) {
    System.err.println("IOException:  " + e);
   }
  }
 }

 /*
  * Create a thread to read from the server.
  */
 public void run() {
  /*
   * Keep on reading from the socket till we receive a Bye from the
   * server. Once we received that then we want to break.
   */
  String responseLine;
  
  try {
   while ((responseLine = input_stream.readLine()) != null) {
     String newUser = "#newUser";
     String friendme = "#friendme";
     String friend = "#OKfriends";
     String no = "#FriendRequestDenied";
     String over = "#FriendshipTerminated";
     String confirm = "#FriendRequestSent";
     String newStatus = "#newStatus";
     String bye = "#Bye";
     String leave = "#Leave";
     String post = "#statusPosted";
     String filled = "#busy";
     String hello = "#welcome";
     String incorrect = "#Incorrect";
     
    // Display on console based on what protocol message we get from server.
    while(!flag){
      if(String.valueOf(bye.charAt(x)).equals(String.valueOf(responseLine.charAt(x)))){
        x++;
        if(x == 4){
          flag = true;
          break;
        } 
      }
      if(String.valueOf(newStatus.charAt(y)).equals(String.valueOf(responseLine.charAt(y)))){
        y++;
        if(y == 10){
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          update = true;
          break; 
        }
      }
      if(String.valueOf(newUser.charAt(z)).equals(String.valueOf(responseLine.charAt(z)))){
        z++;
        if(z == 8){
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          neww = true;
          break;
        }
      }
      if(String.valueOf(post.charAt(a)).equals(String.valueOf(responseLine.charAt(a)))){
        a++;
        if(a == 13){
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          posted = true;
          break;
        }
      }
      if(String.valueOf(leave.charAt(b)).equals(String.valueOf(responseLine.charAt(b)))){
        b++;
        if(b == 6){
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          left = true;
          break;
        }
      }
      if(String.valueOf(filled.charAt(c)).equals(String.valueOf(responseLine.charAt(c)))){
        c++;
        if(c == 5){
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          busy = true;
          break;
        }
      } 
      if(String.valueOf(hello.charAt(d)).equals(String.valueOf(responseLine.charAt(d)))){
        d++;
        if(d == 8){
          welcome = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      } 
      if(String.valueOf(friendme.charAt(e)).equals(String.valueOf(responseLine.charAt(e)))){
        e++;
        if(e == 9){
          request = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      }
      if(String.valueOf(friend.charAt(f)).equals(String.valueOf(responseLine.charAt(f)))){
        f++;
        if(f == 10){
          newfriend = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      }
      if(String.valueOf(no.charAt(g)).equals(String.valueOf(responseLine.charAt(g)))){
        g++;
        if(g == 20){
          reject = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      }
      if(String.valueOf(over.charAt(h)).equals(String.valueOf(responseLine.charAt(h)))){
        h++;
        if(h == 21){
          term = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      }
      if(String.valueOf(confirm.charAt(s)).equals(String.valueOf(responseLine.charAt(s)))){
        s++;
        if(s == 18){
          sent = true;
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
        }
      }
      if(String.valueOf(incorrect.charAt(r)).equals(String.valueOf(responseLine.charAt(r)))){
         r++;
         if(r == 10){
          System.out.println(responseLine); 
          y = 0;
          x = 0;
          z = 0;
          a = 0;
          b = 0;
          c = 0;
          d = 0;
          e = 0;
          f = 0;
          g = 0;
          h = 0;
          s = 0;
          r = 0;
          break;
         }
       }
    }
     if(flag){
       System.out.println("#Bye");
     }
     int i = 0;
     if(update){
       for(i = 0; i < responseLine.length(); i++){
         temp += String.valueOf(responseLine.charAt(i));
       }
       System.out.println(temp);
       temp = "";
       update = false;
       firstt = false;
     }
     if(neww){
       for(i = 0; i < responseLine.length(); i++){
         temp += String.valueOf(responseLine.charAt(i));
       }
       System.out.println(responseLine);
       temp = "";
       neww = false;
     }
     if(left){
       for(i = 6; i < responseLine.length(); i++){
         temp += String.valueOf(responseLine.charAt(i));
       }
       System.out.println("User" + temp + " has left");
       temp = "";
       left = false;
     }
     if(posted){
       System.out.println("Status posted");
       posted = false;
     }
     if(busy){
       System.out.println("Server is Busy, try again later.");
       busy = false;
     }
     if(welcome){
       System.out.println("#Welcome");
       System.out.println("Server connection has been made.");
       welcome = false;
       firstt = false;
     }
     if(newfriend){
       newfriend = false;
       System.out.println(responseLine);
     }
     if(term){
       term = false;
       System.out.println(responseLine);
     }
     if(request){
       request = false;
       System.out.println(responseLine);
     }
     if(reject){
       reject = false;
       System.out.println(responseLine);
     }
     if(sent){
       sent = false;
       System.out.println(responseLine);
       firstt = false;
     }
   }
   closed = true;
   output_stream.close();
   input_stream.close();
   userSocket.close();
   System.exit(0);
  } catch (IOException e) {
   System.err.println("IOException:  " + e);
  }
 }
}



