package io.github.akkhadka.webstore.model;

public class LineItem {
    private int cartLineId;

    public LineItem(Product product, int quantity) {
        this.cartLineId = cartLineId;
        this.product = product;
        this.quantity = quantity;
    }

    public int getCartLineId() {
        return cartLineId;
    }

    public Product getProduct() {
        return product;
    }


    public int getQuantity() {
        return quantity;
    }

    private Product product;

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    private int quantity;
}
