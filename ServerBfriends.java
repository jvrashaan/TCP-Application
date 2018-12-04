//package broadcast;

import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;



/*
 * A server that delivers status messages to other users.
 */
public class ServerBfriends {

 // Create a socket for the server 
 private static ServerSocket serverSocket = null;
 // Create a socket for the server 
 private static Socket userSocket = null;
 // Maximum number of users 
 private static int maxUsersCount = 5;
 // An array of threads for users
 private static userThread[] threads = null;
 

 public static void main(String args[]) {

  // The default port number.
  int portNumber = 8000;
  if (args.length < 2) {
   System.out.println("Usage: java Server <portNumber>\n"
     + "Now using port number=" + portNumber + "\n" +
     "Maximum user count=" + maxUsersCount);
  } else {
   portNumber = Integer.valueOf(args[0]).intValue();
   maxUsersCount = Integer.valueOf(args[1]).intValue();
  }

  System.out.println("Server now using port number=" + portNumber + "\n" + "Maximum user count=" + maxUsersCount);
  
  
  userThread[] threads = new userThread[maxUsersCount];

  /*
   * Open a server socket on the portNumber (default 8000). 
   */
  try {
   serverSocket = new ServerSocket(portNumber);
  } catch (IOException e) {
   System.out.println(e);
  }

  /*
   * Create a user socket for each connection and pass it to a new user
   * thread.
   */
  while (true) {
   try {
    userSocket = serverSocket.accept();
    int i = 0;
    for (i = 0; i < maxUsersCount; i++) {
     if (threads[i] == null) {
      threads[i] = new userThread(userSocket, threads, i);
      threads[i].start();
      break;
     }
    }
    if (i == maxUsersCount) {
     PrintStream output_stream = new PrintStream(userSocket.getOutputStream());
     output_stream.println("#busy");
     output_stream.close();
     userSocket.close();
    }
   } catch (IOException e) {
    System.out.println(e);
   }
  }
 }
}

/*
 * Threads
 */
class userThread extends Thread {

 private String userName = null;
 private String name = null;
 private BufferedReader input_stream = null;
 private PrintStream output_stream = null;
 private Socket userSocket = null;
 private final userThread[] threads;
 private ArrayList<String> friends = new ArrayList<String>(); 
 private int maxUsersCount;
 private String message = null;
 private boolean flag = false;
 private boolean update = false;
 private boolean connect = false;
 private boolean posresp = false;
 private boolean negresp = false;
 private boolean disconnect = false;
 private boolean protocol = false;
 private String temp = "";
 private int e = 0;
 private int f = 0;
 private int g = 0;
 private int h = 0;
 private int j = 0;
 private int k = 0;
 private int count;

 public userThread(Socket userSocket, userThread[] threads, int number) {
  this.userSocket = userSocket;
  this.threads = threads;
  maxUsersCount = threads.length;
 }

