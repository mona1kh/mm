package com.example.tawaqapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class trainingTracks extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_tracks);
    }

    // remove this method after all lama<3 !
    public void backToHome(View V){

        Intent i = new Intent(this,home.class);
        startActivity(i);
        finish();
    }

    public void backToMenu(View V){

        Intent i = new Intent(this,theMenu.class);
        startActivity(i);

    }


    public void goToTrainingPlaces(View V){

        Intent i = new Intent(this,trainingPlaces.class);
        startActivity(i);

    }
}