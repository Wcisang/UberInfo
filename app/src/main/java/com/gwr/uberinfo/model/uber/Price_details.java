package com.gwr.uberinfo.model.uber;

/**
 * Created by willi on 14/08/2016.
 */
public class Price_details {

    String distance_unit;
    float cost_per_minute;
    float minimum;
    float cost_per_distance;
    float base;
    float cancellation_fee;
    String currency_code;


    public String getDistance_unit() {
        return distance_unit;
    }

    public void setDistance_unit(String distance_unit) {
        this.distance_unit = distance_unit;
    }

    public float getCost_per_minute() {
        return cost_per_minute;
    }

    public void setCost_per_minute(float cost_per_minute) {
        this.cost_per_minute = cost_per_minute;
    }

    public float getMinimum() {
        return minimum;
    }

    public void setMinimum(float minimum) {
        this.minimum = minimum;
    }

    public float getCost_per_distance() {
        return cost_per_distance;
    }

    public void setCost_per_distance(float cost_per_distance) {
        this.cost_per_distance = cost_per_distance;
    }

    public float getBase() {
        return base;
    }

    public void setBase(float base) {
        this.base = base;
    }

    public float getCancellation_fee() {
        return cancellation_fee;
    }

    public void setCancellation_fee(float cancellation_fee) {
        this.cancellation_fee = cancellation_fee;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }
}
