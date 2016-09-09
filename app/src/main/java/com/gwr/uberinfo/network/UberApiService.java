package com.gwr.uberinfo.network;

import com.gwr.uberinfo.model.uber.PriceList;
import com.gwr.uberinfo.model.uber.ProductCatalog;
import com.gwr.uberinfo.model.uber.ProductsTimes;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by willi on 14/08/2016.
 */
public interface UberApiService {

    final String SERVER_TOKEN = "oVOjACUCnr8-vEbgYjkOoVOQ8ayARSg_xpUxHY2I";

    public final String BASE_URL = "https://api.uber.com/v1/";

    @GET("estimates/time")
    Call<ProductsTimes> getEstimateProdutsTimes(@QueryMap Map<String,String> parameters);

    @GET("products")
    Call<ProductCatalog> getProductList(@QueryMap Map<String,String> parameters);

    @GET("estimates/price")
    Call<PriceList> getRoutePrice(@QueryMap Map<String,String> parameters);


}
