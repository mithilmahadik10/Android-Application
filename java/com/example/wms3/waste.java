package com.example.wms3;

public class waste {
    String  Quantity, Time, Address;

    public waste(){}

    public waste(String quantity, String time, String address) {

        Quantity = quantity;
        Time = time;
        Address = address;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
