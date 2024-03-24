package com.hostelmanagement.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Payment {

	@Id
	private long payment_id;
	private int amount;
	private Date payment_date;
	@ManyToOne
	private Student student;

	public Payment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Payment(long payment_id, int amount, Date payment_date, Student student) {
		super();
		this.payment_id = payment_id;
		this.amount = amount;
		this.payment_date = payment_date;
		this.student = student;
	}

	public long getPayment_id() {
		return payment_id;
	}

	public void setPayment_id(long payment_id) {
		this.payment_id = payment_id;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Date getPayment_date() {
		return payment_date;
	}

	public void setPayment_date(Date payment_date) {
		this.payment_date = payment_date;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Override
	public String toString() {
		return "Payment [payment_id=" + payment_id + ", amount=" + amount + ", payment_date=" + payment_date
				+ ", student=" + student + "]";
	}

}
