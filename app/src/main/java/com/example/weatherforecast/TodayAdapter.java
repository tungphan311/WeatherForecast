package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TodayAdapter extends BaseAdapter {
    Context context;
    ArrayList<Weather> listWeather;

    public TodayAdapter(Context context, ArrayList<Weather> listWeather) {
        this.context = context;
        this.listWeather = listWeather;
    }

    @Override
    public int getCount() {
        return listWeather.size();
    }

    @Override
    public Object getItem(int position) {
        return listWeather.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder {
        LinearLayout llOneHour;
        TextView tvHour, tvRealTemp, tvFeelTemp, tvIconPhrase, tvHum, tvRain;
        ImageView imgIcon;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        TodayAdapter.ViewHolder viewHolder = null;

        if (view == null) {
            viewHolder = new TodayAdapter.ViewHolder();

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.lvweather, null);

            viewHolder.llOneHour = view.findViewById(R.id.oneHours);
            viewHolder.tvHour = view.findViewById(R.id.hour);
            viewHolder.tvRealTemp = view.findViewById(R.id.realtemp);
            viewHolder.tvFeelTemp = view.findViewById(R.id.feeltemp);
            viewHolder.tvIconPhrase = view.findViewById(R.id.iconphrase);
            viewHolder.tvHum = view.findViewById(R.id.hum);
            viewHolder.tvRain = view.findViewById(R.id.rain);
            viewHolder.imgIcon = view.findViewById(R.id.weathericon);

            view.setTag(viewHolder);
        }
        else {
            viewHolder = (TodayAdapter.ViewHolder) view.getTag();
        }

        Weather weather = (Weather) getItem(position);

        viewHolder.tvHour.setText(weather.getHour());
        viewHolder.tvRealTemp.setText(weather.getRealtemp() + (char)0x00B0 + "C");
        viewHolder.tvFeelTemp.setText(weather.getFeeltemp() + (char)0x00B0 + "C");
        viewHolder.tvIconPhrase.setText("Điều kiện thời tiết: " + weather.getIconphrase());
        viewHolder.tvHum.setText("Độ ẩm: " + weather.getHum() + "%");
        viewHolder.tvRain.setText("Khả năng mưa: " + weather.getRain() + "%");
        Picasso.with(context).load(weather.getIcon()).into(viewHolder.imgIcon);

        return view;
    }
}
