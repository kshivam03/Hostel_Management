package com.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostelmanagement.model.Room;
import com.hostelmanagement.repository.RoomRepository;


@Service
public class RoomServiceImpl implements RoomService {
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Override
	public List<Room> getAllRooms() {
		return roomRepository.findAll();
	}
	
	@Override
	public void saveRoom(Room room) {
		this.roomRepository.save(room);
	}
	
	@Override
	public Room getRoomById(long room_no) {
		Optional<Room> optional=roomRepository.findById(room_no);
		
		Room room=null;
		
		if(optional.isPresent()) {
			room=optional.get();
		}
		else {
			throw new RuntimeException("Room not found for id :: " + room_no);
		}
		
		return room;
	}
	
	@Override
	public void deleteRoomById(long room_no) {
		this.roomRepository.deleteById(room_no);
	}
}

