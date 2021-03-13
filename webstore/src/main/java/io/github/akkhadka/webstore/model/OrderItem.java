package io.github.akkhadka.webstore.model;

public class OrderItem {
    private String name;
    private Cart cart;
    private String shipTo;
    private String addLine;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public String getShipTo() {
        return shipTo;
    }

    public void setShipTo(String shipTo) {
        this.shipTo = shipTo;
    }

    public String getAddLine() {
        return addLine;
    }

    public void setAddLine(String addLine) {
        this.addLine = addLine;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getDayDelivery() {
        return dayDelivery;
    }

    public void setDayDelivery(String dayDelivery) {
        this.dayDelivery = dayDelivery;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String Country;
    private String dayDelivery;
    private String userId;

    public OrderItem(String name, Cart cart, String shipTo, String addLine, String country, String dayDelivery, String userId) {
        this.name = name;
        this.cart = cart;
        this.shipTo = shipTo;
        this.addLine = addLine;
        Country = country;
        this.dayDelivery = dayDelivery;
        this.userId = userId;
    }
}
