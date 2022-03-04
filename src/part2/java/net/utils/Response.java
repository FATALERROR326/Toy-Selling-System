package net.utils;

public class Response {
    public int state;
    public double price;
    public int stock;
    public Response(int state) {
        this.state = state;
    }

    public Response(double price) {
        this.price = price;
    }

    public Response(int state, double price, int stock) {
        this.state = state;
        this.price = price;
        this.stock = stock;
    }
}
