package com.sava.test_demo1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Database extends SQLiteOpenHelper {
    private static final  String DATABASE_NAME ="MY_DATABASE";
    private static final String TABLE_NAME = "SONG";
    private static final String ID_SONG = "id_song";
    private static final String TEN_SONG = "ten_song";
    private static final String CASI_SONG = "casi_song";
    private static final String THOILUONG_SONG = "thoiLuong_song";
    public Database(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String craeteTableSong = " CREATE TABLE " + TABLE_NAME + "( " +
                ID_SONG + " integer PRIMARY KEY AUTOINCREMENT ," +
                TEN_SONG + " text, " +
                CASI_SONG + " text, " +
                THOILUONG_SONG + " int )";
        sqLiteDatabase.execSQL(craeteTableSong);
        this.insertSong(sqLiteDatabase,new Song("Phut Cuoi","Bang Kieu",200));
        this.insertSong(sqLiteDatabase,new Song("Bong Hong Thuy Tinh","Buc Tuong",200));
        this.insertSong(sqLiteDatabase,new Song("Ha Noi Mua Thu","My Linh",200));
        this.insertSong(sqLiteDatabase,new Song("Ba Toi","Tung Duong",200));
        this.insertSong(sqLiteDatabase,new Song("Dem Dong","Bang Kieu",200));
        this.insertSong(sqLiteDatabase,new Song("Goi Hong","Quang Dung",200));
    }
    public ArrayList<Song> getAllSong(){
        ArrayList<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Song song =new Song();
                song.setmID(cursor.getInt(0));
                song.setmTen(cursor.getString(1));
                song.setmCaSi(cursor.getString(2));
                song.setmThoiLuong(cursor.getInt(3));
                list.add(song);
            }while (cursor.moveToNext());
        }
        return list;
    }
    public ArrayList<Song> getAllSongByCasi(String casi){
        ArrayList<Song> list = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME + " WHERE " + CASI_SONG + " LIKE '" +casi + "%'" ;
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                Song song =new Song();
                song.setmID(cursor.getInt(0));
                song.setmTen(cursor.getString(1));
                song.setmCaSi(cursor.getString(2));
                song.setmThoiLuong(cursor.getInt(3));
                list.add(song);
            }while (cursor.moveToNext());
        }
        return list;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void insertSong(SQLiteDatabase db, Song song){
        if(db==null){
            db = getWritableDatabase();
        }
        ContentValues values = new ContentValues();
        values.put(TEN_SONG,song.getmTen());
        values.put(CASI_SONG,song.getmCaSi());
        values.put(THOILUONG_SONG,song.getmThoiLuong());
        db.insert(TABLE_NAME,null,values);
    }
    public void deleteSong(Song song){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, TEN_SONG + " = ?", new String[]{song.getmTen()});
        db.close();
    }
}
