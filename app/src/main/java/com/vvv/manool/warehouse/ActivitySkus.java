package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivitySkus extends AppCompatActivity {
    private static final String TAG = "_ActivitySkus";
    private RecyclerView recyclerView;
    private List<ModelSku> resultSkus;
    private ActivitySkusAdapter adapterSkus;
    private FirebaseDatabase database;
    private DatabaseReference ref_skus;
    private Button btnAsScan;
    private String data;

    private static final int RC_BARCODE_CAPTURE = 9002;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sku);

        data = getIntent().getExtras().getString("ORDER_KEY");
        database=FirebaseDatabase.getInstance();
        ref_skus =database.getReference("skus");

        resultSkus=new ArrayList<>();

        recyclerView=findViewById(R.id.recyclerView_skus);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager lin=new LinearLayoutManager(this);
        lin.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(lin);

        adapterSkus=new ActivitySkusAdapter(resultSkus);
        recyclerView.setAdapter(adapterSkus);

        btnAsScan=findViewById(R.id.btn_as_scan);
        btnAsScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getBaseContext(), BarcodeCaptureActivity.class);
                intent.putExtra(BarcodeCaptureActivity.AutoFocus, true);
                intent.putExtra(BarcodeCaptureActivity.UseFlash, false);
                startActivityForResult(intent, RC_BARCODE_CAPTURE);
            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    //statusMessage.setText(R.string.barcode_success);
                    //tvScanBarcode.setText(barcode.displayValue);
                    Boolean found =false;
                    //перебрать всю коллекцию и найти ключ для данного ШК
                    for (ModelSku resultsku :resultSkus){
                        //если нашел итем с нужным ШК
                        if (resultsku.barcode.equals(barcode.displayValue)) {
                            if (resultsku.fact<resultsku.plan) {
                                found=true;
                                resultsku.fact++;


                                Map<String, Object> skuValues = resultsku.toMap();
                                Map<String, Object> newSku = new HashMap<>();

                                newSku.put(resultsku.key, skuValues);
                                ref_skus.updateChildren(newSku);
                                break;
                            }else{
                                Toast.makeText(this, "Эта продукция уже собрана, перейдите к другой", Toast.LENGTH_SHORT).show();                           }
                        }
                    }
                    if (!found){
                        Toast.makeText(this, "Такого ШК нет в этом заказе", Toast.LENGTH_SHORT).show();
                    }

                } else {
                   // tvScanBarcode.setText(R.string.barcode_failure);
                    //Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                //tvScanBarcode.setText(String.format(getString(R.string.barcode_error),
                  //      CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

}
