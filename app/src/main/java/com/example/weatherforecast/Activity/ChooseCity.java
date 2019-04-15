package com.example.weatherforecast.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherforecast.Adapter.CityApdapter;
import com.example.weatherforecast.Adapter.FavoriteCityAdapter;
import com.example.weatherforecast.Model.City;
import com.example.weatherforecast.Model.FavoriteCity;
import com.example.weatherforecast.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ChooseCity extends Activity {
    ImageView imgAdd;
    Intent intent;

    ArrayList<City> listCity;
    FavoriteCityAdapter adapter;
    ArrayList<FavoriteCity> listFavoriteCity;

    ListView listView;
    ImageView imgBack;

    public SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_city);

        initData();

        loadState();

        if (intent != null) {
            String name = intent.getStringExtra("city");
            String country = intent.getStringExtra("country");
            String id = intent.getStringExtra("id");

            if (name != null && country != null && id != null) {
                listCity.add(new City(name, country, id));
                adapter.notifyDataSetChanged();
            }
        }

        initView();

        eventHandler();
    }

    private void initView() {
        String ID = "";
        for (int i =0; i<listCity.size()-1; i++) {
            ID += listCity.get(i).ID;
            ID += ",";
        }
        ID += listCity.get(listCity.size()-1).ID;

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://api.openweathermap.org/data/2.5/group?id="+  ID +"3&units=metric&appid=b72ce368d7a441149f85cdddf363df06";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("list");

                    for (int i=0; i<jsonArray.length(); i++) {
                        JSONObject jsonObjectCity = jsonArray.getJSONObject(i);

                        
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();

        saveState();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveState();
    }

    private void initData() {
        imgAdd = findViewById(R.id.addCiy);
        intent = getIntent();
        listCity = new ArrayList<City>();
        listFavoriteCity = new ArrayList<FavoriteCity>();
        adapter = new FavoriteCityAdapter(getApplicationContext(), listFavoriteCity);
        listView = findViewById(R.id.listview_city);
        listView.setAdapter(adapter);
        imgBack = findViewById(R.id.btn_thoat);
    }

    private void eventHandler() {
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCity.this, SearchActivity.class);
                startActivity(intent);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.putExtra("id", listCity.get(position).ID);
                startActivity(intent);
            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void loadState() {
        SharedPreferences preferences = getSharedPreferences("my_state", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        listCity.clear();

        int length = preferences.getInt("Length", 0);
        if (length > 0) {
            for (int i = 0; i<length; i++) {
                String name = preferences.getString("Name " + i, "");
                String id = preferences.getString("ID " + i, "");
                String country = preferences.getString("Country " + i, "");

                listCity.add(new City(name, country, id));
            }
            adapter.notifyDataSetChanged();
        }

        editor.clear();
        editor.commit();
    }

    private void saveState() {
        preferences = getSharedPreferences("my_state", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.clear();

        editor.putInt("Length", listCity.size());
        if (listCity.size() > 0) {
            for (int i =0; i<listCity.size(); i++) {
                editor.putString("Name " + i, listCity.get(i).Name);
                editor.putString("Country " + i, listCity.get(i).Country);
                editor.putString("ID " + i, listCity.get(i).ID);
            }
        }

        editor.commit();
    }
}


