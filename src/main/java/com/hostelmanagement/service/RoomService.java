package com.hostelmanagement.service;

import java.util.List;

import com.hostelmanagement.model.Room;


public interface RoomService {
	
	List<Room> getAllRooms();
	
	void saveRoom(Room room);
	
	Room getRoomById(long room_no);
	
	void deleteRoomById(long room_no);
}
