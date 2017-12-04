package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ActivityOrdersAdaper extends RecyclerView.Adapter<ActivityOrdersAdaper.ListViewHolder> {

    private List<ModelOrder> orders;

    public ActivityOrdersAdaper(List<ModelOrder> list) {
        this.orders = list;
    }

    @Override
    public ActivityOrdersAdaper.ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_order,parent,false));
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {

        ModelOrder order = orders.get(position);
        holder.tvListOrder.setText(order.orderName+"");
        holder.tvListTotal.setText(order.orderTotal+"");
        holder.tvListGet.setText(order.orderGet+"");

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Удалить заказ");

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(view.getContext(),ActivitySkus.class);
                intent.putExtra("ORDER_KEY",orders.get(holder.getAdapterPosition()).key);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView tvListOrder,tvListTotal,tvListGet;

        public ListViewHolder(View itemView) {
            super(itemView);

            tvListOrder = (TextView)itemView.findViewById(R.id.tv_al_orderName);
            tvListTotal = (TextView)itemView.findViewById(R.id.tv_al_orderTotal);
            tvListGet = (TextView)itemView.findViewById(R.id.tv_al_orderGet);

        }
    }

}
