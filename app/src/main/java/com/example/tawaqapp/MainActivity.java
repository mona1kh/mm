package com.example.tawaqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToAboutOut(View V){

        Intent i = new Intent(this,aboutOut.class);
        startActivity(i);
    }


    public void goToLoginActivity(View V){

        Intent i = new Intent(this,login.class);
        startActivity(i);
        finish();
    }

    public void goToRegisterActivity(View V){

        Intent i = new Intent(this,register.class);
        startActivity(i);
        finish();
    }
}