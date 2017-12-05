package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by tb_dvl on 04.12.2017.
 */

public class ActivitySkusAdapter extends RecyclerView.Adapter<ActivitySkusAdapter.SkuViewHolder>{

    private List<ModelSku> skus;

    public ActivitySkusAdapter(List<ModelSku> list){
        this.skus=list;
    }

    @Override
    public SkuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SkuViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_sku,parent,false));
    }

    @Override
    public void onBindViewHolder(final SkuViewHolder holder, int position) {

        ModelSku sku = skus.get(position);

        holder.tvSkuArt.setText(sku.art+"");
        holder.tvSkuName.setText(sku.name+"");
        holder.tvSkuBarcode.setText(sku.barcode+"");
        holder.tvSkuPlan.setText(sku.plan +"");
        holder.tvSkuFact.setText(sku.fact+"");


    }

    @Override
    public int getItemCount() {
        return skus.size();
    }

    class SkuViewHolder extends RecyclerView.ViewHolder{

        TextView tvSkuArt,tvSkuName,tvSkuBarcode,tvSkuPlan,tvSkuFact;

        public SkuViewHolder(View itemView) {
            super(itemView);

            tvSkuArt = (TextView)itemView.findViewById(R.id.tv_as_art);
            tvSkuName = (TextView)itemView.findViewById(R.id.tv_as_name);
            tvSkuBarcode = (TextView)itemView.findViewById(R.id.tv_as_barcode);
            tvSkuPlan = (TextView)itemView.findViewById(R.id.tv_as_plan);
            tvSkuFact = (TextView)itemView.findViewById(R.id.tv_as_fact);

        }
    }

}
