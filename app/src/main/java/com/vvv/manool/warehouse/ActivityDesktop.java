package com.vvv.manool.warehouse;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ActivityDesktop extends AppCompatActivity {
    private FirebaseAuth mAuth;
    FirebaseDatabase database;
    @Override
    public void onStart() {
        super.onStart();

        // Проверяю если юзверь залогинен, то пропускаю логин
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
         } else {// No user is signed in
            Intent intent=new Intent(getBaseContext(),ActivityLogin.class);
            startActivity(intent); //  значит что от активити ничего не ожидаем
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);
        database = FirebaseDatabase.getInstance();
        ImageButton img_button_cat=findViewById(R.id.img_button_cat);
        ImageButton img_button_order=findViewById(R.id.img_button_order);
        ImageButton img_button_scan=findViewById(R.id.img_button_scan);

        img_button_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Intent intent=new Intent(getBaseContext(),ActivityCatalog.class);
               // startActivity(intent); //  значит что от активити ничего не ожидаем
                DatabaseReference myRef = database.getReference("ord_id");
                myRef.setValue("Hello, World!");
           }
        });

        img_button_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ActivityList.class);
                startActivity(intent); //  значит что от активити ничего не ожидаем
            }
        });

        img_button_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent=new Intent(getBaseContext(),ActivityScan.class);
                //startActivity(intent); //  значит что от активити ничего не ожидаем
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_desktop,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId){
            case R.id.menu_item_sync:
                Toast.makeText(this, "Синхронизация", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_pref:
                Toast.makeText(this, "Настройка", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_about:
                Toast.makeText(this, "О программе", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_exit:
                //Toast.makeText(this, "Выход", Toast.LENGTH_SHORT).show();
                mAuth.getInstance().signOut();
                Intent intent=new Intent(getBaseContext(),ActivityLogin.class);
                startActivity(intent); //  значит что от активити ничего не ожидаем
                break;
        }
        return super.onOptionsItemSelected(item);
    }





   // final List<Message> messages=new ArrayList<>();

 //   final MessagesRecyclerAdapter adapter=new MessagesRecyclerAdapter(messages);
   //     recyclerView.setAdapter(adapter);

//        imageButton.setOnClickListener(new View.OnClickListener() {
//        @Override
//        public void onClick(View view) {
//            Editable editableText = editText.getText();
//            String text=editableText.toString();
//            Message newMessage=new Message(text,new Date());
//            messages.add(0,newMessage);
//            adapter.notifyItemInserted(0);//все перерисовывает
//            editText.setText("");
//            recyclerView.scrollToPosition(0);


   // });
}
