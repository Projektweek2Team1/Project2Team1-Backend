package com.example.demo.database.service;

public class AppService {

    private final ProductService productService;
    private final SupplierService supplierService;

    public AppService() {
        this.productService = new ProductService();
        this.supplierService = new SupplierService();
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }

    public ProductService getProductService() {
        return productService;
    }
}
