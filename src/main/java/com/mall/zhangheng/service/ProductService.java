package com.mall.zhangheng.service;

import com.mall.zhangheng.domain.Product;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

}
