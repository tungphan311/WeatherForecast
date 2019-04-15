package com.example.weatherforecast.Activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.weatherforecast.Adapter.CityApdapter;
import com.example.weatherforecast.Model.City;
import com.example.weatherforecast.R;

import java.util.ArrayList;

public class ChooseCity extends Activity {
    ImageView imgAdd;
    Intent intent;

    ArrayList<City> listCity;
    CityApdapter adapter;
    ListView listView;

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

        eventHandler();
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
        adapter = new CityApdapter(getApplicationContext(), listCity);
        listView = findViewById(R.id.listview_city);
        listView.setAdapter(adapter);
    }

    private void eventHandler() {
        imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChooseCity.this, SearchActivity.class);
                startActivity(intent);
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


