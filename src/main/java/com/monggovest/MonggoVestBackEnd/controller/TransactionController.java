package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.TransactionModel;

import com.monggovest.MonggoVestBackEnd.model.UserModel;
import com.monggovest.MonggoVestBackEnd.repository.TransactionRepository;
import com.monggovest.MonggoVestBackEnd.repository.UserRepository;

import com.monggovest.MonggoVestBackEnd.utils.UtilsRandomNumber;
import com.monggovest.MonggoVestBackEnd.utils.UtilsDate;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

@RestController
public class TransactionController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    UtilsRandomNumber utilsRandomNumber;

    @Autowired
    UtilsDate utilsDate;

    @Autowired
    private JavaMailSender javaMailSender;


    @GetMapping(path = "/Investors/{userId}")
    public Optional<UserModel> findAll (@PathVariable("userId") Long userId) {
        return userRepository.findById(userId);
    }
    @GetMapping(path = "/Investors/{userId}/transactions/{trxId}")
    public Optional<TransactionModel> findTrx(@PathVariable("userId") Long userId,
                                              @PathVariable("trxId") Long trxId) {
        return transactionRepository.findById(trxId);
    }

    @PostMapping("/Investors/{userId}/transactions")
    public TransactionModel createTransaction(@PathVariable (value = "userId") Long userId,
                                              @Valid @RequestBody TransactionModel model){
        return userRepository.findById(userId).map(transaction -> {
                    model.setUserModel(transaction);
                    model.setTrxInvoiceNum(utilsDate.generateDateInvoice()+ utilsRandomNumber.generateTrxInvoiceNum(6));


                    return transactionRepository.save(model);
                }
        ).orElseThrow(() -> new ResourceNotFoundException("User ID " + userId + " not found"));
    }
}