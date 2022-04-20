package com.example.demo.database.service;

import com.example.demo.Supplier;
import com.example.demo.database.util.DBConnectionService;

import java.sql.*;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class SupplierServiceDB {
    private Connection connection;
    private String schema;

    public SupplierServiceDB() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    public void addSupplier(Supplier supplier) {
        String query = String.format("INSERT into %s.suppliers (\"title\",\"thumbnail_url\",\"url\") values (?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, supplier.getTitle());
            statement.setString(2, supplier.getThumbnail_url());
            statement.setString(3, supplier.getUrl());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Supplier> getAll() {
        List<Supplier> suppliers = new ArrayList<>();
        String query = String.format("select * from %s.suppliers T", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String thumbnail_url = resultSet.getString("thumbnail_url");
                String url = resultSet.getString("url");
                try {
                    Supplier supplier = new Supplier(id, title, thumbnail_url, url);
                    suppliers.add(supplier);
                } catch (IllegalArgumentException ignored) {}
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return suppliers;
    }

    public Supplier get(int id) {
        String query = String.format("SELECT * from %s.suppliers where id = ?;", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String thumbnail_url = resultSet.getString("thumbnail_url");
                String url = resultSet.getString("url");
                Supplier supplier = new Supplier(id, title, thumbnail_url, url);
                return supplier;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void delete(int id) {
        String query = String.format("DELETE from %s.suppliers where id = ?", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    /**
     * Check the connection and reconnect when necessery
     * @return the connection with the db, if there is one
     */
    private Connection getConnection() {
        checkConnection();
        return this.connection;
    }

    /**
     * Check if the connection is still open
     * When connection has been closed: reconnect
     */
    private void checkConnection() {
        try {
            if (this.connection == null || this.connection.isClosed()) {
                System.out.println("Connection has been closed");
                this.reConnect();
            }
        } catch (SQLException throwables) {
            throw new DbException(throwables.getMessage());
        }
    }

    /**
     * Reconnects application to db
     */
    private void reConnect() {
        DBConnectionService.disconnect();   // close connection with db properly
        DBConnectionService.reconnect();      // reconnect application to db server
        this.connection = DBConnectionService.getDbConnection();    // assign connection to DBSQL
    }
}
