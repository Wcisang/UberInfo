package com.gwr.uberinfo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.gwr.uberinfo.R;
import com.gwr.uberinfo.model.uber.Price;
import com.gwr.uberinfo.model.uber.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by willi on 17/08/2016.
 */
public class PriceListAdapter extends BaseAdapter {

    List<Price> list;
    List<Product> products;
    Context c;

    public PriceListAdapter (Context c, List<Price> prices, List<Product> productList){
        list = prices;
        this.c = c;
        products = productList;
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolderItem viewHolderItem;

        if(view == null){
            LayoutInflater layoutInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.price_list_item,viewGroup,false);

            viewHolderItem = new ViewHolderItem();

            viewHolderItem.img = (ImageView) view.findViewById(R.id.ivCarPrice);
            viewHolderItem.tvName = (TextView) view.findViewById(R.id.tvPriceCarName);
            viewHolderItem.tvPreco = (TextView) view.findViewById(R.id.tvPriceCarEstimate);
            view.setTag(viewHolderItem);
        }else{
            viewHolderItem = (ViewHolderItem) view.getTag();
        }

        Price price = list.get(i);

        Product product = getProduct(price.getProduct_id());

        Picasso.with(c).load(product.getImage()).into(viewHolderItem.img);
        viewHolderItem.tvName.setText(price.getDisplay_name());
        viewHolderItem.tvPreco.setText(price.getEstimate());

        return view;
    }

    static class ViewHolderItem {
        ImageView img;
        TextView tvName;
        TextView tvPreco;
    }

    public Product getProduct(String id){

        for(Product product: products){
            if(product.getProduct_id().equals(id)){
                return product;
            }
        }

        return null;
    }

}
