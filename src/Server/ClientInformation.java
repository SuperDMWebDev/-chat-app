package Server;
import java.net.*;
import java.util.List;
import java.io.*;
public class ClientInformation {
	private String userName;
	private int port;
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;
	
	
	public static ClientInformation findClient (List<ClientInformation> listClient, String userName)
	{
		for(ClientInformation client:listClient)
		{
			if(client.userName.equals(userName))
			{
				return client;
			}
		}
		return null;
	}
	public ClientInformation() {
		super();
	}
	public ClientInformation(String userName, int port, Socket socket, BufferedReader reader, BufferedWriter writer) {
		super();
		this.userName = userName;
		this.port = port;
		this.socket = socket;
		this.reader = reader;
		this.writer = writer;
	}
	@Override
	public String toString() {
		return "ClientInformation [userName=" + userName + ", port=" + port + ", socket=" + socket + ", reader="
				+ reader + ", writer=" + writer + "]";
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public Socket getSocket() {
		return socket;
	}
	public void setSocket(Socket socket) {
		this.socket = socket;
	}
	public BufferedReader getReader() {
		return reader;
	}
	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}
	public BufferedWriter getWriter() {
		return writer;
	}
	public void setWriter(BufferedWriter writer) {
		this.writer = writer;
	}
	
	
}
