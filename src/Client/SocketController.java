package Client;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;



// quan ly mot nguoi dang nhap vao server duoi dang username
public class SocketController {
	String userName;
	// thong tin server
	ServerData serverData;
	Socket socket;
	BufferedReader reader;
	BufferedWriter writer;
	
	Thread receiveAndProcessThread;
	// luu ten cac user trong client.
	
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
	public void createNewUser(final Controller controller)
	{
		try {
			writer.write("new login");
			writer.newLine();
			writer.write(this.userName);
			writer.newLine();
			// flush la xa ( xa cac dong ra khoi output)
			writer.flush();
			
			String connectResult=reader.readLine();
			if(connectResult.equals("create successfully"))
			{
			
				System.out.println("Connect client success");
				Main.controller.openClientChatUi();
				Main.controller.closeAddUserToServerUi();
				int numberUserOnline = Integer.parseInt(reader.readLine());
				System.out.println("numberUserOnline "+numberUserOnline);
				for(int i=0;i<users.size();i++)
				{
					System.out.println("users "+users.get(i));
				}
				for(int i=0; i<numberUserOnline;i++)
				{
				
					users.add(reader.readLine());
				}
			
				Main.controller.frameClientChatUi.updateNumberUserOnline();
			    Main.controller.frameClientChatUi.updateOnlineUserJList();
			
			
			// thread nhan tin hieu
			receiveAndProcessThread = new Thread(()->{
				try {
					while(true)
					{
						String title= reader.readLine();
						System.out.println("title "+ title);
						if(title==null)
						{
							throw new IOException();
						}
						switch(title)
						{			
						case "new user online":{
							String userN = reader.readLine();
							users.add(userN);
							controller.frameClientChatUi.updateNumberUserOnline();
							controller.frameClientChatUi.updateOnlineUserJList();
						}
						case "new room":{
							int roomId = Integer.parseInt(reader.readLine());
							String whoCreate= reader.readLine();
							String name = reader.readLine();
							String type = reader.readLine();
							int roomUser= Integer.parseInt(reader.readLine());
							List<String> users = new ArrayList<String>();
							for (int i=0; i<roomUser; i++)
							{
								users.add(reader.readLine());
							}
							RoomChat newRoom= new RoomChat(roomId,name,type,users);
							Main.socketController.rooms.add(newRoom);
							break;
						}
							default:
								break;
						}
					}
				}catch(IOException e)
				{
					JOptionPane.showMessageDialog(Main.controller.frameClientChatUi, "Server đã đóng, ứng dụng sẽ thoát", "Thông báo",
							JOptionPane.INFORMATION_MESSAGE);
				}
			});
			receiveAndProcessThread.start();
			}else {
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void createPrivateRoom(String name) {

		try {
			writer.write("create new room");
			writer.newLine();
			writer.write(name); // name cua nguoi chuan bi tro chuyen
			writer.newLine();
			writer.write("private"); // kieu room
			writer.newLine();
			writer.write("2"); // so luong
			writer.newLine();
			writer.write(userName);
			writer.newLine();
			writer.write(name);
			writer.newLine();
			writer.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
}
