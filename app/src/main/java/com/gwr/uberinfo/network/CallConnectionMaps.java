package com.gwr.uberinfo.network;

import android.util.Log;

import com.gwr.uberinfo.model.maps.RoutesList;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by willi on 17/08/2016.
 */
public class CallConnectionMaps {

    public static void getRouteList(String origin,String destination){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(MapsApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MapsApiService service = retrofit.create(MapsApiService.class);
        Map<String,String> map = new HashMap<>();
        map.put("origin",origin);
        map.put("destination",destination);
        map.put("sensor","false");
        Call<RoutesList> call = service.getRouteDirection(map);

        call.enqueue(new Callback<RoutesList>() {
            @Override
            public void onResponse(Call<RoutesList> call, Response<RoutesList> response) {

                if (response.isSuccessful()) {
                    RoutesList routesList = response.body();
                    EventBus.getDefault().post(routesList);
                }
            }

            @Override
            public void onFailure(Call<RoutesList> call, Throwable t) {
                Log.i("LOGWILLIAM",t.getMessage());
                EventBus.getDefault().post(new RoutesList());
            }
        });

    }
}
