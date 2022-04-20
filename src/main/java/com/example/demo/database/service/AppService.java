package com.example.demo.database.service;

public class AppService {

    private final ProductServiceDB productService;
    private final SupplierService supplierService;

    public AppService() {
        this.productService = new ProductServiceDB();
        this.supplierService = new SupplierService();
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }

    public ProductServiceDB getProductService() {
        return productService;
    }
}
