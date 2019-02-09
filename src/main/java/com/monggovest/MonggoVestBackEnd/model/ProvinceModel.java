package com.monggovest.MonggoVestBackEnd.model;
import com.fasterxml.jackson.annotation.JsonCreator;
import javax.persistence.*;


@Entity
@Table(name = "province")
public class ProvinceModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY )
    private Long provinceId;

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
