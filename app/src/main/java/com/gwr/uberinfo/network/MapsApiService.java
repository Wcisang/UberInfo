package com.gwr.uberinfo.network;

import com.gwr.uberinfo.model.maps.RoutesList;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by willi on 17/08/2016.
 */
public interface MapsApiService {

    public static final String BASE_URL = "http://maps.googleapis.com/maps/api/";


    @GET("directions/json")
    Call<RoutesList> getRouteDirection(@QueryMap Map<String,String> parameters);
}
