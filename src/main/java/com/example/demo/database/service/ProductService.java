package com.example.demo.database.service;

import com.example.demo.Product;

import java.util.List;

public class ProductService {
    private ProductServiceDB products = new ProductServiceDB();

    public Product get(int id) {
        return products.get(id);
    }

    public List<Product> getAll() {
        return products.getAll();
    }

    public void add(Product product) {
        products.addProduct(product);
    }

    public void delete(int id) {
        products.delete(id);
    }
}
