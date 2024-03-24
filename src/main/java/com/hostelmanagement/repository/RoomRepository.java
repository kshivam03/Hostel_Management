package com.hostelmanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hostelmanagement.model.Room;


@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

}
