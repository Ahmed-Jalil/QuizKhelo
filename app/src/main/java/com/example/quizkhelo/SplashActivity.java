package com.example.quizkhelo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quizkhelo.R;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView logo = findViewById(R.id.logo);


        // Load animations
        Animation combinedAnimation = AnimationUtils.loadAnimation(this,R.anim.combined_animation);

        // Start animations
        logo.startAnimation(combinedAnimation);

        new Handler().postDelayed(()->{
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                    finish();
                }, 3000); // 3s delay

    }
}
