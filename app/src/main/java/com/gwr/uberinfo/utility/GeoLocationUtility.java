package com.gwr.uberinfo.utility;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by willi on 16/08/2016.
 */
public class GeoLocationUtility {

    public static LatLng getLatLonFromAdress(Context c, String end) throws IOException {
        Geocoder gc = new Geocoder(c);

        List<Address> addressList = gc.getFromLocationName(end,1);

        LatLng ll = new LatLng(addressList.get(0).getLatitude(), addressList.get(0).getLongitude());

        return ll;
    }

    public static String getAdressFromLatLon(Context c, LatLng latLng) throws IOException {
        Geocoder gc = new Geocoder(c);
        List<Address> addressList = gc.getFromLocation(latLng.latitude,latLng.longitude, 1);

        String address = "Rua: "+addressList.get(0).getThoroughfare()+"\n";
        address += "Cidade: "+addressList.get(0).getSubAdminArea()+"\n";
        address += "Estado: "+addressList.get(0).getAdminArea()+"\n";
        address += "Pais: "+addressList.get(0).getCountryName();

        return  address;
    }
}
