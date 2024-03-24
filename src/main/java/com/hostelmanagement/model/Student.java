package com.hostelmanagement.model;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Student {
	@Id
	private long student_id;
	private String student_fname;
	private String student_lname;
	private String gender;
	private long contact_no;
	private String email_id;
	private Date joining_date;
	private int remaining_amount;
	@ManyToOne
	private Room room;
	@OneToMany
	private List<Payment> payment;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Student(long student_id, String student_fname, String student_lname, String gender, long contact_no,
			String email_id, Date joining_date, int remaining_amount, Room room, List<Payment> payment) {
		super();
		this.student_id = student_id;
		this.student_fname = student_fname;
		this.student_lname = student_lname;
		this.gender = gender;
		this.contact_no = contact_no;
		this.email_id = email_id;
		this.joining_date = joining_date;
		this.remaining_amount = remaining_amount;
		this.room = room;
		this.payment = payment;
	}

	public long getStudent_id() {
		return student_id;
	}

	public void setStudent_id(long student_id) {
		this.student_id = student_id;
	}

	public String getStudent_fname() {
		return student_fname;
	}

	public void setStudent_fname(String student_fname) {
		this.student_fname = student_fname;
	}

	public String getStudent_lname() {
		return student_lname;
	}

	public void setStudent_lname(String student_lname) {
		this.student_lname = student_lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public long getContact_no() {
		return contact_no;
	}

	public void setContact_no(long contact_no) {
		this.contact_no = contact_no;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}

	public int getRemaining_amount() {
		return remaining_amount;
	}

	public void setRemaining_amount(int remaining_amount) {
		this.remaining_amount = remaining_amount;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}

	@Override
	public String toString() {
		return "Student [student_id=" + student_id + ", student_fname=" + student_fname + ", student_lname="
				+ student_lname + ", gender=" + gender + ", contact_no=" + contact_no + ", email_id=" + email_id
				+ ", joining_date=" + joining_date + ", remaining_amount=" + remaining_amount + ", room=" + room
				+ ", payment=" + payment + "]";
	}

}
