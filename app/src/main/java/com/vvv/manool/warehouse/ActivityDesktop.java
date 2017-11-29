package com.vvv.manool.warehouse;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.ImageButton;

public class ActivityDesktop extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desktop);

        ImageButton img_button_cat=findViewById(R.id.img_button_cat);
        ImageButton img_button_order=findViewById(R.id.img_button_order);
        ImageButton img_button_scan=findViewById(R.id.img_button_scan);

        img_button_cat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getBaseContext(),ActivityCatalog.class);
                startActivity(intent); //  значит что от активити ничего не ожидаем
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
                Intent intent=new Intent(getBaseContext(),ActivityScan.class);
                startActivity(intent); //  значит что от активити ничего не ожидаем
            }
        });

    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_desktop,menu);
        return true;
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
