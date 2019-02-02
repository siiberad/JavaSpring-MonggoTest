package com.monggovest.MonggoVestBackEnd.controller;

import com.monggovest.MonggoVestBackEnd.model.ProductModel;
import com.monggovest.MonggoVestBackEnd.repository.ProductRepository;
import javassist.tools.web.BadHttpRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

//    @Autowired
//    private ProvinceRepository provinceRepository;

    @GetMapping //(path = "/{productName}")
    public Iterable<ProductModel> getAll(){
        return productRepository.findAll();
    }

    @GetMapping("/{productId}")
    public Optional<ProductModel>find(@PathVariable("productName") Long productId){
        return productRepository.findById(productId);
    }
    @ResponseStatus(HttpStatus.OK)
    @PostMapping(consumes="application/json")
    public ProductModel create(@RequestBody ProductModel model){
        return productRepository.save(model);
    }

    @DeleteMapping (path = "/{productId}")
    public void delete(@PathVariable("productId") Long productId) {productRepository.deleteById(productId);}

    @PutMapping("/{productId}")
    public ProductModel update(@PathVariable("productId") Long productId,
                               @RequestBody ProductModel model) throws BadHttpRequest{
        if (productRepository.existsById(productId)){
            model.setProductId(productId);
            return productRepository.save(model);
        }
        else {
            throw new BadHttpRequest();
        }
    }


}
