package com.example.solcasinojava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.WithHint;

import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private Object NoLuck;
    private Object Dmed;
    private Object Solcasino;
    private Object Gm;
    private Object Gn;
    //wheel values
    final String sectors = Arrays.toString(new Object[]{NoLuck, Gm, Gn, Solcasino, NoLuck, Dmed, NoLuck, Solcasino, Solcasino, Gm, NoLuck});
    final String [] sectorDegrees = new String[sectors.length()];

    //random index
    String randomSectorIndex = null;

    //what to spin
    ImageView wheel;
    boolean spinning = false;
    String earningsRecord = null;

    //random to help generate random index & etc
    Random random = new Random();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //to spin
        wheel = findViewById(R.id.wheel);
        //tap detector
        ImageView belt =  findViewById(R.id.belt);


        //generate sector degree to use
        generateSectorDegrees();

        //click belt to spin the wheel
        belt.setOnClickListener(v -> {
            //only if it is not spinning
            if(!spinning){
                //spin
                spin();
                spinning = true; //now spinning
            }
        });

        //withdraw
        Button withdrawBtn = findViewById(R.id.withdrawBtn);
        withdrawBtn.setOnClickListener(v -> {
            String text = "Ready to withdraw"+ earningsRecord + " coins money ?? Do more.... ";
            //toast.show();
        });

    }

    private void spin() {
        //get any random sector index
        randomSectorIndex = String.valueOf(random.nextInt(sectors.length())); // the bound is exclusive

        //generate a random degree to spin the wheel
        int randomDegree = Integer.parseInt(generateRandomDegreeToSpin());

        //do actual spinning use the rotation animation
        RotateAnimation rotateAnimation = new RotateAnimation(0, randomDegree,
                RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);

        //Time for spinning
        rotateAnimation.setDuration(3600);  //3.6 seconds
        rotateAnimation.setFillAfter(true);  // filter

        //interpolator
        rotateAnimation.setInterpolator(new DecelerateInterpolator());  //start with high speed, slow down at the end

        //spinning listener
        rotateAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {
                //interested when spinning ends
                //get earns
                // int earnedCoins = sectors[sectors.length() - (Integer.parseInt(randomSectorIndex) + 1)];

                //save the earnings
                // saveEarnings(earnedCoins);

                //toast current earning

                // String sms = "You have earned "+earnedCoins + " coins";
                //toast(sms);

                //spinning ended
                spinning = false;

            }

            // private void toast(String s) {
            //    Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
            //  }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        //apply animation to our wheel
        wheel.startAnimation(rotateAnimation);
    }

   /* private void saveEarnings(int earnedCoins){
        // save it in earning Record
        earningsRecord = earningsRecord + earnedCoins;
        //set the value to tv so user sees
        TextView tv = findViewById(R.id.earnings);
        tv.setText(String.valueOf(earningsRecord));


    } */

    private String generateRandomDegreeToSpin() {
        //make it higher as much as you can
        return (360*sectors.length()) + sectorDegrees [Integer.parseInt(randomSectorIndex)];
    }

    private void generateSectorDegrees() {
        // for 1 sector
        int sectorDegree = 360/sectors.length();

        for (int i = 0; i < sectors.length(); i++){
            //make it as much greater as you can
            sectorDegrees[i] = String.valueOf((i*1) * sectorDegree);
        }
    }
}