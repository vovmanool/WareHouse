package com.vvv.manool.warehouse;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.lang.ref.SoftReference;

/**
 * Created by tb_dvl on 29.11.2017.
 */

public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {

    private static final String TAG = "MyFirebaseInstanceIdSer";

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        sendTokenToServer(token);
    }
    private void sendTokenToServer(String token){
        Log.d(TAG, "sendTokenToServer: "+token);
    }



}
