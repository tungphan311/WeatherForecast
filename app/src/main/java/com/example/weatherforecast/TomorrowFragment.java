package com.example.weatherforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TomorrowFragment extends Fragment {
    ArrayList<Weather> listWeather;
    TodayAdapter adapter;
    ListView listView;

    public TomorrowFragment() {
        // Required empty public constructor
    }

    public static TomorrowFragment newInstance(String param1, String param2) {
        TomorrowFragment fragment = new TomorrowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tomorrow, container, false);

        initView(view);

        initData();

        return view;
    }

    public void initView(View view) {
        listWeather = new ArrayList<>();
        adapter = new TodayAdapter(getActivity().getApplicationContext(), listWeather);
        listView = view.findViewById(R.id.listview);
        listView.setAdapter(adapter);
    }

    public void initData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        String url = "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/700000?apikey=hAaFwVWe0OfIbCw8aqUHkuNltaZKTj65&language=vi-vn";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                //Toast.makeText(getActivity().getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                String hour = "";
                String realtemp = "";
                String feeltemp = "";
                String iconphrase = "";
                String hum = "";
                String rain = "";
                String icon = "";
                String iconurl = "";

                if (response != null) {
                    String size = String.valueOf(response.length());
                    Toast.makeText(getActivity().getApplicationContext(), size, Toast.LENGTH_SHORT).show();
                    try {
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject jsonObject = response.getJSONObject(i);
                            hour = jsonObject.getString("EpochDateTime");
                            long l = Long.valueOf(hour);
                            Date date = new Date(l * 1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
                            String Day = simpleDateFormat.format(date);

                            JSONObject jsonObjectTemp = jsonObject.getJSONObject("Temperature");
                            realtemp = jsonObjectTemp.getString("Value");

                            JSONObject jsonObjectFeelTemp = jsonObject.getJSONObject("RealFeelTemperature");
                            feeltemp = jsonObjectFeelTemp.getString("Value");

                            iconphrase = jsonObject.getString("IconPhrase");
                            hum = jsonObject.getString("RelativeHumidity");
                            rain = jsonObject.getString("RainProbability");
                            icon = jsonObject.getString("WeatherIcon");
                            iconurl = "https://developer.accuweather.com/sites/default/files/" + icon + "-s.png";

                            listWeather.add(new Weather(Day, iconurl, realtemp, feeltemp, iconphrase, hum, rain));
                            adapter.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity().getApplicationContext(), "get list error", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);
    }

}
