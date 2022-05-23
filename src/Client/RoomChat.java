package Client;

import java.util.ArrayList;
import java.util.List;


public class RoomChat {
	public int idRoom;
	public String name;
	public String type;
	public List<String> users;
	public List<MessageData> messages;
	
	public RoomChat(int idRoom, String name, String type, List<String> users) {
		this.idRoom = idRoom;
		this.name = name;
		this.type = type;
		this.users = users;
		this.messages = new ArrayList<MessageData>();
	}
	
	
	
}
