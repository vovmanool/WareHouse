package com.vvv.manool.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ActivityOrders extends AppCompatActivity {

private RecyclerView recyclerView;
private List<ModelOrder> resultList;
private ActivityOrdersAdaper adapterList;
private FirebaseDatabase database;
private DatabaseReference ref_orders;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        database=FirebaseDatabase.getInstance();
        ref_orders =database.getReference("orders");

        resultList=new ArrayList<>();

        recyclerView=findViewById(R.id.recycleViewList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin=new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);

//        createRes();

        adapterList=new ActivityOrdersAdaper(resultList);
        recyclerView.setAdapter(adapterList);

        updateList();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        removeOrder(item.getGroupId());
        return super.onContextItemSelected(item);
    }

//    private void createRes(){
//        for (int i = 0; i < 10; i++) {
//            resultList.add(new ModelOrder("123","5","0",""));
//        }
//
//    }

    private void updateList(){
        ref_orders.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                resultList.add(dataSnapshot.getValue(ModelOrder.class));
                adapterList.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ModelOrder model = dataSnapshot.getValue(ModelOrder.class);
                int index=getItemIndex(model);
                resultList.set(index,model);
                adapterList.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ModelOrder model = dataSnapshot.getValue(ModelOrder.class);
                int index=getItemIndex(model);
                resultList.remove(index);
                adapterList.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }



    private int getItemIndex(ModelOrder list){
        int index = -1;
        for (int i = 0; i <resultList.size(); i++) {
            if(resultList.get(i).key.equals(list.key)){
                index=i;
                break;
            }
        }
        return index;
    }

    private void removeOrder(int position){
        ref_orders.child(resultList.get(position).key).removeValue();

    }

}
