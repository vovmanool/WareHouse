package com.vvv.manool.warehouse;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyListRecycleAdaper extends RecyclerView.Adapter<MyListRecycleAdaper.ListViewHolder> {

    private List<ListModel> list;

    public MyListRecycleAdaper(List<ListModel> list) {
        this.list = list;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_list,parent,false));
    }

    @Override
    public void onBindViewHolder(final ListViewHolder holder, int position) {

        ListModel order = list.get(position);
        holder.tvListOrder.setText(order.orderName+"");
        holder.tvListTotal.setText(order.orderTotal+"");
        holder.tvListGet.setText(order.orderGet+"");

        holder.itemView.setOnCreateContextMenuListener(new View.OnCreateContextMenuListener() {
            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(holder.getAdapterPosition(), 0, 0, "Удалить заказ");

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
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
