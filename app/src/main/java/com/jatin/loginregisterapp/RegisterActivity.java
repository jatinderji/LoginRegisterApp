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

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText etUsername, etPassword, etCPassword;
    private MaterialButton btnRegister;
    private AppCompatTextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);


        String text = tvLogin.getText().toString();

        SpannableString ss = new SpannableString(text);

        ClickableSpan cs = new ClickableSpan() {

            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.rgb(98, 0, 238));
                ds.setFakeBoldText(true);
            }

            @Override
            public void onClick(@NonNull View view) {
                gotoLogin();
            }
        };
        ss.setSpan(cs, 15, text.length(), SpannableString.SPAN_INCLUSIVE_EXCLUSIVE);
        tvLogin.setText(ss);
        // use for scrolling or click behaviour
        tvLogin.setMovementMethod(LinkMovementMethod.getInstance());


        btnRegister.setOnClickListener(view -> {

            String un = etUsername.getText().toString();
            String up = etPassword.getText().toString();

            if (un.length() > 0 && up.length() > 0) {

                SharedPreferences sp = getSharedPreferences("jv", MODE_PRIVATE);

                final SharedPreferences.Editor edit = sp.edit();
                edit.putString("un", un);
                edit.putString("up", up);
                edit.commit();
                Toast.makeText(this, "Registered Successfully...", Toast.LENGTH_SHORT).show();
                gotoLogin();
            }
            else{
                Toast.makeText(this, "Please enter username and password", Toast.LENGTH_SHORT).show();
            }

        });


    }

    private void gotoLogin() {
        startActivity(new Intent(RegisterActivity.this, MainActivity.class));
        finish();
    }
}