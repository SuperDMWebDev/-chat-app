package Server;

public class Controller {
	ServerUi frameServerUi;
	public void start()
	{
		ServerUi frame = new ServerUi(this);
		frameServerUi=frame;
		frameServerUi.setVisible(true);
		Main.socketController=new SocketController();
	}
}
