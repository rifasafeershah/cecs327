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
import java.net.UnknownHostException;
import java.util.Scanner;
  
public class UDPClient
{
    
	public static void main(String args[]) throws IOException
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please Enter Port Number: "); //let client input the port number
        int serverPort = inputServerPort();
        
        System.out.println("Please Enter Server's Public IP address: "); //let client input the Server's public IP address
        InetAddress aHost = InetAddress.getByName(inputServerIP());

        DatagramSocket aSocket = null;
        
        try 
        {
        	aSocket = new DatagramSocket();
        	
        	while (true)
            {
        		System.out.println("Please Enter Message: ");
                String inp = sc.nextLine();
                
        		if (inp.equals("stop"))//stop function when receive "stop"
                {
                	System.out.println("System closed, Goodbye!");
                	aSocket.close(); //close the socket
                	break;
                }
        		else 
        		{
        			byte[] m = inp.getBytes();
                    DatagramPacket request = new DatagramPacket(m,  m.length, aHost, serverPort);
                    aSocket.send(request);
                    byte[] buffer = new byte[1000];
                    DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                    aSocket.receive(reply);
                    System.out.println("Server's Echo: " + new String(reply.getData())); //Display the message receive from Server 
                    
                    buffer = new byte[1000]; //clean
        			
        		}
            }	
        }catch (SocketException e){System.out.println("Socket: " + e.getMessage());
        }catch (IOException e) {System.out.println("IO: " + e.getMessage());
        }finally {if(aSocket != null) aSocket.close();
        }
    }
	
	
	
	
    
	/**
	 * Check the client input of port number correctness
	 * @return the portNumber
	 */
    public static int inputServerPort()
	{	
		int portNumber =0;
		boolean valid = false;
		int temp = 0;
		while(!valid)
		{
			temp = getInt();
			if(temp == 6789)
			{
				portNumber = temp;
				valid = true;
			}
			else
			{
				System.out.println( "Wrong Number, Enter Another Port Number Again: " );
			}
		}
		
		return portNumber;
	}
    
    /**
     * Check the client input of public IP address correctness
     * @return
     * @throws IOException
     */
    public static String inputServerIP() throws IOException
    {
    	String serverIP = null;
		boolean valid = false;
		String temp;
		
		while(!valid)
		{
			temp = getString();
		
			if(temp.equals("73.92.199.15") || temp.equals("47.144.149.247") || temp.equals("35.225.145.103"))
			{
				serverIP = temp;
				valid = true;
			}
			else
			{
				System.out.println( "Wrong IP, Please Enter Another IP Again: " );
			}
		}
    	
    	return serverIP;
    }
    
	/**
	 * Takes in a string from the user.
	 * @return the inputted String.
	 */
	public static String getString() 
	{
		Scanner in = new Scanner( System.in );
		String input = in.nextLine();
		return input;
	}
	
	/**
	 * Checks if the inputted value is an integer.
	 * @return the valid input.
	 */
	public static int getInt() {
		Scanner in = new Scanner( System.in );
		int input = 0;
		boolean valid = false;
		while( !valid ) {
			if( in.hasNextInt() ) 
			{
				input = in.nextInt();
				valid = true;
			} else {
				in.next(); //clear invalid string
				System.out.println( "Invalid Input." );
			}
		}
		return input;
		
	}

    
}