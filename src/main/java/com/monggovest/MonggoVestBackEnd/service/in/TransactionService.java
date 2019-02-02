package com.monggovest.MonggoVestBackEnd.service.in;


import com.monggovest.MonggoVestBackEnd.model.TransactionModel;

public interface TransactionService {

	public void sendOrderConfirmation(TransactionModel transactionModel);
	
}
