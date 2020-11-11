package com.pyropy.work24.views.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.VideoView;

import com.airbnb.lottie.LottieAnimationView;
import com.pyropy.work24.R;
import com.pyropy.work24.views.fragments.GettingStarted1;
import com.pyropy.work24.views.fragments.GettingStarted2;
import com.pyropy.work24.views.fragments.GettingStarted3;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class SplashScreen extends AppCompatActivity {

//    public static int SPLASH_TIME_OUT = 4000;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_splash);
//        VideoView videoView = (VideoView) findViewById(R.id.videoView);
//
//
//        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.work));
//        videoView.start();
//        new Handler().postDelayed(new Runnable() {
//
//
//            /*
//             * Showing splash screen with a timer. This will be useful when you
//             * want to show case your app logo / company
//             */
//
//            @Override
//            public void run() {
//                // This method will be executed once the timer is over
//                // Start your app main activity
//                Intent i = new Intent(SplashScreen.this, MyAppIntroActivity.class);
//                startActivity(i);
//
//                // close this activity
//                finish();
//            }
//        }, SPLASH_TIME_OUT);
//    }
private ImageView logo, splashImg;
    private LottieAnimationView mLottieAnimationView;
    private static final int NUM_PAGES = 3;
    private ViewPager mViewPager;
    private ScreenSlidePagerAdapter mPagerAdapter;
    Animation anim;
    private static final String SHARED_PREFS = "com.pyropy.nodal.STATUS";
    public static final String STATUS = "status";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        initComponents();
        if(!isFirstRun()){
            animateComponents();
            //saveRun();
        }else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent =  new Intent(SplashScreen.this, Login.class);

//                    Pair[] uPairs = new Pair[1];
//                    uPairs[0]  = new Pair<View, String>(logo,"logo_image");

                    //ActivityOptions uOptions = ActivityOptions.makeSceneTransitionAnimation(SplashActivity.this,uPairs);

                    //startActivity(intent,uOptions.toBundle());
                    startActivity(intent);
                    finish();
                }
            }, 5000);
        }
    }

    private void saveRun() {
        SharedPreferences uSharedPreferences = getApplicationContext().getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = uSharedPreferences.edit();
        edit.putString(STATUS, "run once");
        edit.commit();
    }

    private boolean isFirstRun() {
        String ran = getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE).getString(STATUS, "first run");
        if (ran.equalsIgnoreCase("first run")) {
            return false;
        }
        return true;
    }

    private void animateComponents() {
        splashImg.animate().translationY(-3500).setDuration(1500).setStartDelay(3000);
        logo.animate().translationY(2500).setDuration(1500).setStartDelay(3000);
        mLottieAnimationView.animate().translationY(2500).setDuration(1500).setStartDelay(3000);
    }

    private void initComponents() {
        logo = findViewById(R.id.logo);
        splashImg = findViewById(R.id.bg_img);
        mLottieAnimationView = findViewById(R.id.lottie);

        mViewPager = findViewById(R.id.pager);
        mPagerAdapter = new ScreenSlidePagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);

        //animation
        anim = AnimationUtils.loadAnimation(this,R.anim.fadein);
        mViewPager.startAnimation(anim);
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {

        public ScreenSlidePagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            //Fragment uFragment;
            switch (position){
                case 0:
                    GettingStarted1 tab1  = new GettingStarted1();
                    return tab1;
                case 1:
                    GettingStarted2 tab2 = new GettingStarted2();
                    return tab2;
                case 2:
                    GettingStarted3 tab3 = new GettingStarted3();
                    return tab3;
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
