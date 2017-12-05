package com.vvv.manool.warehouse;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tb_dvl on 04.12.2017.
 */

public class ModelSku {
    String key,orderKey,art,name,barcode,picLink;
    int plan,fact;
    double price;

    public ModelSku(){

    }

    public ModelSku(String key, String orderKey, String art, String name, String barcode, String picLink, int plan, int fact, double price) {

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

    public Map<String,Object> toMap(){
        HashMap<String,Object> result=new HashMap<>();
        result.put("key",key);
        result.put("art",art);
        result.put("orderKey",orderKey);
        result.put("name",name);
        result.put("barcode",barcode);
        result.put("plan",plan);
        result.put("fact",fact);
        result.put("price",price);
        return result;
    }
}
