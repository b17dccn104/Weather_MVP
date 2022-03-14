package com.example.mvpweather.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.mvpweather.model.weather_7days.Lists;
import com.example.mvpweather.ui.adapter.RecycleViewWeather16DayAdapter;
import com.example.mvpweather.ui.adapter.ViewPagerAdapter;
import com.example.mvpweather.R;
import com.example.mvpweather.ui.display_home.HomeFragment;
import com.example.mvpweather.ui.display_note.NoteFragment;
import com.example.mvpweather.ui.display_weather7day.Weather7DayFragment;
import com.example.mvpweather.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecycleViewWeather16DayAdapter.senDataToActiviTy {
    private RecycleViewWeather16DayAdapter rcv;
    private ViewPagerAdapter adapter;
    private List<Lists> list = new ArrayList<>();

   private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        rcv = new RecycleViewWeather16DayAdapter(this);
        adapter = new ViewPagerAdapter(
                getSupportFragmentManager(),
                FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
        );
        adapter.addFragment(new HomeFragment(), "HOME");
        adapter.addFragment(new Weather7DayFragment(), "7DAY");
        adapter.addFragment(new NoteFragment(), "NOTE");
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setOffscreenPageLimit(0); //load du lieu cho 0 tap tiep thep
        binding.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == 1){
                    Fragment fragment_weather7day = adapter.getFragmentByPosition(position);
                    Fragment fragment_home = adapter.getFragmentByPosition(0);
                    if (fragment_home instanceof HomeFragment) {
                        HomeFragment homeFragment = (HomeFragment) fragment_home;
                        String cityName = homeFragment.getCityName();
                        if (fragment_weather7day instanceof Weather7DayFragment){
                            Weather7DayFragment weather_7dayFragment = (Weather7DayFragment) fragment_weather7day;
                            weather_7dayFragment.setCityName(cityName);
                        }
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        binding.tapLayout.setupWithViewPager(binding.viewPager);
        binding.tapLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        binding.tapLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_next_week_24);
        binding.tapLayout.getTabAt(2).setIcon(R.drawable.ic_note);

    }

    @Override
    public void senData(Lists lists) {
        NoteFragment noteFragment = (NoteFragment) adapter.getFragmentByPosition(2);
        noteFragment.receiveDataFromFragment7Day(lists);
    }
}
