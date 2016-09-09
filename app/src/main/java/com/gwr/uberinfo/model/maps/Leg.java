package com.gwr.uberinfo.model.maps;

import java.util.List;

/**
 * Created by willi on 17/08/2016.
 */
public class Leg {

    Distance distance;
    Duration duration;
    List<Step> steps;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public List<Step> getSteps() {
        return steps;
    }

    public void setSteps(List<Step> steps) {
        this.steps = steps;
    }
}
