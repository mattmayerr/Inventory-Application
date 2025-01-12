package com.example.demo.service;

import com.example.demo.domain.Part;
import com.example.demo.domain.Product;

import java.util.List;

/**
 *
 *
 *
 *
 */
public interface ProductService {
    public List<Product> findAll();
    public Product findById(Long theId);
    public void save (Product theProduct);
    public void deleteById(Long theId);
    public List<Product> listAll(String keyword);

}
