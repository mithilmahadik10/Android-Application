package com.example.wms3;

public class food {
    String Fooditems, Quantity, Time, Address;

    public food(){}

    public food(String fooditems, String quantity, String time, String address) {
        Fooditems = fooditems;
        Quantity = quantity;
        Time = time;
        Address = address;
    }

    public String getFooditems() {
        return Fooditems;
    }

    public void setFooditems(String fooditems) {
        Fooditems = fooditems;
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
