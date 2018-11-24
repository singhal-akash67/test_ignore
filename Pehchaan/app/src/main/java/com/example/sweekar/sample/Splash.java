package com.example.sweekar.sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class Splash extends Activity {
    final static int timer=1500;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        final ImageView iv = (ImageView) findViewById(R.id.pehchan);
    //    final Animation an = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_in);
        //final Animation an2 = AnimationUtils.loadAnimation(getBaseContext(), R.anim.abc_fade_out);
/*
        iv.startAnimation(an2);
        an2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //iv.startAnimation(an2);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
*/
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                finish();
                    Intent i = new Intent(Splash.this, MainActivity.class);
                    startActivity(i);
            }
        }, timer);
    }
    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
