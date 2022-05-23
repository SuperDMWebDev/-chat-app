package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;


// quan ly mot nguoi dang nhap vao server duoi dang username
public class SocketController {
	String userName;
	// thong tin server
	ServerData serverData;
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	
	// luu ten cac user trong client
	public List<String> users;
	// luu cac phong hien co 
	public List<RoomChat> rooms;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public ServerData getServerData() {
		return serverData;
	}
	public void setServerData(ServerData serverData) {
		this.serverData = serverData;
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

	public SocketController(String name,ServerData server)
	{
		users= new ArrayList<String>();
		rooms = new ArrayList<RoomChat>();
		try {
			this.userName=name;
			this.serverData=server;
			Socket socketClient = new Socket(server.getIp(),server.getPort());
			this.socket=socketClient;
			BufferedReader readerStream = new BufferedReader(new InputStreamReader(socket.getInputStream(),StandardCharsets.UTF_8));
			this.reader=readerStream;
			BufferedWriter writerStream = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(),StandardCharsets.UTF_8));
			this.writer=writerStream;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			//xem xet lenh nay  Main.connectServerScreen.loginResultAction("closed");
			e.printStackTrace();
		}
		
	}
	public void createNewUser()
	{
		try {
			writer.write("new user");
			writer.newLine();
			writer.write(this.userName);
			writer.newLine();
			// flush la xa ( xa cac dong ra khoi output)
			writer.flush();
			
			String connectResult=reader.readLine();
			if(connectResult.equals("create successfully"))
			{
				System.out.println("Connect client success");
				System.out.println("next line"+reader.readLine());
				System.out.println("next line"+reader.readLine());
				System.out.println("next line"+reader.readLine());
//				int numberUserOnline = Integer.parseInt(reader.readLine());
//				System.out.println("number online" + numberUserOnline);
//				for(int i=0; i<numberUserOnline;i++)
//				{
//					users.add(reader.readLine());
//				}
//				Controller.frameClientChatUi.updateNumberUserOnline();
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
