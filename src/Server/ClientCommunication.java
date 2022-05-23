package Server;
import java.net.*;
import java.util.List;
import java.io.*;
public class ClientCommunication extends Thread{
	public ClientInformation client;
	public ClientCommunication(Socket socket)
	{
		
		try {
			client=new ClientInformation();
			client.setSocket(socket);
			BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			client.setReader(is);
			BufferedWriter os= new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			client.setWriter(os);
			client.setPort(socket.getPort());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() 
	{
		boolean checkExisted=false;
		// nhan du lieu tu nguoi dung va tra loi
		while(true)
		{
			try {
				String message = client.getReader().readLine();
				if(message!=null)
				{
					System.out.println("Message server"+message);
					switch(message)					{
						case"new user":
							System.out.println("New client in server");
							String userName=client.getReader().readLine();
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
								client.setUserName(userName);
								Main.socketController.client.add(client);
								// update cai danh sach client 
								// tao thanh cong ket noi 
								client.getWriter().write("create successfully");
								client.getWriter().newLine();
								client.getWriter().flush();
								
								// gui so luong client da ket noi 
								System.out.println("size"+Main.socketController.client.size());
								client.getWriter().write(Main.socketController.client.size());
								client.getWriter().newLine();
								client.getWriter().flush();
								
								//gui thong tin cac client da ket noi tru client hien tai 
								for(ClientInformation client : Main.socketController.client)
								{
									if(client.getUserName().equals(userName))
										continue;
									client.getWriter().write(client.getUserName());
									client.getWriter().newLine();
									client.getWriter().flush();
								}
								
								
								
							}
							else {
								//ket noi that bai 
								client.getWriter().write("create failed");
								client.getWriter().newLine();
								client.getWriter().flush();
							}
							break;
						
								
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
	
}
