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
        String query = String.format("insert into %s.orders (\"email\",\"date\",\"chassis_id\",\"motherboard_id\",\"cpu_id\",\"gpu_id\",\"RAM_id\",\"SSD_id\",\"HDD_id\",\"batterij_id\",\"os_id\") values (?,?,?,?,?,?,?,?,?,?,?)", schema);
        try {
            PreparedStatement statement = getConnection().prepareStatement(query);
            statement.setString(1, order.getEmail());
            statement.setDate(2, order.getDate());
            statement.setInt(3, order.getChassisId());
            statement.setInt(4, order.getMotherboardId());
            statement.setInt(5, order.getCpuId());
            statement.setInt(6, order.getGpuId());
            statement.setInt(7, order.getRamId());
            statement.setInt(8, order.getSsdId());
            statement.setInt(9, order.getHddId());
            statement.setInt(10, order.getBatteryId());
            statement.setInt(11, order.getOsId());
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
                Date date = resultSet.getDate("date");
                int chassisId = resultSet.getInt("chassis_id");
                int motherboardId = resultSet.getInt("motherboard_id");
                int cpuId = resultSet.getInt("cpu_id");
                int gpuId = resultSet.getInt("gpu_id");
                int ramId = resultSet.getInt("RAM_id");
                int ssdId = resultSet.getInt("SSD_id");
                int hddId = resultSet.getInt("HDD_id");
                int batteryId = resultSet.getInt("batterij_id");
                int osId = resultSet.getInt("os_id");
                Order order = new Order(id, email, date, chassisId, motherboardId, cpuId, gpuId, ramId, ssdId, hddId, batteryId, osId);
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
                Date date = resultSet.getDate("date");
                int chassisId = resultSet.getInt("chassis_id");
                int motherboardId = resultSet.getInt("motherboard_id");
                int cpuId = resultSet.getInt("cpu_id");
                int gpuId = resultSet.getInt("gpu_id");
                int ramId = resultSet.getInt("RAM_id");
                int ssdId = resultSet.getInt("SSD_id");
                int hddId = resultSet.getInt("HDD_id");
                int batteryId = resultSet.getInt("batterij_id");
                int osId = resultSet.getInt("os_id");
                Order order = new Order(id, email, date, chassisId, motherboardId, cpuId, gpuId, ramId, ssdId, hddId, batteryId, osId);
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
