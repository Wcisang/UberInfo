package com.gwr.uberinfo.model.maps;

import java.util.List;

/**
 * Created by willi on 17/08/2016.
 */
public class RoutesList {

    List<Route> routes;
    String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Route> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }
}
