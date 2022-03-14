package com.example.mvpweather.ui.display_weather7day;
import com.example.mvpweather.model.weather_7days.Weather7day;
import com.example.mvpweather.service.APIService;
import com.example.mvpweather.service.Dataservice;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Weather7DayPresenter {
    private Weather7DayInterface weather7DayInterface;

    public Weather7DayPresenter(Weather7DayInterface weather7DayInterface) {
        this.weather7DayInterface = weather7DayInterface;
    }

    public void getDataByWeather7DayFragment(String city, String apiKey){
        Dataservice dataservice = APIService.getService();
        Call<Weather7day> callback = dataservice.getDataWeather7Day(city, apiKey);
        callback.enqueue(new Callback<Weather7day>() {
            @Override
            public void onResponse(Call<Weather7day> call, Response<Weather7day> response) {
                Weather7day weather7day = response.body();
                if (weather7day != null){
                    weather7DayInterface.onSuccess(weather7day);
                } else {
                    weather7DayInterface.onFailed("Error");
                }
            }

            @Override
            public void onFailure(Call<Weather7day> call, Throwable t) {

            }
        });
    }
}
