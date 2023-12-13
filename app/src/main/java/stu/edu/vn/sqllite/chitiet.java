package stu.edu.vn.sqllite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import stu.edu.vn.sqllite.model.Sach;

public class chitiet extends AppCompatActivity {

    Sach chon;
    TextView ten,tacgia,namsanxuat;
    Button trove;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet);
        addcontrol();
        addEvent();
        getdata();
    }

    private void getdata() {
        Intent intent=getIntent();
        if(intent.hasExtra("chitiet")&&chon==null){
            chon= (Sach) intent.getSerializableExtra("chitiet");
            ten.setText(chon.getTen());
            tacgia.setText(chon.getTacgia());
            namsanxuat.setText(String.valueOf(chon.getNamxuatban()));

        }
    }

    private void addEvent() {
        trove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chon=null;
                finish();
            }
        });

    }

    private void addcontrol() {
        trove=findViewById(R.id.btntrove);
        ten=findViewById(R.id.tvten);
        tacgia=findViewById(R.id.tvtacgia);
        namsanxuat=findViewById(R.id.tvnamsanxuat);
        chon=null;
    }

}