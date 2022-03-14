package com.example.mvpweather.ui.display_note;

import com.example.mvpweather.model.weather_note.Note;

import java.util.List;

public interface NoteInterface {
    void onSuccess(List<Note> notes);
    void onFailed(String error);
}
