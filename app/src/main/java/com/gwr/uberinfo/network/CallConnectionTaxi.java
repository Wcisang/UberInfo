package com.gwr.uberinfo.network;

import com.google.android.gms.maps.model.LatLng;
import com.gwr.uberinfo.model.taxi.TaxiPrice;
import com.gwr.uberinfo.model.uber.Price;
import com.gwr.uberinfo.model.uber.PriceList;

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
public class CallConnectionTaxi {

    public static void getPriceTaxi(LatLng inicio, LatLng fim, String entity_handle){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(TaxiApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        TaxiApiService service = retrofit.create(TaxiApiService.class);
        Map<String,String> map = new HashMap<>();
        map.put("origin",inicio.latitude+","+inicio.longitude);
        map.put("destination",fim.latitude+","+fim.longitude);
        map.put("key",TaxiApiService.SERVER_TOKEN);


        Call<TaxiPrice> call = service.getTaxiPrice(map);
        call.enqueue(new Callback<TaxiPrice>() {
            @Override
            public void onResponse(Call<TaxiPrice> call, Response<TaxiPrice> response) {
                TaxiPrice taxiPrice = response.body();
                EventBus.getDefault().post(taxiPrice);
            }

            @Override
            public void onFailure(Call<TaxiPrice> call, Throwable t) {
                EventBus.getDefault().post(new TaxiPrice());
            }
        });



    }
}
