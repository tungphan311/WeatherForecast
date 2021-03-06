package com.example.weatherforecast.Activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.weatherforecast.Fragment.TodayFragment;
import com.example.weatherforecast.Fragment.TomorrowFragment;
import com.example.weatherforecast.R;
import com.example.weatherforecast.Fragment.SevenDaysFragment;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    public String data;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        intent = getIntent();
        if (intent != null) {
            String LAT = intent.getStringExtra("lat");
            String DATA = intent.getStringExtra("data");
            Log.d("coord", "onCreate: data "+DATA);
            Log.d("coord", "onCreate: lat "+LAT);
            String LON = intent.getStringExtra("lon");
            if (LAT == null) {
//                data = "1580578";
                data="10.83&lon=106.67";
            }
            else {
                data = LAT+"&lon="+LON;
            }
        }


    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;

            switch (position) {
                case 0:
                    fragment = new TodayFragment();
                    break;

                case 1:
                    fragment = new TomorrowFragment();
                    break;

                case 2:
                    fragment = new SevenDaysFragment();
                    break;
            }

            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "NOW";
                case 1:
                    return "TODAY";
                case 2:
                    return "7 days";
            }
            return null;
        }
    }
}