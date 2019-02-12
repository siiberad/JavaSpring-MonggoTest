package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "transaction")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = false)
public class TransactionModel extends AuditModel{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long trxId;

    @Column(unique = true)
    private String trxInvoiceNum;

    @Enumerated(EnumType.ORDINAL)
    private Status status = Status.belum;

    @NotNull
    @Size(max = 20)
    private String noRekening;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "userId")
    @JsonIgnore
    private UserModel userModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "bankId")
    @JsonProperty
    private BankModel bankModel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "productId")
    @JsonProperty
    @JsonIgnoreProperties(value = {"productName","productPrice", "provinceModel"}, allowGetters = false)
    private ProductModel productModel;

    public Long getTrxId() {
        return trxId;
    }

    public void setTrxId(Long trxId) {
        this.trxId = trxId;
    }

    public String getTrxInvoiceNum() {
        return trxInvoiceNum;
    }

    public void setTrxInvoiceNum(String trxInvoiceNum) {
        this.trxInvoiceNum = trxInvoiceNum;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getNoRekening() {
        return noRekening;
    }

    public void setNoRekening(String noRekening) {
        this.noRekening = noRekening;
    }

    public UserModel getUserModel() {
        return userModel;
    }

    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
    }

    public BankModel getBankModel() {
        return bankModel;
    }

    public void setBankModel(BankModel bankModel) {
        this.bankModel = bankModel;
    }

    public ProductModel getProductModel() {
        return productModel;
    }

    public void setProductModel(ProductModel productModel) {
        this.productModel = productModel;
    }

    @Override
    public String toString() {
        return "TransactionModel{" +
                "trxId=" + trxId +
                ", trxInvoiceNum='" + trxInvoiceNum + '\'' +
                ", status=" + status +
                ", noRekening='" + noRekening + '\'' +
                ", userModel=" + userModel +
                ", bankModel=" + bankModel +
                ", productModel=" + productModel +
                '}';
    }
}




