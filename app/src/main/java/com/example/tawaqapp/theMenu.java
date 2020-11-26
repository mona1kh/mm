package com.example.tawaqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class theMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_menu);
    }


    public void goToHome(View v){
        Intent i = new Intent(this,home.class);
        startActivity(i);
        finish();
    }
    public void goTotawaq(View v){
        Intent i = new Intent(this,users.class);
        startActivity(i);
        finish();
    }

    public void goToTracks(View v){
        Intent i = new Intent(this,trainingTracks.class);
        startActivity(i);
        finish();
    }

    public void goToCourses(View v){
        Intent i = new Intent(this,courses.class);
        startActivity(i);
        finish();
    }

    public void goToUserActivity(View v){
        Intent i = new Intent(this,userAccount.class);
        startActivity(i);
        finish();
    }

    public void goToAboutIn(View v){
        Intent i = new Intent(this,aboutIn.class);
        startActivity(i);
        finish();
    }
    public void logout(View v){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
        finish();
    }

}