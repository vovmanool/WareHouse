package com.vvv.manool.warehouse;

/**
 * Created by tb_dvl on 04.12.2017.
 */

public class ModelSku {
    String key,orderKey,art,name,barcode,picLink;
    int plan,fact;
    double price;

    public ModelSku(){

    }

    int a,b;
    public ModelSku(String key, String orderKey, String art, String name, String barcode, String picLink, int plan, int fact, double price) {


        a=b;
        this.key = key;
        this.orderKey = orderKey;
        this.art = art;
        this.name = name;
        this.barcode = barcode;
        this.picLink = picLink;
        this.plan = plan;
        this.fact = fact;
        this.price = price;
    }
}
