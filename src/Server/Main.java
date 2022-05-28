package Server;

public class Main {
	public static SocketController socketController;
	public static Controller controller;
	
	public static void main(String[] args) {
		controller= new Controller();
		controller.start();
	}
}
