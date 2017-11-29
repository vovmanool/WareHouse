package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ActivityLogin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        View.OnClickListener  listenerLogin = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Log.d(TAG, "onClick: ");
                Intent intent=new Intent(getBaseContext(),ActivityDesktop.class);
                startActivity(intent);
            }
        };
        Button btn=(Button)findViewById(R.id.btn_login);
        btn.setOnClickListener(listenerLogin);




    }
}
