package com.sava.test_demo1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class SongAdapter  extends ArrayAdapter<Song> {
    private Context mContext;
    private int mResource;
    private ArrayList<Song> mListSong;
    public  SongAdapter(Context context, int  resource , ArrayList<Song> listSong){
        super(context,resource,listSong);
        this.mContext = context;
        this.mResource = resource;
        this.mListSong = listSong;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_song,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvTen = (TextView) convertView.findViewById(R.id.tv_ten);
            viewHolder.tvCaSi = (TextView) convertView.findViewById(R.id.tv_casi);
            viewHolder.tvThoiLuong = (TextView) convertView.findViewById(R.id.tv_thoiLuong);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Song song = mListSong.get(position);
        viewHolder.tvTen.setText(song.getmTen());
        viewHolder.tvCaSi.setText(song.getmCaSi());
        viewHolder.tvThoiLuong.setText(String.valueOf(song.getmThoiLuong()));
        return convertView;


    }

    public class ViewHolder{
        TextView tvTen;
        TextView tvCaSi;
        TextView tvThoiLuong;
    }
}
