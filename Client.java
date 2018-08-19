import java.io.*;
import java.util.*;

public class Client {
  // takes these as argument 
  // host
  //port
  //proxy port to listen on 
  //name of object to use
  
  
  public static void main (String args[])throws IOException{
    if(args.length!= 4 ) {
      System.out.println("insufficient arguments, requires :  ");
       System.out.println ("<host><port><proxyport > " + "<objectname>");
      return;
    }
    
    
    
  
    
