package com.example.weatherforecast;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        int actionBarTitleId = Resources.getSystem().getIdentifier("action_bar_title", "id", "android");
//        if (actionBarTitleId > 0) {
//            TextView title = (TextView) findViewById(actionBarTitleId);
//            if (title != null) {
//                title.setTextColor(Color.WHITE);
//            }
//        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
    }

    public static class PlaceholderFragment extends Fragment {

        private static final String KEY_COLOR = "key_color";

        public PlaceholderFragment() {
        }

        // Method static dạng singleton, cho phép tạo fragment mới, lấy tham số đầu vào để cài đặt màu sắc.
        public static PlaceholderFragment newInstance(int color) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(KEY_COLOR, color);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.tomorrow, container, false);
            SwipeRefreshLayout mainLayout = rootView.findViewById(R.id.swipeRefreshLayout);

            /**
             * Số 1: Màu xanh.
             * Số 2: Màu đỏ.
             * Số 3: Màu vàng.
             */
            switch (getArguments().getInt(KEY_COLOR)) {
                case 1:
//                    mainLayout.setBackgroundColor(Color.GREEN);
                    rootView = inflater.inflate(R.layout.today, container, false);
                    TextView tempView = rootView.findViewById(R.id.temp);
                    ImageView weatherIcon = rootView.findViewById(R.id.weather_icon);
                    TextView pressView = rootView.findViewById(R.id.pressure);
                    TextView humView = rootView.findViewById(R.id.hum);
                    TextView TextViewwindView = rootView.findViewById(R.id.wind);
                    weatherIcon.setImageResource(R.drawable.ic_sun);


                    break;

                case 2:
//                    mainLayout.setBackgroundColor(Color.RED);

                TextView todayTemp = rootView.findViewById(R.id.todayTemperature);
                TextView todayDescription = rootView.findViewById(R.id.todayDescription);
                TextView todayWind = rootView.findViewById(R.id.todayWind);
                TextView todayPressure = rootView.findViewById(R.id.todayPressure);
                TextView todayHumid = rootView.findViewById(R.id.todayHumidity);
                TextView todaySunrise = rootView.findViewById(R.id.todaySunrise);
                TextView todaySunset = rootView.findViewById(R.id.todaySunset);
                TextView todayUV = rootView.findViewById(R.id.todayUvIndex);
                ImageView todayIcon =  rootView.findViewById(R.id.todayIcon);



                todayTemp.setText("0 °C");
                todayDescription.setText("Description");
                todayWind.setText("Wind: 0 m/s");
                todayPressure.setText("Pressure: 0 hpa");
                todayHumid.setText("Humidity: 0 %");
                todaySunrise.setText("Sunrise: 00:00");
                todaySunset.setText("Sunset: 00:00");
                todayUV.setText("UV index: none");
                todayIcon.setImageResource(R.drawable.ic_sun);

                GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 4);

                RecyclerView recyclerView = rootView.findViewById(R.id.viewPager);
                recyclerView.setLayoutManager(gridLayoutManager);
                recyclerView.setHasFixedSize(true);
                break;
                case 3:
//                    mainLayout.setBackgroundColor(Color.YELLOW);
                    rootView = inflater.inflate(R.layout.day_of_week, container, false);


                    break;



                default:
                    mainLayout.setBackgroundColor(Color.WHITE);
                    break;
            }



            return rootView;
        }
    }

    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // position + 1 vì position bắt đầu từ số 0.
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "TODAY";
                case 1:
                    return "TOMORROW";
                case 2:
                    return "7 days";
            }
            return null;
        }
    }
}