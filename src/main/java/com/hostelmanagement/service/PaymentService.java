package com.hostelmanagement.service;

import java.util.List;

import com.hostelmanagement.model.Payment;


public interface PaymentService {
	
	List<Payment> getAllPayments();
	
	void savePayment(Payment payment);
	
	Payment getPaymentById(long payment_id);	
	
	void deletePaymentById(long payment_id);
}
