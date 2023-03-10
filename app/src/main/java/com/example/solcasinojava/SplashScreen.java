package com.example.solcasinojava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    private Timer splashTimer;
    private static final long DELAY =5000;
    private boolean scheduled = false;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        context = this;
        splashTimer = new Timer();

        splashTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(context,SplashScreen2.class);
                startActivity(intent);
                finish();
            }
        }, DELAY);
        scheduled = true;
    }

}