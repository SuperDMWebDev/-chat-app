package Client;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class ServerData {
	public String serverName;
	public String ip;
	public int port;
	public String status;
	// 0 hoac 1 the hien co connect hay khong 
	public int connect;
	// so luong cac client 
	public int connectAccount;
	
	
	public int getConnectAccount() {
		return connectAccount;
	}


	public void setConnectAccount(int connectAccount) {
		this.connectAccount = connectAccount;
	}


	@Override
	public String toString() {
		return "ServerData [serverName=" + serverName + ", ip=" + ip + ", port=" + port + ", status=" + status
				+ ", connect=" + connect + "]";
	}


	public ServerData(String serverName, String ip, int port)
	{
		this.serverName = serverName;
		this.ip = ip;
		this.port = port;
		this.status = "No working";
		this.connect= 0;
		this.connectAccount=0;
	}
	public ServerData() {
		super();
	}
	public ServerData(String serverName, String ip, int port, String status, int connect, int connectAccount) {
		super();
		this.serverName = serverName;
		this.ip = ip;
		this.port = port;
		this.status = status;
		this.connect = connect;
		this.connectAccount= connectAccount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getServerName() {
		return serverName;
	}
	public void setServerName(String serverName) {
		this.serverName = serverName;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	public int getConnect() {
		return connect;
	}
	public void setConnect(int connect) {
		this.connect = connect;
	}
	public static List<ServerData> getAllServer(){
		List<ServerData> serverList = new ArrayList<ServerData>();

		try {
			ClassLoader loader = ServerData.class.getClassLoader();
	
			BufferedReader br = new BufferedReader(new FileReader("configServer.txt"));
			 try {
				String line = br.readLine();
				while(line!=null)
				{
					String []data= line.split(",");
					ServerData a= new ServerData(data[0],data[1],Integer.parseInt(data[2]));
					if(a!=null)
					{
						serverList.add(a);
					}
					line=br.readLine();
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return serverList;

	}
}
