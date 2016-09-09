package com.gwr.uberinfo.model.uber;

import java.util.List;

/**
 * Created by willi on 14/08/2016.
 */
public class ProductsTimes {

    List<Time> times;
    private String classTester;

    public List<Time> getTimes() {
        return times;
    }

    public void setTimes(List<Time> times) {
        this.times = times;
    }

    public String getClassTester() {
        return classTester;
    }
    public void setClassTester(String classTester) {
        this.classTester = classTester;
    }
}
