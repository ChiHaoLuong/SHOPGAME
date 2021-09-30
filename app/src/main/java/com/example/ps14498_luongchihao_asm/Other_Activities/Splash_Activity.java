package com.example.ps14498_luongchihao_asm.Other_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ps14498_luongchihao_asm.R;
import com.facebook.shimmer.ShimmerFrameLayout;

public class Splash_Activity extends AppCompatActivity {

    Animation topAnim, botAnim;
    ShimmerFrameLayout iv;
    TextView tvslogan, tvname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        setContentView(R.layout.activity_splash);
        AnhXa();

//        animations
        topAnim = AnimationUtils.loadAnimation(Splash_Activity.this, R.anim.top_animation_splash);
        botAnim = AnimationUtils.loadAnimation(Splash_Activity.this, R.anim.bottom_animation_splash);

//        set animation
        iv.setAnimation(topAnim);
        tvname.setAnimation(botAnim);
        tvslogan.setAnimation(botAnim);
        iv.startShimmer();

//        Move to Login
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(Splash_Activity.this, Login_Activity.class);
                startActivity(i);
                finish();
            }
        }, 5000);
    }

    private void AnhXa() {
        iv = findViewById(R.id.iv_splash);
        tvname = findViewById(R.id.tvname_splash);
        tvslogan = findViewById(R.id.tvslogan_splash);
    }
}