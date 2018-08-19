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
    String host = args[0];
    int port = Integer.valueOf(args[1]).intValue;
    int proxyport =Integer.valueOf(args[2]).intValue; 
    String name = args[3] ; 
    
    // create instance of RMI to connect to registory 
    RMI rmi = null ;
    RemoteObjectRefrence ror = null;
    try{
      rmi = new RMI (host,port, proxyport);
      ror = rmi.lookup(name);
    } catch (Exception e){
      System.out.println(e);
      System.out.println("either arguments incorrect or name not in registory ");
      if(rmi!=null){
        rmi.close();
        return ;
      }
      MathInter maths = null;
      try{
        maths = (MathInter)ror.localise();
      }catch(Exception e){
        System.out.println("localise has thrown exception");
        System.out.println(e);
        return ; 
      }
    
         if(!maths.contains("a")) {
            maths.newVar("a", (Integer)3);
        }
        if(!maths.contains("b")) {
            maths.newVar("b", (Integer)6);
        }
        if(!maths.contains("c")) {
            maths.newVar("c", (Integer)69);
        }

        maths.addTo("a", maths.getValue("b"));
        maths.addTo("c", maths.getValue("a"));

        System.out.println("The value of a is " + maths.getValue("a"));
        System.out.println("The value of b is " + maths.getValue("b"));
        System.out.println("The value of c is " + maths.getValue("c"));

        maths.remove("b");

        // prints all variables on remote end
        maths.printAll();

        rmi.close();
        return;
    }
}
    
    
  
    
