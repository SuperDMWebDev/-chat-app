package Server;

public class Main {
	public static SocketController socketController;
	
	public static void main(String[] args) {
		Controller controller= new Controller();
		controller.start();
	}
}
