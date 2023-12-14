package com.example.splashartproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashArtActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;

    ImageView ivIc, ivLg;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_splash_art);

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation);

        ivLg = findViewById(R.id.ivLg);
        ivIc = findViewById(R.id.ivIc);


    // ivLg.setAnimation(topAnim);
    //  ivIc.setAnimation(bottomAnim);

    // ivLg.setAnimation(bottomAnim);
    //  ivIc.setAnimation(topAnim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashArtActivity.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        },6000);

    }
}