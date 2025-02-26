
/** 
 * CECS 327 - Assignment 4: Socket Programming
 *
 * Requirements: You are required to write an Echo Client and an Echo Server program.
 * The echo client communicates with the echo server using UDP.
 * 
 * @author Zixi Liu & Rifa Safeer Shah
 * @date 04/21/2021
 */

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

  
public class UDPServer
{
	
    public static void main(String[] args) throws IOException
    {
        DatagramSocket  aSocket = null;
        try 
        {
        	System.out.println("Welcome! ");
            aSocket = new DatagramSocket(6789);// Create a socket to listen at port 6789
        	byte[] buffer = new byte[1000];
        	
        	while(true) 
        	{
        		if (data(buffer).toString().equals("stop")) //stop function when receive "stop"
                {
        			System.out.println("System closed, Goodbye!");
                    aSocket.close();//close the socket
                    break;
                }
        		else
        		{
        			DatagramPacket request = new DatagramPacket(buffer, buffer.length);
             		aSocket.receive(request);
             		System.out.println("Client Message: " + data(buffer)); //display Client message
             		byte[] st = upperCase(request.getData()).getBytes(); //convert message to upper case
             		DatagramPacket reply = new DatagramPacket(st, request.getLength()
             				                    , request.getAddress(), request.getPort());
             		aSocket.send(reply);// send upper case message to client
        			buffer = new byte[1000]; //clean
        		}
        	
        	}
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();
        }   	
    }
  

    /**
     * Convert the byte array data to String
     * @param ary
     * @return a string of information
     */
    public static String data(byte[] ary)
    {
        if (ary == null)
            return null;
        StringBuilder x = new StringBuilder();
        int i = 0;
        while (ary[i] != 0)
        {
            x.append((char) ary[i]);
            i++;
        }
        String info = x.toString();
        
        return info;
    }
    
    /**
     * Convert the byte array data to a Upper case String
     * @param ary
     * @return a string of upper case information 
     */
    public static String upperCase(byte[] ary)
    {
    	byte[] upper = null;
        if (ary == null)
            return null;
        StringBuilder info = new StringBuilder();
        int i = 0;
        while (ary[i] != 0)
        {
        	char temp = Character.toUpperCase((char) ary[i]);
            info.append(temp);
            i++;
        }
        String  upInfo= info.toString();

        return upInfo;
    }
    
}