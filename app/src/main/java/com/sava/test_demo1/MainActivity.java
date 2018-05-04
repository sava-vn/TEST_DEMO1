package com.sava.test_demo1;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static int REQUEST_ADD =1;
    private ListView lvSong;
    private Database database;
    private SongAdapter adapter;
    private ArrayList<Song> listSong;
    private FloatingActionButton fab;
    private EditText edtSearch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidget();
        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                listSong.clear();
                listSong.addAll(database.getAllSongByCasi(edtSearch.getText().toString()));
                adapter.notifyDataSetChanged();
            }
        });
        lvSong.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Thông báo");
                builder.setMessage("Bạn có chắc chắn muốn xóa !");
                builder.setPositiveButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        database.deleteSong(listSong.get(position));
                        listSong.clear();
                        listSong.addAll(database.getAllSongByCasi(edtSearch.getText().toString()));
                        adapter.notifyDataSetChanged();
                        dialogInterface.dismiss();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        //Thêm mới Song
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,AddActivity.class);
                startActivityForResult(intent,REQUEST_ADD);
            }
        });
    }
    private void initWidget(){
        edtSearch = (EditText) findViewById(R.id.edt_search);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        lvSong = (ListView) findViewById(R.id.lv_song);
        database = new Database(this);
        listSong= database.getAllSong();
        adapter = new SongAdapter(this,R.layout.item_song,listSong);
        lvSong.setAdapter(adapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQUEST_ADD&&resultCode==AddActivity.RESULT_MAIN){
            Song song = new Song();
            song.setmTen(data.getExtras().getString("TEN"));
            song.setmCaSi(data.getExtras().getString("CASI"));
            song.setmThoiLuong(Integer.valueOf(data.getExtras().getString("THOILUONG")));
            listSong.clear();
            database.insertSong(null,song);
            listSong.addAll(database.getAllSong());
            adapter.notifyDataSetChanged();
        }
    }
}
