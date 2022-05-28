package Server;

public class Controller {
	public ServerUi frameServerUi = new ServerUi(this);
	public void start()
	{
		ServerUi frame = new ServerUi(this);
		frameServerUi=frame;
		frameServerUi.setVisible(true);
		Main.socketController=new SocketController();
	}
	public void updateNewUser()
	{
		frameServerUi.updateClientTable();
	}
}
