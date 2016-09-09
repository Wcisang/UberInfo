package com.gwr.uberinfo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.gwr.uberinfo.R;
import com.gwr.uberinfo.adapter.PriceListAdapter;
import com.gwr.uberinfo.model.taxi.TaxiPrice;
import com.gwr.uberinfo.model.uber.Price;
import com.gwr.uberinfo.model.uber.Product;

import java.util.List;

/**
 * Created by willi on 17/08/2016.
 */
public class PriceListDialog extends Dialog {

    Context context;
    TextView tvTaxiPrice;
    TaxiPrice taxiPrice;
    List<Price> uberPrices;
    List<Product> products;
    ListView listView;

    public PriceListDialog(Context context, TaxiPrice price, List<Price> priceList, List<Product> productList) {
        super(context);
        this.context = context;
        this.taxiPrice = price;
        this.uberPrices = priceList;
        this.products = productList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prices_dialog);
        tvTaxiPrice = (TextView) findViewById(R.id.tvPrecoTaxi);
        listView = (ListView) findViewById(R.id.lvPrecoUber);
        tvTaxiPrice.setText("R$"+taxiPrice.getTotal_fare());
        listView.setAdapter(new PriceListAdapter(context,uberPrices,products));


    }
}
