package com.iprismech.alertnikkiresidence.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.iprismech.alertnikkiresidence.R;
import com.iprismech.alertnikkiresidence.adapters.SlidingImage_Adapter;
import com.iprismech.alertnikkiresidence.base.BaseAbstractActivity;
import com.iprismech.alertnikkiresidence.factories.Constants.AppConstants;
import com.iprismech.alertnikkiresidence.factories.controllers.ApplicationController;
import com.iprismech.alertnikkiresidence.pojo.IntroModel;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class IntroScreensActivity extends BaseAbstractActivity implements View.OnClickListener {
    private TextView start;
    private ViewPager introViewpager;
    private ArrayList<IntroModel> introModelList = new ArrayList<>();


    private static final Integer[] IMAGES = {
            R.drawable.intro_0,
            R.drawable.intro_1,
            R.drawable.intr0_2,
            R.drawable.intro_3,
            R.drawable.intro_4};
    /*private static final String[] TITLES = {
            "Alert Nikkii",
            "Alert Nikkii",
            "Alert Nikkii"};
    private static final String[] DESCRIPTION = {
            "loprem ipsum",
            "loprem ipsum",
            "loprem ipsum"};*/


    private static int NUM_PAGES = 0;
    private static int currentPage = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_intro_screens);
    }

    @Override
    protected View getView() {
        View view = getLayoutInflater().inflate(R.layout.activity_intro_screens, null);
        return view;
    }

    @Override
    public void setPresenter() {

    }

    @Override
    protected void setListenerToViews() {
        super.setListenerToViews();
        start.setOnClickListener(this);
    }

    @Override
    protected void initializeViews() {
        super.initializeViews();
        ApplicationController.getInstance().setContext(context);
        start = findViewById(R.id.btn_start);
        introViewpager= findViewById(R.id.introViewpager);
        init();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                ApplicationController.getInstance().handleEvent(AppConstants.EventIds.LAUNCH_LOGIN_SCREEN);
                finish();
                break;
        }
    }

    private void init() {
        introModelList.add(new IntroModel(R.drawable.intro_0, "Alert Nikki", "Description"));
        introModelList.add(new IntroModel(R.drawable.intro_1, "Alert Nikki", "Description"));
        introModelList.add(new IntroModel(R.drawable.intr0_2, "Alert Nikki", "Description"));
        introModelList.add(new IntroModel(R.drawable.intro_3, "Alert Nikki", "Description"));
        introModelList.add(new IntroModel(R.drawable.intro_4, "Alert Nikki", "Description"));
        for (int i = 0; i < IMAGES.length; i++)
//            introModelList.add(new IntroModel(IMAGES[i], TITLES[i], DESCRIPTION[i]));
            // introimageList.add(IMAGES[i]);
//        introtitleList.add(DESCRIPTION[]);
            introModelList.get(i);
        introViewpager = findViewById(R.id.introViewpager);
        introViewpager.setAdapter(new SlidingImage_Adapter(IntroScreensActivity.this, introModelList));
        CirclePageIndicator indicator = findViewById(R.id.indicator);
        indicator.setViewPager(introViewpager);
        final float density = getResources().getDisplayMetrics().density;

        //Set circle indicator radius
        indicator.setRadius(3 * density);
        NUM_PAGES = IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                introViewpager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2000);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int pos) {
            }
        });


    }

}
