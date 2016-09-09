package com.gwr.uberinfo;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Slide;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.view.animation.Transformation;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.gwr.uberinfo.dialog.PriceListDialog;
import com.gwr.uberinfo.dialog.UberProductsDialog;
import com.gwr.uberinfo.model.maps.RoutesList;
import com.gwr.uberinfo.model.maps.Step;
import com.gwr.uberinfo.model.taxi.TaxiPrice;
import com.gwr.uberinfo.model.uber.Price;
import com.gwr.uberinfo.model.uber.PriceList;
import com.gwr.uberinfo.model.uber.Product;
import com.gwr.uberinfo.model.uber.ProductCatalog;
import com.gwr.uberinfo.model.uber.ProductsTimes;
import com.gwr.uberinfo.model.uber.Time;
import com.gwr.uberinfo.network.CallConnectionMaps;
import com.gwr.uberinfo.network.CallConnectionTaxi;
import com.gwr.uberinfo.network.CallConnectionUber;
import com.gwr.uberinfo.utility.GeoLocationUtility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class UberLocationActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleApiClient.ConnectionCallbacks,
        LocationListener {


    FloatingActionButton fab;
    GoogleMap googleMap;
    private GoogleApiClient mGoogleApiClient;
    private LocationRequest locationRequest;
    private Location mLastLocation;
    Marker marker;
    List<Time> list;
    List<Product> productList;
    ImageView imgFormDown;
    LinearLayout llForm;
    LinearLayout llToolbar;
    RelativeLayout llUberLocation;
    EditText edtInicioRua;
    EditText edtFimRua;
    Button btnCalcular;
    Polyline polyline;
    TaxiPrice taxiPrice;
    List<Price> uberPrices;
    FloatingActionButton fbPrice;
    Animation animation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            Slide slide = new Slide();
            slide.setDuration(3000);
            Fade fade = new Fade();
            fade.setDuration(3000);

            getWindow().setExitTransition(slide);
            getWindow().setReenterTransition(fade);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uber_location);
        fab = (FloatingActionButton) findViewById(R.id.fbUber);
        fbPrice = (FloatingActionButton) findViewById(R.id.fbPrice);
        imgFormDown = (ImageView) findViewById(R.id.ivFormDown);
        llForm = (LinearLayout) findViewById(R.id.llFormularioBusca);
        llToolbar = (LinearLayout) findViewById(R.id.llToolbar);
        llUberLocation = (RelativeLayout) findViewById(R.id.llUberLocation);
        edtFimRua = (EditText) findViewById(R.id.edtFimRua);
        edtInicioRua = (EditText) findViewById(R.id.edtInicioRua);
        btnCalcular = (Button) findViewById(R.id.btnCalcularRota);

        fbPrice.hide();
        animation = AnimationUtils.loadAnimation(this,R.anim.show_fab);
        productList = new ArrayList<>();
        list = new ArrayList<>();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        callConnection();

        EventBus.getDefault().register(UberLocationActivity.this);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!list.isEmpty() || !productList.isEmpty()) {
                    UberProductsDialog uber = new UberProductsDialog(UberLocationActivity.this,
                            list, productList);
                    uber.show();
                } else {
                    Toast.makeText(UberLocationActivity.this, "SEM UBER PROXIMO", Toast.LENGTH_SHORT).show();
                }
            }
        });


        imgFormDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (llForm.getVisibility() == View.GONE) {
                    expand(llForm);
                    //llForm.setVisibility(View.VISIBLE);
                    //llForm.animate()
                    //      .alpha(1.0f)
                    //    .translationY(llToolbar.getHeight())
                    //.setListener(new AnimatorListenerAdapter() {
                    //  @Override
                    //public void onAnimationEnd(Animator animation) {
                    //      super.onAnimationEnd(animation);
                    //    }
                    //});
                } else {
                    collapse(llForm);
                    /*llForm.animate()
                            .alpha(0.0f)
                            .translationY(0)
                            .setListener(new AnimatorListenerAdapter() {
                                @Override
                                public void onAnimationEnd(Animator animation) {
                                    if(llForm.getVisibility() == View.VISIBLE){
                                        llForm.setVisibility(View.GONE);

                                    }
                                }
                            }); */

                }
            }
        });


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                taxiPrice = null;
                uberPrices = null;
                fbPrice.hide();
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                String inicio = edtInicioRua.getText().toString();
                String fim = edtFimRua.getText().toString();
                LatLng latLngInicio = null;
                LatLng latLngFim = null;
                String origem = null, destination = null;
                try {
                    latLngInicio = GeoLocationUtility.getLatLonFromAdress(UberLocationActivity.this, inicio);
                    latLngFim = GeoLocationUtility.getLatLonFromAdress(UberLocationActivity.this, fim);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    origem = URLEncoder.encode(edtInicioRua.getText().toString(), "UTF-8");
                    destination = URLEncoder.encode(edtFimRua.getText().toString(), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                callConnectionRouteList(inicio, fim);
                CallConnectionUber.getRoutePrice(latLngInicio,latLngFim);
                CallConnectionTaxi.getPriceTaxi(latLngInicio,latLngFim,"São Paulo");

            }
        });

        fbPrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PriceListDialog dialog = new PriceListDialog(UberLocationActivity.this,
                        taxiPrice,uberPrices,productList);
                dialog.show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mGoogleApiClient != null && mGoogleApiClient.isConnected()) {
            startLocationUpdate();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mGoogleApiClient != null) {
            stoplocationUpdate();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(UberLocationActivity.this);
    }

    public void callConnectionRouteList(String inicio, String fim) {
        CallConnectionMaps.getRouteList(inicio, fim);
    }


    private void stoplocationUpdate() {
        LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient, UberLocationActivity.this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;
    }

    private synchronized void callConnection() {
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addOnConnectionFailedListener(this)
                .addConnectionCallbacks(this)
                .addApi(LocationServices.API).build();

        mGoogleApiClient.connect();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Tentando conectar...", Toast.LENGTH_SHORT).show();
        callConnection();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    1);
        }

        Location l = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);

        if (l != null) {
            mLastLocation = l;
            LatLng latLng = new LatLng(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            CallConnectionUber.getProductList(latLng);
            addMarketMyPosition(latLng, "", "Estou aqui");
        }

        startLocationUpdate();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


    public void addMarketMyPosition(LatLng latLng, String title, String snippet) {
        CameraPosition cameraPosition = new CameraPosition.Builder().target(latLng).zoom(18).bearing(0).build();
        CameraUpdate cameraUpdate = CameraUpdateFactory.newCameraPosition(cameraPosition);
        marker = googleMap.addMarker(new MarkerOptions().position(latLng).title(title).snippet(snippet).draggable(false)
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_account_location_black_24dp)));
        googleMap.animateCamera(cameraUpdate);

    }

    private void startLocationUpdate() {
        initLocationRequest();
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, locationRequest, UberLocationActivity.this);
    }

    public void initLocationRequest() {
        locationRequest = new LocationRequest();
        locationRequest.setInterval(5000);
        locationRequest.setFastestInterval(2000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }

    @Override
    public void onLocationChanged(Location l) {
        LatLng latLng = new LatLng(l.getLatitude(), l.getLongitude());
        changeMyPositionLocation(latLng);
        CallConnectionUber.getEstimateTimeList(latLng, UberLocationActivity.this);

        if (productList.isEmpty()) {
            CallConnectionUber.getProductList(latLng);
        }
    }

    public void changeMyPositionLocation(LatLng latLng) {
        marker.setPosition(latLng);
    }


    @Subscribe
    public void onEvent(ProductsTimes productsTimes) {
        list = productsTimes.getTimes();
    }


    @Subscribe
    public void onEvent(ProductCatalog productCatalog) {

        if (productCatalog.getList() != null) {
            productList = productCatalog.getList();
        }
    }

    @Subscribe
    public void onEvent(RoutesList routesList) {

        if (routesList.getRoutes() != null) {

            if (routesList.getRoutes().isEmpty()) {
                Snackbar snackbar = Snackbar
                        .make(llUberLocation, "Rota não encontrada", Snackbar.LENGTH_LONG);
                snackbar.show();
            } else {

                List<Step> steps = routesList.getRoutes().get(0).getLegs().get(0).getSteps();
                List<LatLng> lines = new ArrayList<>();

                for (Step step : steps) {
                    for (LatLng p : decodePolyline(step.getPolyline().getPoints())) {
                        lines.add(p);
                    }
                }

                drawRoute(lines);




            }
        }
    }

    @Subscribe
    public void onEvent(PriceList priceList){

        uberPrices = priceList.getPrices();
        if(uberPrices != null && taxiPrice != null){
            fbPrice.show();
            fbPrice.startAnimation(animation);
        }

    }

    @Subscribe
    public void onEvent(TaxiPrice price){

        taxiPrice = price;
        if(uberPrices != null && taxiPrice != null){
            fbPrice.show();
        }
    }

    public void drawRoute(List<LatLng> latLngList) {
        PolylineOptions po;

        if (polyline == null) {
            po = new PolylineOptions();
            for (int i = 0, tam = latLngList.size(); i < tam; i++) {
                po.add(latLngList.get(i));
            }

            po.color(Color.BLACK);
            polyline = googleMap.addPolyline(po);

        } else {
            polyline.setPoints(latLngList);
        }

    }

    private List<LatLng> decodePolyline(String encoded) {

        List<LatLng> listPoints = new ArrayList<LatLng>();
        int index = 0, len = encoded.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = encoded.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            LatLng p = new LatLng((((double) lat / 1E5)), (((double) lng / 1E5)));
            Log.i("Script", "POL: LAT: " + p.latitude + " | LNG: " + p.longitude);
            listPoints.add(p);
        }
        return listPoints;
    }


    public void expand(final View v) {
        v.measure(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        final int targetHeight = v.getMeasuredHeight();

        // Older versions of android (pre API 21) cancel animations for views with a height of 0.
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    public void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();

        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };

        // 1dp/ms
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }


}
