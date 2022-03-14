package com.example.mvpweather.model.weather_current;

public class Main {
    private float temp;
    private float feels_like;
    private float humidity;

    public Main() {
    }

    public Main(float temp, float feels_like, float humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getFeels_like() {
        return feels_like;
    }

    public void setFeels_like(float feels_like) {
        this.feels_like = feels_like;
    }

    public float getHumidity() {
        return humidity;
    }

    public void setHumidity(float humidity) {
        this.humidity = humidity;
    }
}
