package com.example.mvpweather.ui.display_weather7day;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvpweather.ui.adapter.RecycleViewWeather16DayAdapter;
import com.example.mvpweather.model.weather_7days.Weather7day;
import com.example.mvpweather.databinding.FragmentWeather7dayBinding;
import com.example.mvpweather.utils.KeyConstants;

public class Weather7DayFragment extends Fragment implements Weather7DayInterface{
    private FragmentWeather7dayBinding binding;
    private View view;
    private Weather7DayPresenter weather7DayPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        weather7DayPresenter = new Weather7DayPresenter(this);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWeather7dayBinding.inflate(inflater,container,false);
        view = binding.getRoot();
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setCityDeFault("Hanoi");
    }

    private void setCityDeFault(String city) {
        weather7DayPresenter.getDataByWeather7DayFragment(city, KeyConstants.APIKEY);
    }

    private void setDataFragmentWeather7Day(Weather7day weather7day){
            binding.textviewCity16day.setText(weather7day.getCity().getName());
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL,false);
            binding.rcv16day.setLayoutManager(linearLayoutManager);
            RecycleViewWeather16DayAdapter adapter = new RecycleViewWeather16DayAdapter(weather7day.getList(),getActivity());
            binding.rcv16day.setAdapter(adapter);
    }

    public void setCityName(String cityName){
        if (binding != null){
           setCityDeFault(cityName);
        }
    }

    @Override
    public void onSuccess(Weather7day data) {
        if (data != null) {
            setDataFragmentWeather7Day(data);
        } else return;
    }

    @Override
    public void onFailed(String error) {
        if (error != null){
            Toast.makeText(getContext(),error,Toast.LENGTH_SHORT).show();
        } else return;
    }
}