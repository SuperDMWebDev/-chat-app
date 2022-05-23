package Client;
import java.net.*;
public class ConnectionController {
	public static boolean getServerOnline(String ip,int port)
	{
		try {
			
			Socket ss= new Socket(ip,port);
			ss.close();
			return true;
			
			
		}catch(Exception ex)
		{
			System.out.println(ex.getMessage());
			return false;
		}
	}
}
