package com.gwr.uberinfo.network;

import com.gwr.uberinfo.model.taxi.TaxiPrice;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by willi on 17/08/2016.
 */
public interface TaxiApiService {

    final String SERVER_TOKEN = "musTac3SUFr7";

    public final String BASE_URL = "https://api.taxifarefinder.com/";

    @GET("fare")
    Call<TaxiPrice> getTaxiPrice(@QueryMap Map<String,String> parameters);
}
