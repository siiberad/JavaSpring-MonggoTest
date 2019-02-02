package com.monggovest.MonggoVestBackEnd.repository;


import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;

@RestResource(exported = false)
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

}

