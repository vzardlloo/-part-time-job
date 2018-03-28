package com.mall.zhangheng.service;

import com.mall.zhangheng.domain.Product;
import com.mall.zhangheng.dto.ResponseModel;
import com.mall.zhangheng.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> getAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }

    public List<Product> getProducts() {
        List<Product> productList = productRepository.findAll();
        List<Product> products = new LinkedList<>();
        for (int i = 0; i < 8; i++) {
            if (productList.get(i) != null) {
                products.add(productList.get(i));
            }
        }
        return products;
    }

    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    public void deleteProduct(Product product){
        productRepository.delete(product);
    }

}
