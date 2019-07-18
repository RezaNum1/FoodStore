package com.example.listactivitydetail;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

public class Splash_screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        ImageView img = findViewById(R.id.img_spalsh_screen);

        AlphaAnimation animation = new AlphaAnimation(0.0f, 1.f);
        animation.setDuration(2000);
        animation.setFillAfter(true);
        img.startAnimation(animation);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_screen.this,Auth.class);
                startActivity(intent);
                finish();
            }
        },2000);
    }
}
