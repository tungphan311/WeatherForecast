package com.example.weatherforecast.Fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.weatherforecast.Model.OneDay;
import com.example.weatherforecast.R;
import com.example.weatherforecast.Adapter.SevendayAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class SevenDaysFragment extends Fragment {

    ListView listView;
    SevendayAdapter adapter;
    ArrayList<OneDay> listDay;

    public SevenDaysFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.day_of_week, container, false);

        initView(view);

        initData();

        return view;
    }

    public void initView(View view) {
        listView = view.findViewById(R.id.lv_7day);
        listDay = new ArrayList<OneDay>();
        adapter = new SevendayAdapter(getActivity().getApplicationContext(), listDay);
        listView.setAdapter(adapter);
    }

    private void initData() {
    }

}
