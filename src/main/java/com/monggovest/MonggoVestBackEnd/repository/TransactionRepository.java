package com.monggovest.MonggoVestBackEnd.repository;


import com.monggovest.MonggoVestBackEnd.model.TransactionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionModel, Long> {

}

