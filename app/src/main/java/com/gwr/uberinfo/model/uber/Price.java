package com.gwr.uberinfo.model.uber;

/**
 * Created by willi on 17/08/2016.
 */
public class Price {

    String product_id;
    String currency_code;
    String display_name;
    String estimate;
    String low_estimate;
    String high_estimate;
    String surge_multiplier;
    String duration;
    String distance;


    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getCurrency_code() {
        return currency_code;
    }

    public void setCurrency_code(String currency_code) {
        this.currency_code = currency_code;
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public String getLow_estimate() {
        return low_estimate;
    }

    public void setLow_estimate(String low_estimate) {
        this.low_estimate = low_estimate;
    }

    public String getHigh_estimate() {
        return high_estimate;
    }

    public void setHigh_estimate(String high_estimate) {
        this.high_estimate = high_estimate;
    }

    public String getSurge_multiplier() {
        return surge_multiplier;
    }

    public void setSurge_multiplier(String surge_multiplier) {
        this.surge_multiplier = surge_multiplier;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }
}
