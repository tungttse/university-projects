package io.github.akkhadka.webstore.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    public List<LineItem> getLineItems() {
        return Collections.unmodifiableList(lineItems);
    }

    private final List<LineItem> lineItems = new ArrayList<>();

    public Cart() {

    }

    private String userId;

    public void addItem(Product product, int quantity) {
        LineItem line = lineItems.stream().filter(x -> x.getProduct().getProductId() == product.getProductId()).findFirst().orElse(null);
        if (line == null) {
            lineItems.add(new LineItem(product, quantity));
        } else {
            line.setQuantity(line.getQuantity() + 1);
        }
    }

    public void setItem(Product product, int quantity) {
        LineItem line = lineItems.stream().filter(x -> x.getProduct().getProductId() == product.getProductId()).findFirst().orElse(null);
        if (line == null) {
            lineItems.add(new LineItem(product, quantity));
        } else {
            line.setQuantity(quantity);
        }
    }

    public void removeLine(Product product) {
        lineItems.removeIf(x -> x.getProduct().getProductId() == product.getProductId());
    }

    public int getTotalItems() {
        return lineItems.stream().mapToInt(x -> x.getQuantity()).sum();
    }

    public double getTotalPrice() {
        double total = 0.00;
        for (var item : lineItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }
}
