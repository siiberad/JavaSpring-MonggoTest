package com.monggovest.MonggoVestBackEnd.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "province")
public class ProvinceModel {

    @Id
    @NotNull
    private Long provinceId;

    @NotNull
    @JsonIgnore
    private String provinceName;

    public ProvinceModel() {}

    @JsonCreator
    public ProvinceModel(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
