package com.vvv.manool.warehouse;

import android.content.Intent;
import android.media.Image;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.facebook.stetho.Stetho;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ActivityLogin extends AppCompatActivity {
    private static final String TAG = "VVV_ActivityLogin";
    //private SQLiteDatabase db;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Stetho.initializeWithDefaults(this);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();

        final EditText ed_email=findViewById(R.id.tfld_email);
        final EditText ed_pass=findViewById(R.id.tfld_pass);
        final Button btn_signIn=findViewById(R.id.btn_signIn);
        final Button btn_signUp=findViewById(R.id.btn_signUp);
        final ImageView img_view_l_email=findViewById(R.id.img_login_email);
        final ImageView img_view_l_pass=findViewById(R.id.img_login_pass);
        btn_signIn.setEnabled(false);
        btn_signUp.setEnabled(false);

        ed_email.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {           }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //если с емайлом все ок
                if (isValidEmail(ed_email.getText().toString())){
                    //ставлю ЛАйк имеджа для емайла
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_view_l_email.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_24dpgray, getApplicationContext().getTheme()));
                    } else {
                        img_view_l_email.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_24dpgray));
                    }
                    //если и с пасс все ок, то отображаю кнопки
                    if (isValidPass(ed_pass.getText().toString())) {
                        btn_signIn.setEnabled(true);
                        btn_signUp.setEnabled(true);
                    }
                }else{//дизлайкаю емайл
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_view_l_email.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_down_black_24dpgrey, getApplicationContext().getTheme()));
                    } else {
                        img_view_l_email.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_down_black_24dpgrey));
                    }//ДИСаблю кнопки
                    btn_signIn.setEnabled(false);
                    btn_signUp.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {         }
        });


        ed_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {           }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //если с Пасс все ок то Лайкаю Пасс
                if (isValidPass(ed_pass.getText().toString())){
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_view_l_pass.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_24dpgray, getApplicationContext().getTheme()));
                    } else {
                        img_view_l_pass.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_up_black_24dpgray));
                    }//Если с емайлом ок - отображаю кнопки
                    if ( isValidEmail(ed_email.getText().toString())) {
                        btn_signIn.setEnabled(true);
                        btn_signUp.setEnabled(true);
                    }
                }else{//дизлайкаю емайл и скрываю кнопки
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        img_view_l_pass.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_down_black_24dpgrey, getApplicationContext().getTheme()));
                    } else {
                        img_view_l_pass.setImageDrawable(getResources().getDrawable(R.drawable.ic_thumb_down_black_24dpgrey));
                    }
                    btn_signIn.setEnabled(false);
                    btn_signUp.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {           }
        });

        //обработчик заЛОГиниться
        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signInAccount(ed_email.getText().toString(), ed_pass.getText().toString());
            }
        });
        //Обработчик заРЕГистрироваться
        btn_signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUpAccount(ed_email.getText().toString(), ed_pass.getText().toString());
            }
        });
    }

    //Проверяю валидность поля пароля
    public boolean isValidPass(String pass){
        if (pass.length()>5) { return true; }
        else { return false;}
    }
    //проверяю валидность поля емайла
    public boolean isValidEmail(String email){
        if ((email.length()>6) &&(email.indexOf(" ")<0) && (email.indexOf("@")>0) && (email.indexOf(".")>email.indexOf("@")+1)&& (email.indexOf(".")+1<email.length())) {
            return true;
        }else { return false;  }
    }




    private void signInAccount(String email, String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            //updateUI(user);
                            //FirebaseUser user1 = FirebaseAuth.getInstance().getCurrentUser();
                            if (user != null) {
                                // Name, email address, and profile photo Url
                                String name = user.getDisplayName();
                                String email = user.getEmail();
                               // Check if user's email is verified
                                boolean emailVerified = user.isEmailVerified();
                                // The user's ID, unique to the Firebase project. Do NOT use this value to
                                // authenticate with your backend server, if you have one. Use
                                // FirebaseUser.getToken() instead.
                                String uid = user.getUid();
                                Intent intent=new Intent(getBaseContext(),ActivityDesktop.class);
                                startActivity(intent); //  значит что от активити ничего не ожидаем
                           }

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });

    }
    private void signUpAccount(String email, String password) {
       mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(ActivityLogin.this, "Вы успешно зарегистрированны", Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getBaseContext(),ActivityDesktop.class);
                            startActivity(intent); //  значит что от активити ничего не ожидаем
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(ActivityLogin.this, "Похоже такой пользователь уже есть", Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                        // ...
                    }
                });
    }


}
