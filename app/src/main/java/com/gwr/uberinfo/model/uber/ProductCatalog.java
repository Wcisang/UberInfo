package com.gwr.uberinfo.model.uber;

import java.util.List;

/**
 * Created by willi on 14/08/2016.
 */
public class ProductCatalog {

        List<Product> products;

    public List<Product> getList() {
        return products;
    }

    public void setList(List<Product> list) {
        this.products = list;
    }
}
