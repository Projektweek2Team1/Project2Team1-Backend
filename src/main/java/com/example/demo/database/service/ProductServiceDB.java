package com.example.demo.database.service;

import com.example.demo.Product;
import com.example.demo.database.util.DBConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceDB {
    private Connection connection;
    private String schema;

    public ProductServiceDB() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    public void addProduct(Product product) {
        String query = String.format("insert into %s.products (\"title\",\"description\",\"thumbnail_url\",\"quantity\",\"price\",\"type\") values (?,?,?,?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, product.getTitle());
            statement.setString(2, product.getDescription());
            statement.setString(3, product.getThumbnail_url());
            statement.setInt(4, product.getQuantity());
            statement.setFloat(5, product.getPrice());
            statement.setString(6, product.getType());
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String query = String.format("SELECT * from %s.products order by type, price where quantity > 0", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String thumbnail_url = resultSet.getString("thumbnail_url");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                Product product = new Product(id, title, description, thumbnail_url, quantity, price, type);
                products.add(product);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return products;
    }

    public Product get(int id) {
        String query = String.format("SELECT * from %s.products where id = ?;", schema);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String title = resultSet.getString("title");
                String description = resultSet.getString("description");
                String thumbnail_url = resultSet.getString("thumbnail_url");
                int quantity = resultSet.getInt("quantity");
                float price = resultSet.getFloat("price");
                String type = resultSet.getString("type");
                Product product = new Product(id, title, description, thumbnail_url, quantity, price, type);
                return product;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void delete(int id) {
        String query = String.format("delete from %s.products where id = ?", schema);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void decreaseQuantity(int id) {
        String query = String.format("update %s.products set quantity = quantity - 1 where id = ? and quantity > 0", schema);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
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
