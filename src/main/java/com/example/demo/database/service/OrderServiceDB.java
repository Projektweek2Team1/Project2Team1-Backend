package com.example.demo.database.service;

import com.example.demo.Order;
import com.example.demo.Product;
import com.example.demo.database.util.DBConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceDB {
    private Connection connection;
    private String schema;

    public OrderServiceDB() {
        this.connection = DBConnectionService.getDbConnection();
        this.schema = DBConnectionService.getSchema();
    }

    public void addOrder(Order order) {
        String query = String.format("insert into %s.orders (\"email\",\"date\",\"itemIds\") values (?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, order.getEmail());
            statement.setTimestamp(2, order.getDate());
            statement.setArray(3, connection.createArrayOf("int", order.getItemIds()));
            statement.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Order> getAll() {
        List<Order> orders = new ArrayList<>();
        String query = String.format("SELECT * from %s.orders order by date", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String email = resultSet.getString("email");
                Timestamp date = resultSet.getTimestamp("date");
                Integer[] itemIds = (Integer[]) resultSet.getArray("itemIds").getArray();
                Order order = new Order(id, email, date, itemIds);
                orders.add(order);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return orders;
    }

    public Order get(int id) {
        String query = String.format("SELECT * from %s.orders where id = ?;", schema);
        try {
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String email = resultSet.getString("email");
                Timestamp date = resultSet.getTimestamp("date");
                Integer[] itemIds = (Integer[]) resultSet.getArray("itemIds").getArray();
                Order order = new Order(id, email, date, itemIds);
                return order;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public void delete(int id) {
        String query = String.format("delete from %s.orders where id = ?", schema);
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
