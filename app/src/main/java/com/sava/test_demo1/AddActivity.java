package com.sava.test_demo1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    public static int RESULT_MAIN =2;
    private EditText edtTen;
    private EditText edtCasi;
    private EditText edtThoiLuong;
    private Button btnHuy;
    private Button btnLuu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initWidget();
    }
    private void initWidget(){
        edtTen = (EditText) findViewById(R.id.edt_ten);
        edtCasi = (EditText) findViewById(R.id.edt_casi);
        edtThoiLuong = (EditText) findViewById(R.id.edt_ThoiLuong);
        btnHuy = (Button) findViewById(R.id.btn_huy);
        btnLuu = (Button) findViewById(R.id.btn_luu);
    }
    public void onMyClick(View view){
        if(view.getId()==R.id.btn_huy){
            this.finish();
        }else{
            Intent intent = new Intent();
            intent.putExtra("TEN",edtTen.getText().toString());
            intent.putExtra("CASI",edtCasi.getText().toString());
            intent.putExtra("THOILUONG",edtThoiLuong.getText().toString());
            setResult(RESULT_MAIN,intent);
            this.finish();
        }
    }
}
