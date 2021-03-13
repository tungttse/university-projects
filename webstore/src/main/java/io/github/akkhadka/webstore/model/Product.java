package io.github.akkhadka.webstore.model;

public class Product {
    private int productId ;
    private String name ;
    private String description ;
    private double price ;

    public Product(){

    }

    public Product(int id, String name, String description, double price) {
        this.productId=id;
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }

    public boolean isValid(){
        if (this.getName() == null || this.getName().length() == 0){
            return false;
        }

        if (this.getDescription() == null || this.getDescription().length() == 0){
            return false;
        }

        if (this.getPrice() < 0.01){
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;
        return name != null ? name.equals(product.name) : product.name == null;
    }

}
