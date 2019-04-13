package com.example.weatherforecast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class TodayFragment extends Fragment {
    TextView txtName, txtCurrentDate, txtTemp, txtHumidity, txtPressure, txtWind, txtVisibility;
    TextView txtSunset, txtSunrise, txtDescription;
    ImageView imgWeatherIcon;

    public TodayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.today, container, false);
        initData(view);

        GetCurrentWeather("SaiGon");
        return view;
    }

    public void initData(View view) {
        txtName = view.findViewById(R.id.city_country);
        txtCurrentDate = view.findViewById(R.id.current_date);
        imgWeatherIcon = view.findViewById(R.id.weather_icon);
        txtTemp = view.findViewById(R.id.temp);
        txtHumidity = view.findViewById(R.id.hum);
        txtPressure = view.findViewById(R.id.pressure);
        txtWind = view.findViewById(R.id.wind);
        txtVisibility = view.findViewById(R.id.visibility);
        txtSunset = view.findViewById(R.id.sunset);
        txtSunrise = view.findViewById(R.id.sunrise);
        txtDescription = view.findViewById(R.id.description);
    }

    public void GetCurrentWeather(String data) {
        RequestQueue requestQueue = Volley.newRequestQueue(getContext().getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/weather?q=" + data + "&appid=b72ce368d7a441149f85cdddf363df06&units=metric";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String day = jsonObject.getString("dt");
                            String name = jsonObject.getString("name");
                            txtName.setText(name);

                            long l = Long.valueOf(day);
                            Date date = new Date(l*1000L);
                            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm");
                            String Day = simpleDateFormat.format(date);

                            txtCurrentDate.setText(Day);

                            JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                            JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                            String status = jsonObjectWeather.getString("main");
                            String icon = jsonObjectWeather.getString("icon");

                            Picasso.with(getActivity().getApplicationContext()).load("http://openweathermap.org/img/w/" + icon +".png")
                                    .into(imgWeatherIcon);

                            JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                            String temp = jsonObjectMain.getString("temp");
                            String maxTemp = jsonObjectMain.getString("temp_max");
                            String minTemp = jsonObjectMain.getString("temp_min");
                            String humidity = jsonObjectMain.getString("humidity");
                            String pressure = jsonObjectMain.getString("pressure");
                            Double dou = Double.valueOf(temp);
                            String Temp = String.valueOf(dou.intValue());
                            Double max = Double.valueOf(maxTemp);
                            String MaxTemp = String.valueOf(max.intValue());
                            Double min = Double.valueOf(minTemp);
                            String MinTemp = String.valueOf(min.intValue());
                            txtTemp.setText(Temp);
                            txtHumidity.setText(humidity + "%");
                            txtPressure.setText(pressure + " hPa");

                            JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                            String speed = jsonObjectWind.getString("speed");
                            txtWind.setText(speed + " m/s");

                            String visibility = jsonObject.getString("visibility");
                            int visi = Integer.valueOf(visibility);
                            float tamnhin = visi / 1000;
                            String Visiblity = String.valueOf(tamnhin);
                            txtVisibility.setText(Visiblity + " km");

                            JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                            String sunrise = jsonObjectSys.getString("sunrise");
                            long rise = Long.valueOf(sunrise);
                            Date dRise = new Date(rise*1000L);
                            SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                            String Sunrise = format.format(dRise);
                            txtSunrise.setText(Sunrise);

                            String sunset = jsonObjectSys.getString("sunset");
                            long set = Long.valueOf(sunset);
                            Date dSet = new Date(set*1000L);
                            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                            String SunSet = dateFormat.format(dSet);
                            txtSunset.setText(SunSet);

                            txtDescription.setText("Nhiệt độ cao nhất sẽ là: " + MaxTemp + (char)0x00B0 + "C. Nhiệt độ thấp nhất sẽ là: "
                                                    + MinTemp + (char)0x00B0 + "C.");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(stringRequest);
    }

}
