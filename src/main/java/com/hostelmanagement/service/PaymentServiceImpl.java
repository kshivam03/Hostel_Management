package com.hostelmanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hostelmanagement.model.Payment;
import com.hostelmanagement.repository.PaymentRepository;


@Service
public class PaymentServiceImpl implements PaymentService {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public List<Payment> getAllPayments() {
		return paymentRepository.findAll();
	}
	
	@Override
	public void savePayment(Payment payment) {
		this.paymentRepository.save(payment);
	}
	
	@Override
	public Payment getPaymentById(long payment_id) {
		Optional<Payment> optional=paymentRepository.findById(payment_id);
		
		Payment payment=null;
		
		if(optional.isPresent()) {
			payment=optional.get();
		}
		else {
			throw new RuntimeException("Room not found for id :: " + payment_id);
		}
		
		return payment;
	}
	
	@Override
	public void deletePaymentById(long payment_id) {
		this.paymentRepository.deleteById(payment_id);
	}

}

