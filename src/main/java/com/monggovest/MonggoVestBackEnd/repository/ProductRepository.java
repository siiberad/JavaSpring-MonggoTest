package com.monggovest.MonggoVestBackEnd.repository;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource
public interface ProductRepository extends JpaRepository<ProductModel, Long> {
 }
