package com.hostelmanagement.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Staff {
	@Id
	private long staff_id;
	private String staff_fname;
	private String staff_lname;
	private String gender;
	private String post;
	private long contact_no;
	private Date joining_date;

	public Staff() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Staff(long staff_id, String staff_fname, String staff_lname, String gender, String post, long contact_no,
			Date joining_date) {
		super();
		this.staff_id = staff_id;
		this.staff_fname = staff_fname;
		this.staff_lname = staff_lname;
		this.gender = gender;
		this.post = post;
		this.contact_no = contact_no;
		this.joining_date = joining_date;
	}

	public long getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(long staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_fname() {
		return staff_fname;
	}

	public void setStaff_fname(String staff_fname) {
		this.staff_fname = staff_fname;
	}

	public String getStaff_lname() {
		return staff_lname;
	}

	public void setStaff_lname(String staff_lname) {
		this.staff_lname = staff_lname;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public long getContact_no() {
		return contact_no;
	}

	public void setContact_no(long contact_no) {
		this.contact_no = contact_no;
	}

	public Date getJoining_date() {
		return joining_date;
	}

	public void setJoining_date(Date joining_date) {
		this.joining_date = joining_date;
	}

}
