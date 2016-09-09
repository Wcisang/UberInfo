package com.gwr.uberinfo.model.taxi;

/**
 * Created by willi on 17/08/2016.
 */
public class TaxiPrice {

    String status;
    float total_fare;
    float initial_fare;
    float metered_fare;
    float tip_amount;
    float tip_percentage;
    String locale;
    String rate_area;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public float getTotal_fare() {
        return total_fare;
    }

    public void setTotal_fare(float total_fare) {
        this.total_fare = total_fare;
    }

    public float getInitial_fare() {
        return initial_fare;
    }

    public void setInitial_fare(float initial_fare) {
        this.initial_fare = initial_fare;
    }

    public float getMetered_fare() {
        return metered_fare;
    }

    public void setMetered_fare(float metered_fare) {
        this.metered_fare = metered_fare;
    }

    public float getTip_amount() {
        return tip_amount;
    }

    public void setTip_amount(float tip_amount) {
        this.tip_amount = tip_amount;
    }

    public float getTip_percentage() {
        return tip_percentage;
    }

    public void setTip_percentage(float tip_percentage) {
        this.tip_percentage = tip_percentage;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getRate_area() {
        return rate_area;
    }

    public void setRate_area(String rate_area) {
        this.rate_area = rate_area;
    }
}
