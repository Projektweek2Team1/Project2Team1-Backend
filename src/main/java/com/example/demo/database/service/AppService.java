package com.example.demo.database.service;

public class AppService {

    private final ProductService productService;
    private final SupplierService supplierService;
    private final OrderService orderService;

    public AppService() {
        this.productService = new ProductService();
        this.supplierService = new SupplierService();
        this.orderService = new OrderService();
    }

    public SupplierService getSupplierService() {
        return supplierService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public OrderService getOrderService() {
        return orderService;
    }
}
