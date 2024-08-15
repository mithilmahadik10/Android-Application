package com.example.wms3;

public class clothes {
    String  Quantity, Time, Address;

    public clothes(){}

    public clothes(String quantity, String time, String address) {

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
