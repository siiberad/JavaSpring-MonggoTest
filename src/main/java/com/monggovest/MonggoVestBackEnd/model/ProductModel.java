package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "products")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = false)
public class ProductModel extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @NotNull
    @Size(max = 100)
    private String productName;

    @NotNull
    @Size(max = 250)
    private String productPrice;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "provinceId")
    @JsonProperty
    private ProvinceModel provinceModel;

    public ProductModel() {}
    @JsonCreator
    public ProductModel(Long productId) {
        this.productId = productId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public ProvinceModel getProvinceModel() {
        return provinceModel;
    }

    public void setProvinceModel(ProvinceModel provinceModel) {
        this.provinceModel = provinceModel;
    }
}

