package com.vvv.manool.warehouse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class ActivitySkus extends AppCompatActivity {
    private static final String TAG = "_ActivitySkus";
    private RecyclerView recyclerView;
    private List<ModelSku> resultSkus;
    private ActivitySkusAdapter adapterSkus;
    private FirebaseDatabase database;
    private DatabaseReference ref_skus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku);


        database=FirebaseDatabase.getInstance();
        String data = getIntent().getExtras().getString("ORDER_KEY");
        //Toast.makeText(this, data, Toast.LENGTH_SHORT).show();

        ref_skus =database.getReference("skus");


        resultSkus=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerView_skus);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin=new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);

        adapterSkus=new ActivitySkusAdapter(resultSkus);
        recyclerView.setAdapter(adapterSkus);

        updateList(data);


    }


    private void updateList(String data){
        ref_skus.orderByChild("orderKey").equalTo(data).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                resultSkus.add(dataSnapshot.getValue(ModelSku.class));
                adapterSkus.notifyDataSetChanged();

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                ModelSku model = dataSnapshot.getValue(ModelSku.class);
                int index=getItemIndex(model);
                resultSkus.set(index,model);
                adapterSkus.notifyItemChanged(index);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                ModelSku model = dataSnapshot.getValue(ModelSku.class);
                int index=getItemIndex(model);
                resultSkus.remove(index);
                adapterSkus.notifyItemRemoved(index);
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    private int getItemIndex(ModelSku sku){
        int index = -1;
        for (int i = 0; i <resultSkus.size(); i++) {
            if(resultSkus.get(i).key.equals(sku.key)){
                index=i;
                break;
            }
        }
        return index;
    }

}
