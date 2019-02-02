package com.monggovest.MonggoVestBackEnd.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.*;

@Entity
@Table(name = "User")
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(max = 100)
    private String userName;

    @NotNull
    @Size(max = 45)
    @Column(unique = true)
    private String userEmail;

    @NotNull
    @Size(max = 45)
    private String userPassword;

    /*@Size(max = 20)
    private Date userBirthDate;*/

    @Size(max = 20)
    private String idCardType;

    @Size(max = 20)
    private String idCardNumber;

    @Size(max = 50)
    private String userAddress;

    @Size(max = 50)
    private String userProvince;

    private Integer userContact;

    @Size(max = 50)
    private String userIncomeSource;

    private Integer userIncome;

    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "userModel")
    private List<TransactionModel> transactionModels = new ArrayList<>();

    public List<TransactionModel> getTransactionModels() {
        return transactionModels;
    }

    public void setTransactionModels(List<TransactionModel> transactionModels) {
        this.transactionModels = transactionModels;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    /*public Date getUserBirthDate() {
        return userBirthDate;
    }

    public void setUserBirthDate(Date userBirthDate) {
        this.userBirthDate = userBirthDate;
    }*/

    public String getIdCardType() {
        return idCardType;
    }

    public void setIdCardType(String idCardType) {
        this.idCardType = idCardType;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserProvince() {
        return userProvince;
    }

    public void setUserProvince(String userProvince) {
        this.userProvince = userProvince;
    }

    public Integer getUserContact() {
        return userContact;
    }

    public void setUserContact(Integer userContact) {
        this.userContact = userContact;
    }

    public String getUserIncomeSource() {
        return userIncomeSource;
    }

    public void setUserIncomeSource(String userIncomeSource) {
        this.userIncomeSource = userIncomeSource;
    }

    public Integer getUserIncome() {
        return userIncome;
    }

    public void setUserIncome(Integer userIncome) {
        this.userIncome = userIncome;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", idCardType='" + idCardType + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userProvince='" + userProvince + '\'' +
                ", userContact=" + userContact +
                ", userIncomeSource='" + userIncomeSource + '\'' +
                ", userIncome=" + userIncome +
                ", transactionModels=" + transactionModels +
                '}';
    }
}
