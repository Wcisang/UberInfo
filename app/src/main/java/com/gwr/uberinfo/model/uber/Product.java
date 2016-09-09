package com.gwr.uberinfo.model.uber;

/**
 * Created by willi on 14/08/2016.
 */
public class Product {

    int capacity;
    String description;
    Price_details price_details;
    String image;
    String display_name;
    String product_id;
    boolean shared;

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Price_details getPrice_details() {
        return price_details;
    }

    public void setPrice_details(Price_details price_details) {
        this.price_details = price_details;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }
}
