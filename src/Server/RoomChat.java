package Server;

import java.util.List;

public class RoomChat {
	public static int currentRoomID = 1;

	private int id;
	private String name;
	private List<String> users;

	public RoomChat(String name, List<String> users) {
		this.id = currentRoomID++;
		this.name = name;
		this.users = users;
	}

	public static RoomChat findRoom(List<RoomChat> roomList, int id) {
		for (RoomChat room : roomList)
			if (room.id == id)
				return room;
		return null;
	}

	public static int getCurrentRoomID() {
		return currentRoomID;
	}

	public static void setCurrentRoomID(int currentRoomID) {
		RoomChat.currentRoomID = currentRoomID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getUsers() {
		return users;
	}

	public void setUsers(List<String> users) {
		this.users = users;
	}
	
	
}
