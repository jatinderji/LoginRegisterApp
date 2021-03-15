package com.jatin.loginregisterapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText etUsername,etPassword;
    private MaterialButton btnLogin;
    private AppCompatTextView tvRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);

        String text = tvRegister.getText().toString();

        SpannableString ss = new SpannableString(text);

        ClickableSpan cs = new ClickableSpan() {

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(98,0,238));
                ds.setFakeBoldText(true);
            }

            @Override
            public void onClick(@NonNull View view) {
                gotoRegister();
            }
        };
        ss.setSpan(cs,10,text.length(),SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);

        tvRegister.setText(ss);
        // use for scrolling or click behaviour
        tvRegister.setMovementMethod(LinkMovementMethod.getInstance());


        btnLogin.setOnClickListener(view -> {

            String un = etUsername.getText().toString();
            String up = etPassword.getText().toString();

            SharedPreferences sp = getSharedPreferences("jv",MODE_PRIVATE);
            if(un.length()>0 && up.length()>0) {
                String oun = sp.getString("un", null);
                String oup = sp.getString("up", null);
                if(oun.equals(un) && oup.equals(up)){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();
                    gotoWelcome();
                }
                else {
                    Toast.makeText(this, "Invalid Username or Password", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }
        });
        
        
    }

    private void gotoRegister(){
        startActivity(new Intent(MainActivity.this,RegisterActivity.class));
        finish();
    }
    private void gotoWelcome(){
        startActivity(new Intent(MainActivity.this,WelcomeActivity.class));
        finish();
    }
}