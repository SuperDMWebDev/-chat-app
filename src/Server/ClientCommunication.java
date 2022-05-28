package Server;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

import Server.Main;

import java.io.*;
public class ClientCommunication extends Thread{
	public ClientInformation connectingClient;
	public ClientCommunication(Socket socket)
	{
		
		try {
			connectingClient=new ClientInformation();
			connectingClient.setSocket(socket);
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			connectingClient.setReader(is);
			BufferedWriter os= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			connectingClient.setWriter(os);
			connectingClient.setPort(socket.getPort());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() 
	{
	
		// nhan du lieu tu nguoi dung va tra loi
		try {
		while(true)
		{
				boolean checkExisted=false;
				String message = connectingClient.getReader().readLine();
				if(message==null)
				{
					throw new IOException();
				}
				
				System.out.println("Message server"+message);
				switch(message)	{
						case"new login":{
							System.out.println("New client in server");
							String userName=connectingClient.getReader().readLine();
							// lay ten nguoi dinh dang nhap
							System.out.println("User server "+userName);
							List<ClientInformation> clientList= Main.socketController.client;
							for(ClientInformation clientInfo: clientList)
							{
								if(clientInfo.getUserName().equals(userName))
								{
									checkExisted=true;
									break;
								}
							}
							// khong tim thay ton tai 
							if(checkExisted==false)
							{
								// tao username moi 
								System.out.println("Tao user moi");
								connectingClient.setUserName(userName);
								Main.socketController.client.add(connectingClient);
								Main.controller.updateNewUser();
								// update cai danh sach client 
								// tao thanh cong ket noi 
								connectingClient.getWriter().write("create successfully");
								connectingClient.getWriter().newLine();
								connectingClient.getWriter().flush();
								
								// gui so luong client da ket noi 
								System.out.println("size "+Main.socketController.client.size());
								connectingClient.getWriter().write(String.valueOf(Main.socketController.client.size()-1));
								connectingClient.getWriter().newLine();
								connectingClient.getWriter().flush();
								
								//gui thong tin cac client da ket noi tru client hien tai 
								for(ClientInformation client : Main.socketController.client)
								{
									if(client.getUserName().equals(connectingClient.getUserName()))
											continue;
									// server da vao cho nay 
									System.out.println("ten client "+ client.getUserName());
									client.getWriter().write(client.getUserName());
									client.getWriter().newLine();
									client.getWriter().flush();
								
								}
								
								for(ClientInformation client : Main.socketController.client)
								{
									if (client.getUserName().equals(connectingClient.getUserName()))
										continue;
									client.getWriter().write("new user online");
									client.getWriter().newLine();
									client.getWriter().write(connectingClient.getUserName());
									client.getWriter().newLine();
									client.getWriter().flush();
								}
								
								
							}
							else {
								//ket noi that bai 
								connectingClient.getWriter().write("create failed");
								connectingClient.getWriter().newLine();
								connectingClient.getWriter().flush();
							}
							break;
						}
						case "create new room": {
							String roomName = connectingClient.getReader().readLine();
							System.out.println("room name" + roomName);
							String roomType = connectingClient.getReader().readLine();
							System.out.println("room type" +roomType);
							int userCount = Integer.parseInt(connectingClient.getReader().readLine());
							// gom tat ca users lai 
							List<String> users = new ArrayList<String>();
							for (int i = 0; i < userCount; i++)
								users.add(connectingClient.getReader().readLine());
							// tao mot room chat moi
							RoomChat newRoom = new RoomChat(roomName, users);
							Main.socketController.rooms.add(newRoom);
							
							
							// bat dau chat
							for(int i=0;i<userCount;i++)
							{
								BufferedWriter currentWriter = ClientInformation.findClient(Main.socketController.client,users.get(i)).getWriter();
								// khoi tao phong moi
								currentWriter.write("new room");
								currentWriter.newLine();
								// gui id phong
								currentWriter.write(String.valueOf(newRoom.getId()));
								currentWriter.newLine();
								// gui username cua client
								currentWriter.write(connectingClient.getUserName());
								currentWriter.newLine();
								
								// gui di thong tin phong la private
								if(roomType.equals("private"))
								{
									// gui ten nguoi con lai ( 1 thi gui 0 , 0 thi gui 1 )
									currentWriter.write(users.get(1-i));
									currentWriter.newLine();
								}
								currentWriter.write(roomType);
								currentWriter.newLine();
								currentWriter.write(String.valueOf(users.size()));
								currentWriter.newLine();
								for (String user : users) {
									currentWriter.write(user);
									currentWriter.newLine();
								}
								currentWriter.flush();
							}
						}
						
						
					}
								
			}
				
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
