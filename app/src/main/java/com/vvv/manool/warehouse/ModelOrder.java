package com.vvv.manool.warehouse;

/**
 * Created by tb_dvl on 03.12.2017.
 */

public class ModelOrder {
    String key;
    int orderName,orderTotal,orderGet;

    public ModelOrder(){

    }

    public ModelOrder(String key, int orderName, int orderTotal, int orderGet) {
        this.key = key;
        this.orderName = orderName;
        this.orderTotal = orderTotal;
        this.orderGet = orderGet;
    }
}
