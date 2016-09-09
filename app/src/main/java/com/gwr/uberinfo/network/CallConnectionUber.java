package com.gwr.uberinfo.network;

import android.content.Context;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.gwr.uberinfo.model.uber.PriceList;
import com.gwr.uberinfo.model.uber.ProductCatalog;
import com.gwr.uberinfo.model.uber.ProductsTimes;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by willi on 14/08/2016.
 */
public class CallConnectionUber {


    public static void getEstimateTimeList(LatLng latLng, Context c){


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UberApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UberApiService uberApiService = retrofit.create(UberApiService.class);
        Map<String,String> map = new HashMap<>();
        map.put("start_latitude",String.valueOf(latLng.latitude));
        map.put("start_longitude",String.valueOf(latLng.longitude));
        map.put("server_token",UberApiService.SERVER_TOKEN);
        Call<ProductsTimes> call = uberApiService.getEstimateProdutsTimes(map);

        call.enqueue(new Callback<ProductsTimes>() {
            @Override
            public void onResponse(Call<ProductsTimes> call, Response<ProductsTimes> response) {

                if (!response.isSuccessful()) {
                    Log.i("LOGWILLIAM",response.message());
                }else{
                    Log.i("LOGWILLIAM",response.message());
                    ProductsTimes productsTimes = response.body();

                    EventBus.getDefault().post(productsTimes);

                }
            }

            @Override
            public void onFailure(Call<ProductsTimes> call, Throwable t) {
                String erro;
                erro = t.getMessage();
                Log.i("LOGWILLIAM",t.getMessage());
            }
        });


    }


    public static void getProductList (LatLng latLng){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UberApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UberApiService uberApiService = retrofit.create(UberApiService.class);
        Map<String,String> map = new HashMap<>();
        map.put("latitude",String.valueOf(latLng.latitude));
        map.put("longitude",String.valueOf(latLng.longitude));
        map.put("server_token",UberApiService.SERVER_TOKEN);
        Call<ProductCatalog> call = uberApiService.getProductList(map);

        call.enqueue(new Callback<ProductCatalog>() {
            @Override
            public void onResponse(Call<ProductCatalog> call, Response<ProductCatalog> response) {

                if (!response.isSuccessful()) {
                    Log.i("LOGWILLIAM",response.message());
                }else {
                    ProductCatalog productCatalog = response.body();
                    EventBus.getDefault().post(productCatalog);
                }
            }

            @Override
            public void onFailure(Call<ProductCatalog> call, Throwable t) {
                EventBus.getDefault().post(new ProductCatalog());
            }
        });
    }

    public static void getRoutePrice(LatLng inicio, LatLng fim){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UberApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        UberApiService service = retrofit.create(UberApiService.class);
        Map<String,String> map = new HashMap<>();
        map.put("start_latitude",String.valueOf(inicio.latitude));
        map.put("start_longitude",String.valueOf(inicio.longitude));
        map.put("end_latitude",String.valueOf(fim.latitude));
        map.put("end_longitude",String.valueOf(fim.longitude));
        map.put("server_token",UberApiService.SERVER_TOKEN);

        Call<PriceList> call = service.getRoutePrice(map);

        call.enqueue(new Callback<PriceList>() {
            @Override
            public void onResponse(Call<PriceList> call, Response<PriceList> response) {

                if(response.isSuccessful()){
                    PriceList priceList = response.body();
                    EventBus.getDefault().post(priceList);
                }
            }

            @Override
            public void onFailure(Call<PriceList> call, Throwable t) {
                EventBus.getDefault().post(new PriceList());
            }
        });

    }
}
