package com.example.mvpweather.ui.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mvpweather.R;
import com.example.mvpweather.data.SQLiteQuyenGopOpenHelper;
import com.example.mvpweather.databinding.ItemWeatherNoteBinding;
import com.example.mvpweather.model.weather_7days.Lists;
import com.example.mvpweather.model.weather_note.Note;
import com.example.mvpweather.ui.display_note.NoteFragment;
import com.example.mvpweather.utils.KeyTemF;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class RecycleViewNoteAdapter extends RecyclerView.Adapter<RecycleViewNoteAdapter.MyViewHolder> {
    private Context context;
    private List<Note> notes;
    private NoteFragment noteFragment;
    private SQLiteQuyenGopOpenHelper db;
    private DecimalFormat df = new DecimalFormat("#");

    public RecycleViewNoteAdapter(Context context, List<Note> notes) {
        this.context = context;
        this.notes = notes;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_weather_note,parent,false);
        db = new SQLiteQuyenGopOpenHelper(context);
        noteFragment = new NoteFragment();
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (notes == null){
            return;
        }
        holder.bindData(position);
        holder.binding.imageNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.deleteNote(notes.get(position).getId());
                notes.remove(notes.get(position));
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (notes != null){
            return notes.size();
        }
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        private ItemWeatherNoteBinding binding;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemWeatherNoteBinding.bind(itemView);
        }

        private void bindData(int position){
            Note note = notes.get(position);
            Date date = new Date(Long.valueOf(note.getDate()) * 1000L);
            SimpleDateFormat sp = new SimpleDateFormat("EE");
            binding.textviewHumidityNote.setText(String.valueOf(note.getHumidity()));
            binding.textviewTempminNote.setText(String.valueOf(df.format(note.getTemp_min()- KeyTemF.TEMF)));
            binding.textviewTempmaxNote.setText(String.valueOf(df.format(note.getTemp_max()- KeyTemF.TEMF)));
            binding.textviewDateNote.setText(sp.format(date));
            String icon = note.getIcon();
            Glide.with(context)
                    .load("http://openweathermap.org/img/wn/" + icon + ".png").into(binding.imagewviewIconNote);
        }
    }
}
