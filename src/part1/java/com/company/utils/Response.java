package com.company.utils;
public class Response {
    private int state;
    private double price;

    public Response(int state) {
        this.state = state;
    }

    public Response(double price) {
        this.price = price;
    }

    public Response(int state, double price) {
        this.state = state;
        this.price = price;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
