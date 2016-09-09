package com.gwr.uberinfo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.gwr.uberinfo.R;
import com.gwr.uberinfo.adapter.ProductListExpandable;
import com.gwr.uberinfo.model.uber.Product;
import com.gwr.uberinfo.model.uber.Time;

import java.util.List;

/**
 * Created by willi on 14/08/2016.
 */
public class UberProductsDialog extends Dialog {

    List<Time> times;
    List<Product> products;
    Context context;
    public UberProductsDialog(Context context, List<Time> times, List<Product> products) {
        super(context);
        this.times = times;
        this.products = products;
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.uber_product_dialog);
        ExpandableListView exp = (ExpandableListView) findViewById(R.id.elvProdutos);
        exp.setAdapter(new ProductListExpandable(context,times,products));

    }
}