 public void run() {
  int maxUsersCount = this.maxUsersCount;
  userThread[] threads = this.threads;
  
  try {
   /*
    * Create input and output streams for this client.
    * Read user name.
    */
    input_stream = new BufferedReader(new InputStreamReader(this.userSocket.getInputStream())); 
    output_stream = new PrintStream(this.userSocket.getOutputStream());
    name = input_stream.readLine();
   /* Welcome the new user. */
    int y = 6;
    userName = "";
    while(y < name.length()){
       userName += String.valueOf(name.charAt(y));
       y++;
     }
    System.out.println("#welcome " + userName);
    output_stream.println("#welcome");
    int i = 0;
    for (i = 0; i < maxUsersCount; i++){
      if(threads[i] != this && threads[i] != null){
        output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
        output_stream.println("#newUser " + userName);
      } 
    }
   
   /* Start the conversation. */
   String status = "#newStatus";
   String request = "#friendme";
   String accept = "#friend";
   String deny = "#DenyFriendRequest";
   String bye = "#Bye";
   String end = "#unfriend";
   while (true) {
     message = input_stream.readLine();
     System.out.println(message + " " + this.userName);
     count = 0;
     while(!flag){
      //System.out.println(h);
       count++;
       if(message.length() < 4){
        System.out.println("Incorrect message protocol"); 
        protocol = true;
        break;
       }
       if(String.valueOf(bye.charAt(e)).equals(String.valueOf(message.charAt(e)))){
         e++;
         if(e == 4){
           flag = true;
           f = 0;
           g = 0;
           h = 0;
           j = 0;
           k = 0;
           break;
         }
       } 
       if(String.valueOf(status.charAt(f)).equals(String.valueOf(message.charAt(f)))){
         f++;
         if(f == 10){
           f = 0;
           e = 0;
           g = 0;
           j = 0;
           k = 0;
           update = true;
           break; 
         }
       } 
       if(String.valueOf(request.charAt(g)).equals(String.valueOf(message.charAt(g)))) {
         g++;
         if(g == 9){
           f = 0;
           e = 0;
           g = 0;
           h = 0;
           j = 0;
           k = 0;
           connect = true;
           break;
         }
       } 
       if(String.valueOf(accept.charAt(h)).equals(String.valueOf(message.charAt(h)))){
         h++;
         if(h == 7 && !(String.valueOf(message.charAt(7)).equals("m"))){
           f = 0;
           e = 0;
           g = 0;
           h = 0;
           j = 0;
           k = 0;
           posresp = true;
           break;
         } 
         if(h == 7 && posresp == false){
           h = 0;
         }
         
       }
       if(String.valueOf(deny.charAt(j)).equals(String.valueOf(message.charAt(j)))) {
         j++;
         if(j == 18){
           f = 0;
           e = 0;
           g = 0;
           h = 0;
           j = 0;
           k = 0;
           negresp = true;
           break;
         }
       } 
       if(String.valueOf(end.charAt(k)).equals(String.valueOf(message.charAt(k)))) {
         k++;
         if(k == 9){
           f = 0;
           e = 0;
           g = 0;
           h = 0;
           j = 0;
           k = 0;
           disconnect = true;
           break;
         }
       }
       if(count > 18){
         System.out.println("#Incorrect message protocol");
         protocol = true;
         f = 0;
         e = 0;
         g = 0;
         h = 0;
         j = 0;
         k = 0;
         break;
       }
    }
     if(protocol){
       output_stream = new PrintStream(this.userSocket.getOutputStream());
       output_stream.println("#Incorrect message protocol");
       protocol = false;
     }
     if(flag){
       output_stream = new PrintStream(this.userSocket.getOutputStream());
       output_stream.println("#Bye  " + this.userName);
       output_stream.close();
       for (i = 0; i < maxUsersCount; i++){
         if(threads[i] != this && threads[i] != null){
           output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
           output_stream.println("#Leave " + this.userName);
         }
       }
       break;
     }
     if(update){ 
       for(i = 11; i < message.length(); i++){
         temp += String.valueOf(message.charAt(i));
       }
       for (i = 0; i < friends.size(); i++){
         String nm = friends.get(i);
         for(int z = 0; z < maxUsersCount; z++){
           if(threads[z] != null && threads[z].userName.equals(nm)){
             output_stream = new PrintStream(threads[z].userSocket.getOutputStream());
             output_stream.println("#newStatus "+ this.userName + " " + temp);
           }
         }
       }
       update = false;
       temp = "";
       output_stream = new PrintStream(this.userSocket.getOutputStream());
       output_stream.println("#statusPosted");
     }
     if(connect){
       for(i = 10; i < message.length(); i++){
        temp += String.valueOf(message.charAt(i)); 
       }
       for(i = 0; i < maxUsersCount; i++){
         if(threads[i] != null && threads[i].userName.equals(temp)){
           output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
           output_stream.println("#friendme " + this.userName);
         }
       }
       connect = false;
       temp = "";
       output_stream = new PrintStream(this.userSocket.getOutputStream());
       output_stream.println("#FriendRequestSent");
     }
     if(posresp){
       for(i = 8; i < message.length(); i++){
        temp += String.valueOf(message.charAt(i)); 
       }
       for(i = 0; i < maxUsersCount; i++){
         if(threads[i] != null && (threads[i].userName.equals(temp) || threads[i] == this)){
           output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
           output_stream.println("#OKfriends " + this.userName + " " + temp);
         }
         if(threads[i] != null && threads[i].userName.equals(temp)){
          threads[i].friends.add(this.userName); 
          //System.out.println(threads[i].friends);
         }
       }
       posresp = false;
       this.friends.add(temp);
       temp = "";
       //System.out.println(this.friends);
     }
     if(negresp){
       for(i = 19; i < message.length(); i++){
        temp += String.valueOf(message.charAt(i)); 
       }
       for(i = 0; i < maxUsersCount; i++){
         if(threads[i] != null && threads[i].userName.equals(temp)){
           output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
           output_stream.println("#FriendRequestDenied " + this.userName);
         }
       }
       negresp = false;
       temp = "";
     }
     if(disconnect){
       for(i = 10; i < message.length(); i++){
        temp += String.valueOf(message.charAt(i)); 
       }
       for(i = 0; i < maxUsersCount; i++){
         if(threads[i] != null && (threads[i].userName.equals(temp) || threads[i] == this)){
           output_stream = new PrintStream(threads[i].userSocket.getOutputStream());
           output_stream.println("#FriendshipTerminated " + this.userName + " " + temp);
         }
         if(threads[i] != null && threads[i].userName.equals(temp)){
          threads[i].friends.remove(this.userName); 
         }
       }
       disconnect = false;
       this.friends.remove(temp);
       temp = "";
     }
   }

   // conversation ended.

   /*
    * Clean up. Set the current thread variable to null so that a new user
    * could be accepted by the server.
    */
   synchronized (userThread.class) {
    for (i = 0; i < maxUsersCount; i++) {
     if (threads[i] == this) {
      threads[i] = null;
     }
    }
   }
   /*
    * Close the output stream, close the input stream, close the socket.
    */
   input_stream.close();
   //output_stream.close();
   userSocket.close();
  } catch (IOException e) {
  }
 }
}




