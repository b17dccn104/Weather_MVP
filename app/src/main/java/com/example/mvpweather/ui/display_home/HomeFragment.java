package com.example.mvpweather.ui.display_home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.bumptech.glide.Glide;
import com.example.mvpweather.model.weather_current.WeatherCurrent;
import com.example.mvpweather.databinding.FragmentHomeBinding;
import com.example.mvpweather.utils.KeyConstants;
import com.example.mvpweather.utils.KeyTemF;
import com.example.mvpweather.utils.KeyboardUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment implements HomeInterface{
    private FragmentHomeBinding binding;
    private View view;
    private DecimalFormat df = new DecimalFormat("#");
    private HomePresenter homePresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        homePresenter = new HomePresenter(this);

    }

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        selectDown();
        setCityDefault("Hanoi");
        clickListener();
    }

    private void clickListener() {
            binding.buttonSearch.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sentDataToHomePresenter();
                    updateViewSearch();
                }
            });
    }

    private void setCityDefault(String city) {
        homePresenter.getDataByFragmentHome(city, KeyConstants.APIKEY);
    }

    private void sentDataToHomePresenter() {
        String city = binding.edittextSearch.getText().toString().trim();
        homePresenter.getDataByFragmentHome(city, KeyConstants.APIKEY);
    }

    private void updateViewSearch(){
        binding.groudSearch.setVisibility(View.GONE);
        binding.groupCity.setVisibility(View.VISIBLE);
        KeyboardUtils.hideKeyboardFrom(getContext(), view);
        binding.edittextSearch.setText("");
    }

    public String getCityName(){
        if(binding != null){
            return binding.textviewCity.getText().toString();
        }
        return "";
    }

    private void selectDown() {
            binding.imageDown.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    binding.groupCity.setVisibility(View.GONE);
                    binding.groudSearch.setVisibility(View.VISIBLE);
                }
            });
    }

    // nguyeen ly S.O.L.I.D

    @Override
    public void onSuccess(WeatherCurrent data) {
        if (data.getName() != null && binding != null){
            binding.textviewCity.setText(data.getName());
            Date date = new Date(Long.valueOf(data.getDt()) * 1000L);
            SimpleDateFormat sp = new SimpleDateFormat("EE yyyy-MM-dd HH-mm-ss");
            binding.textviewCurrentTime.setText(sp.format(date));
            binding.textviewTemperature.setText(String.valueOf(df.format((data.getMain().getTemp()) - KeyTemF.TEMF)));
            binding.textviewTempFeels.setText(String.valueOf(df.format((data.getMain().getFeels_like()) - KeyTemF.TEMF)));
            binding.textviewDetailHumidity.setText(df.format(data.getMain().getHumidity()));
            binding.textviewDetailWindspeed.setText(String.valueOf(data.getWind().getSpeed()));
            binding.textviewDescription.setText(data.getWeather().get(0).getDescription().toString());
            String icon = data.getWeather().get(0).getIcon();
            Glide.with(getContext())
                    .load("http://openweathermap.org/img/wn/" + icon + ".png").into(binding.imageviewIconWeather);
        } else return;
    }

    @Override
    public void onFailed(String error) {
        if (error != null) {
            Toast.makeText(getContext(), error, Toast.LENGTH_SHORT).show();
        } else return;
    }
}

