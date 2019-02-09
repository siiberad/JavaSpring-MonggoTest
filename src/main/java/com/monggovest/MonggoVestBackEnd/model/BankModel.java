package com.monggovest.MonggoVestBackEnd.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;


@Entity
@Table(name = "bank_model")
public class BankModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long bankId;

    private String bankName;

    public BankModel() {}

    @JsonCreator
    public BankModel(Long bankId) {
        this.bankId = bankId;
    }

    public Long getBankId() {
        return bankId;
    }

    public void setBankId(Long bankId) {
        this.bankId = bankId;
    }

    @JsonProperty
    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

}
