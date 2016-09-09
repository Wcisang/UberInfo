package com.gwr.uberinfo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwr.uberinfo.R;
import com.gwr.uberinfo.model.uber.Price_details;
import com.gwr.uberinfo.model.uber.Product;
import com.gwr.uberinfo.model.uber.Time;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by willi on 14/08/2016.
 */
public class ProductListExpandable extends BaseExpandableListAdapter {

    private Context context;
    private List<Time> timeList;
    private List<Product> productList;

    public ProductListExpandable(Context context, List<Time> times, List<Product> products) {
        this.context = context;
        this.timeList = times;
        this.productList = products;
    }

    @Override
    public int getGroupCount() {
        return timeList.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return 1;
    }

    @Override
    public Object getGroup(int i) {
        return timeList.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        return timeList.get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.head_item_exp_product, null);
        }
        Time time = timeList.get(i);
        Product product = getProduct(time.getProduct_id());

        ImageView img = (ImageView) convertView.findViewById(R.id.ivCarItemExp);
        TextView txtNomeUber = (TextView) convertView.findViewById(R.id.tvUberCarName);
        TextView txtEstimate = (TextView) convertView.findViewById(R.id.tvUberCarEstimate);

        Picasso.with(context).load(product.getImage()).into(img);
        txtNomeUber.setText(time.getDisplay_name());
        txtEstimate.setText(time.getEstimate()/60 +" min");
        return convertView;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View convertView, ViewGroup viewGroup) {

        if (convertView == null) {
            LayoutInflater inf = (LayoutInflater) context
                    .getSystemService(context.LAYOUT_INFLATER_SERVICE);
            convertView = inf.inflate(R.layout.child_item_exp_product, null);
        }
        Time time = timeList.get(i);
        Product product = getProduct(time.getProduct_id());

        TextView txtcapacidade = (TextView) convertView.findViewById(R.id.child_item_capacidade);
        TextView txtDescricao = (TextView) convertView.findViewById(R.id.child_item_desc);
        TextView txtCompart = (TextView) convertView.findViewById(R.id.child_item_compartilhado);
        TextView txtCustoMinuto = (TextView) convertView.findViewById(R.id.tvCustoMinuto);
        TextView txtCustoMinimo = (TextView) convertView.findViewById(R.id.tvCustoMinimo);
        TextView txtCustoDistanciaLabel = (TextView) convertView.findViewById(R.id.tvCustoDistanciaLab);
        TextView txtCustoDistancia = (TextView) convertView.findViewById(R.id.tvCustoDistancia);
        TextView txtCustoBase = (TextView) convertView.findViewById(R.id.tvCustoBase);
        TextView txtCustoCancelamento = (TextView) convertView.findViewById(R.id.tvCustoCancelamento);

        Price_details p = product.getPrice_details();

        txtcapacidade.setText(String.valueOf(product.getCapacity()));
        txtDescricao.setText(product.getDescription());
        txtCompart.setText(product.isShared()?"Sim":"Não");
        txtCustoMinuto.setText(String.valueOf(p.getCost_per_minute()));
        txtCustoMinimo.setText(String.valueOf(p.getMinimum()));
        txtCustoDistanciaLabel.setText("Custo(R$/"+p.getDistance_unit()+"): ");
        txtCustoDistancia.setText(String.valueOf(p.getCost_per_distance()));
        txtCustoBase.setText(String.valueOf(p.getBase()));
        txtCustoCancelamento.setText(String.valueOf(p.getCancellation_fee()));


        return convertView;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }


    public Product getProduct(String product_id){

        for (Product p : productList){
            if(p.getProduct_id().equals(product_id)){
                return p;
            }
        }
        return null;
    }
}
