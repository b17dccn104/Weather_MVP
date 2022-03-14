package com.example.mvpweather.model.weather_note;

public class Note {
    private int id;
    private long date;
    private String icon;
    private float temp_min;
    private float temp_max;
    private int humidity;

    public Note(long date, String icon, float temp_min, float temp_max, int humidity) {
        this.date = date;
        this.icon = icon;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    public Note(int id, long date, String icon, float temp_min, float temp_max, int humidity) {
        this.id = id;
        this.date = date;
        this.icon = icon;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        this.humidity = humidity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(float temp_min) {
        this.temp_min = temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(float temp_max) {
        this.temp_max = temp_max;
    }
}
