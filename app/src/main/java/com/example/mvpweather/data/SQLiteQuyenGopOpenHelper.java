package com.example.mvpweather.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mvpweather.model.weather_note.Note;

import java.util.ArrayList;
import java.util.List;

public class SQLiteQuyenGopOpenHelper extends SQLiteOpenHelper {
    private  static  final String DATABASE_NAME = "Lists.db";
    private static  final int DATABASE_VERSION = 1;

    public SQLiteQuyenGopOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table lists(" +
                "id integer primary key autoincrement," +
                "date integer," +
                "icon text," +
                "tempmin real," +
                "tempmax real," +
                "humidity integer" +
                ")";
        db.execSQL(sql);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long addNote(Note note) {
        ContentValues values = new ContentValues();
        values.put("date", note.getDate());
        values.put("icon", note.getIcon());
        values.put("tempmin", note.getTemp_min());
        values.put("tempmax", note.getTemp_max());
        values.put("humidity", note.getHumidity());
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.insert("lists", null, values);
    }

    public List<Note> getAllNote() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("lists", null, null, null, null, null, null);
        List<Note> notes = new ArrayList<>();
        while(cursor.moveToNext()) {
            int id = cursor.getInt(0);
            long date = cursor.getLong(1);
            String icon = cursor.getString(2);
            float tmin = cursor.getFloat(3);
            float tmax = cursor.getFloat(4);
            int humidity = cursor.getInt(5);
            Note note = new Note(id,date,icon,tmin,tmax,humidity);
            notes.add(note);
        }
        return notes;
    }

    public int deleteNote(int id) {
        String whereClause = "id = ?";
        String[] args = { String.valueOf(id) };
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase.delete("lists", whereClause, args);
    }


}
