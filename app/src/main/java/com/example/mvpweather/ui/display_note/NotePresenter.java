package com.example.mvpweather.ui.display_note;

import android.content.Context;

import com.example.mvpweather.data.SQLiteNoteOpenHelper;
import com.example.mvpweather.model.weather_note.Note;

import java.util.List;

public class NotePresenter {
    private SQLiteNoteOpenHelper db;
    private NoteInterface noteInterface;
    private Context context;

    public NotePresenter(NoteInterface noteInterface, Context context) {
        this.context = context;
        this.noteInterface = noteInterface;
    }

    public void getListNote(Note note) {
        db = new SQLiteNoteOpenHelper(context);
        db.addNote(note);
        List<Note> notes = db.getAllNote();
        if (notes != null){
            noteInterface.onSuccess(notes);
        } else {
            noteInterface.onFailed("Error");
        }
    }
}
