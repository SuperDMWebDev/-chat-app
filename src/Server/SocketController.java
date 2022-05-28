package Server;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.*;
import java.util.*;





public class SocketController {
	public static String serverName;
	public static String serverIp;
	public static ServerSocket server;
	
	// list cac client connect
	public static List<ClientInformation> client;
	// list cac room dang ton tai 
	public static List<RoomChat> rooms;
	public static String getServerIp()
	{
		


		try {
			// lay duoc ip address cua may tinh 
		    InetAddress myIP=InetAddress.getLocalHost();
		    serverIp=myIP.getHostAddress();
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverIp;
		
	}
	public static String getServerName()
	{
		try {
			// lay duoc ip address cua may tinh 
		    InetAddress myIP=InetAddress.getLocalHost();
		    serverName=myIP.getHostAddress();
		    
		    
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverName;
		
	}
	public static void OpenSocket(int portNumber)
	{
	
		try {
			server = new ServerSocket(portNumber);
			client=new ArrayList<ClientInformation>();
			rooms = new ArrayList<RoomChat>();
			Thread thread = new Thread(() -> {	
				try {
				
					do {
						System.out.println("Waiting for client");
	
						Socket ss = server.accept();
						// connect
						System.out.println("System connect");
						ClientCommunication newClient = new ClientCommunication(ss);
						newClient.start();
					} while (server != null && !server.isClosed());
				} catch (IOException e) {
					System.out.println("Server or client socket closed");
				}
			});
		thread.start();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
//			ClientThread clientThread = new ClientThread(ss);
//			clientThread.start();

				
				
		
	}
	public static void closeSocket()
	{
		try {
			// tu in ra 1 thong bao close
			server.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public static void main(String[] args) {
//		String ip = SocketController.getThisIp();
//		System.out.println("ip"+ip);
//	}
}
