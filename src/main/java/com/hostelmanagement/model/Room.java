package com.hostelmanagement.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Room {

	@Id
	private long room_no;
	private String room_type;
	private int room_capacity;
	private int room_vacancy;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Student> student;

	public Room() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Room(long room_no, String room_type, int room_capacity, int room_vacancy, List<Student> student) {
		super();
		this.room_no = room_no;
		this.room_type = room_type;
		this.room_capacity = room_capacity;
		this.room_vacancy = room_vacancy;
		this.student = student;
	}

	public long getRoom_no() {
		return room_no;
	}

	public void setRoom_no(long room_no) {
		this.room_no = room_no;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public int getRoom_capacity() {
		return room_capacity;
	}

	public void setRoom_capacity(int room_capacity) {
		this.room_capacity = room_capacity;
	}

	public int getRoom_vacancy() {
		return room_vacancy;
	}

	public void setRoom_vacancy(int room_vacancy) {
		this.room_vacancy = room_vacancy;
	}

	public List<Student> getStudent() {
		return student;
	}

	public void setStudent(List<Student> student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Room [room_no=" + room_no + ", room_type=" + room_type + ", room_capacity=" + room_capacity
				+ ", room_vacancy=" + room_vacancy + ", student=" + student + "]";
	}

}
