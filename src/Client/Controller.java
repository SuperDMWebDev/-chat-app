package Client;

public class Controller {
	public ClientUi frameClientUi;
	public AddServerUi frameAddServerUi;
	public EditServerUi frameEditServerUi;
	public AddUserToServerUi frameAddUserToServerUi;
	public static ClientChatUi frameClientChatUi;
	
	public void start()
	{
		ClientUi frame1= new ClientUi(this);
		frameClientUi=frame1;
		frameClientUi.setVisible(true);
	}
	public void openAddServerUi()
	{
		AddServerUi frame1= new AddServerUi(this);
		frameAddServerUi=frame1;
		frameAddServerUi.setVisible(true);
	}
	public void closeAddServerUi()
	{
		frameAddServerUi.setVisible(false);
		frameAddServerUi.dispose();
	}
	public void openEditServerUi(int rowSelected,String serverName,String ip,int port)
	{
		EditServerUi frame1 = new EditServerUi(this,rowSelected,serverName,ip,port);
		frameEditServerUi=frame1;
		frameEditServerUi.setVisible(true);
	}
	public void closeEditServerUi()
	{
		frameEditServerUi.setVisible(false);
	}
	public void openAddUserToServerUi(String serverName,String ipServer,int portServer)
	{
		AddUserToServerUi frame1= new AddUserToServerUi(this,serverName,ipServer,portServer);
		frameAddUserToServerUi= frame1;
		frameAddUserToServerUi.setVisible(true);
	}
	public void closeAddUserToServerUi()
	{
		frameAddUserToServerUi.setVisible(false);
		frameAddUserToServerUi.dispose();
	}
	public void openClientChatUi(String userName)
	{
		ClientChatUi frame1= new ClientChatUi(this,userName);
		frameClientChatUi=frame1;
		frameClientChatUi.setVisible(true);
	}
	public void closeClientChatUi()
	{
		frameClientChatUi.setVisible(false);
	}
	

}
