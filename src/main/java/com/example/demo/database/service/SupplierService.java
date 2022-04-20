package com.example.demo.database.service;

import com.example.demo.Product;
import com.example.demo.Supplier;

import java.util.List;

public class SupplierService {
    private SupplierServiceDB suppliers = new SupplierServiceDB();

    public Supplier get(int id) {
        return suppliers.get(id);
    }

    public List<Supplier> getAll() {
        return suppliers.getAll();
    }

    public void add(Supplier supplier) {
        suppliers.addSupplier(supplier);
    }

    public void delete(int id) {
        suppliers.delete(id);
    }
}
