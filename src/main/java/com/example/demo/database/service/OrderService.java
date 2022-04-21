package com.example.demo.database.service;

import com.example.demo.Order;
import com.example.demo.Product;

import java.util.List;

public class OrderService {
    private OrderServiceDB products = new OrderServiceDB();

    public Order get(int id) {
        return products.get(id);
    }

    public List<Order> getAll() {
        return products.getAll();
    }

    public void add(Order order) {
        products.addOrder(order);
    }

    public void delete(int id) {
        products.delete(id);
    }
}
