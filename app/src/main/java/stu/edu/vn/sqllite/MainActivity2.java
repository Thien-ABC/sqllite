package stu.edu.vn.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import stu.edu.vn.sqllite.model.Sach;

public class MainActivity2 extends AppCompatActivity {

    Sach chon;
    EditText txt_ten,txt_tacgia,txt_namxuatban;
    Button btnluu;
    int requestcode=113,resultcode=115;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        addControl();
        addEvent();
        getintentdata();
    }

    private void getintentdata() {
        Intent intent=getIntent();
        if(intent.hasExtra("chon")){
            chon=(Sach) intent.getSerializableExtra("chon");
            if(chon!=null){
                txt_ten.setText(chon.getTen());
                txt_tacgia.setText(chon.getTacgia());
                txt_namxuatban.setText(String.valueOf(chon.getNamxuatban()));
            }
        }
    }
    private void addEvent() {
        btnluu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xulyluu();
            }
        });
    }

    private void xulyluu() {
        if(chon==null){
            chon=new Sach();
        }
        chon.setTen(txt_ten.getText().toString());
        chon.setTacgia(txt_tacgia.getText().toString());
        chon.setNamxuatban(Integer.parseInt(txt_namxuatban.getText().toString()));
        Intent intent=getIntent();
        intent.putExtra("tra",chon);
        setResult(requestcode,intent);
        finish();


    }

    private void addControl() {
        btnluu=findViewById(R.id.btnLuu);
        txt_ten=findViewById(R.id.edit_ten);
        txt_tacgia=findViewById(R.id.edit_tacgia);
        txt_namxuatban=findViewById(R.id.edit_namxb);
        chon=null;
    }
}