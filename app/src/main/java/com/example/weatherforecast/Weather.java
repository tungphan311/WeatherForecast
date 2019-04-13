package com.example.weatherforecast;

public class Weather {
    public String hour;
    public String icon;
    public String realtemp;
    public String feeltemp;
    public String iconphrase;
    public String hum;
    public String rain;

    public Weather(String hour, String icon, String realtemp, String feeltemp, String iconphrase, String hum, String rain) {
        this.hour = hour;
        this.icon = icon;
        this.realtemp = realtemp;
        this.feeltemp = feeltemp;
        this.iconphrase = iconphrase;
        this.hum = hum;
        this.rain = rain;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getRealtemp() {
        return realtemp;
    }

    public void setRealtemp(String realtemp) {
        this.realtemp = realtemp;
    }

    public String getFeeltemp() {
        return feeltemp;
    }

    public void setFeeltemp(String feeltemp) {
        this.feeltemp = feeltemp;
    }

    public String getIconphrase() {
        return iconphrase;
    }

    public void setIconphrase(String iconphrase) {
        this.iconphrase = iconphrase;
    }

    public String getHum() {
        return hum;
    }

    public void setHum(String hum) {
        this.hum = hum;
    }

    public String getRain() {
        return rain;
    }

    public void setRain(String rain) {
        this.rain = rain;
    }
}
