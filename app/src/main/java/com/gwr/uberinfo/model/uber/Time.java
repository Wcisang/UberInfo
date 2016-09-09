package com.gwr.uberinfo.model.uber;

/**
 * Created by willi on 14/08/2016.
 */
public class Time {

    String localized_display_name;
    int estimate;
    String display_name;
    String product_id;


    public String getLocalized_display_name() {
        return localized_display_name;
    }

    public void setLocalized_display_name(String localized_display_name) {
        this.localized_display_name = localized_display_name;
    }

    public int getEstimate() {
        return estimate;
    }

    public void setEstimate(int estimate) {
        this.estimate = estimate;
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
}
