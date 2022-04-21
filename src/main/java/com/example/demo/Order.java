package com.example.demo;

import java.sql.Date;

public class Order {

    private int id;
    private String email;
    private Date date;
    private int chassisId, motherboardId, cpuId, gpuId, ramId, ssdId, hddId, batteryId, osId;

    public Order() {}

    public Order(int id, String email, Date date, int chassisId, int motherboardId, int cpuId, int gpuId, int ramId, int ssdId, int hddId, int batteryId, int osId) {
        super();
        this.id = id;
        this.email = email;
        this.date = date;
        this.chassisId = chassisId;
        this.motherboardId = motherboardId;
        this.cpuId = cpuId;
        this.gpuId = gpuId;
        this.ramId = ramId;
        this.ssdId = ssdId;
        this.hddId = hddId;
        this.batteryId = batteryId;
        this.osId = osId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getChassisId() {
        return chassisId;
    }

    public void setChassisId(int chassisId) {
        this.chassisId = chassisId;
    }

    public int getMotherboardId() {
        return motherboardId;
    }

    public void setMotherboardId(int motherboardId) {
        this.motherboardId = motherboardId;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public int getGpuId() {
        return gpuId;
    }

    public void setGpuId(int gpuId) {
        this.gpuId = gpuId;
    }

    public int getRamId() {
        return ramId;
    }

    public void setRamId(int ramId) {
        this.ramId = ramId;
    }

    public int getSsdId() {
        return ssdId;
    }

    public void setSsdId(int ssdId) {
        this.ssdId = ssdId;
    }

    public int getHddId() {
        return hddId;
    }

    public void setHddId(int hddId) {
        this.hddId = hddId;
    }

    public int getBatteryId() {
        return batteryId;
    }

    public void setBatteryId(int batteryId) {
        this.batteryId = batteryId;
    }

    public int getOsId() {
        return osId;
    }

    public void setOsId(int osId) {
        this.osId = osId;
    }

}
