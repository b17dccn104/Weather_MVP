package com.example.mvpweather.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvpweather.model.weather_7days.Lists;
import com.example.mvpweather.R;
import com.example.mvpweather.databinding.ItemWeather16dayBinding;
import com.example.mvpweather.ui.MainActivity;
import com.example.mvpweather.utils.KeyTemF;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RecycleViewWeather16DayAdapter extends RecyclerView.Adapter<RecycleViewWeather16DayAdapter.MyViewHolder> {
    private Context context;
    private List<Lists> list_Lists ;
    private senDataToActiviTy senDataToActiviTy;
    private DecimalFormat df = new DecimalFormat("#");

    public RecycleViewWeather16DayAdapter(List<Lists> list, Context context){
        this.list_Lists = list;
        this.context = context;
    }

    public interface senDataToActiviTy{
        void senData(Lists lists);
    }

    public RecycleViewWeather16DayAdapter(senDataToActiviTy senDataToActiviTy){
        this.senDataToActiviTy = senDataToActiviTy;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_16day,parent,false);
        senDataToActiviTy = (senDataToActiviTy) context;
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (list_Lists == null){
            return;
        }
        holder.bindData(position);
        holder.binding.imageNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senDataToActiviTy.senData(list_Lists.get(position));
                holder.binding.imageNote.setImageResource(R.drawable.ic_note_change);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (list_Lists != null){
            return list_Lists.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemWeather16dayBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemWeather16dayBinding.bind(itemView);
        }

        public void bindData(int position) {
            Lists lists = list_Lists.get(position);
            Date date = new Date(Long.valueOf(lists.getDt()) * 1000L);
            SimpleDateFormat sp = new SimpleDateFormat("EE");
            binding.textviewHumidity16day.setText(String.valueOf(lists.getMain().getHumidity()));
            binding.textviewTempmin16day.setText(String.valueOf(df.format(lists.getMain().getTemp_min()- KeyTemF.TEMF)));
            binding.textviewTempmax16day.setText(String.valueOf(df.format(lists.getMain().getTemp_max()- KeyTemF.TEMF)));
            binding.textviewDate16day.setText(sp.format(date));
            String icon = lists.getWeather().get(0).getIcon();
            Glide.with(context)
                    .load("http://openweathermap.org/img/wn/" + icon + ".png").into(binding.imagewviewIcon16day);
        }
    }

}
