package com.gwr.uberinfo.model.maps;

import java.util.List;

/**
 * Created by willi on 17/08/2016.
 */
public class Route {

    List<Leg> legs;

    public List<Leg> getLegs() {
        return legs;
    }

    public void setLegs(List<Leg> legs) {
        this.legs = legs;
    }
}
