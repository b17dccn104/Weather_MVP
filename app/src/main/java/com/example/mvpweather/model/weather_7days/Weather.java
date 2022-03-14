package com.example.mvpweather.model.weather_7days;

import java.io.Serializable;

public class Weather implements Serializable {
    private String icon;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
