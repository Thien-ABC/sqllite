package stu.edu.vn.sqllite;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import stu.edu.vn.sqllite.adapter.AdapterSach;
import stu.edu.vn.sqllite.dao.DBHelper;
import stu.edu.vn.sqllite.model.Sach;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fa;

    ListView listView;
    DBHelper helper;
    Toolbar toolbar;
    List<Sach> listsach =new ArrayList<>();

    AdapterSach adapter;
    Sach chon;
    int requestcode=113,resultcode=115;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        helper = new DBHelper(MainActivity.this);
        helper.QueryData(DBHelper.SQL_Create_Table);
        addEvent();
        hienthisach();
        chon=null;
    }

    private void addEvent() {
        fa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, MainActivity2.class);
                startActivityForResult(intent,requestcode);
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent=new Intent(MainActivity.this, chitiet.class);
                chon=adapter.getItem(position);
                intent.putExtra("chitiet",chon);
                startActivity(intent);
            }
        });
    }

    private void addControls()
    {


        fa=findViewById(R.id.faThem);
        listView = findViewById(R.id.lvQlsach);
        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        if(v.getId()==R.id.lvQlsach){
            getMenuInflater().inflate(R.menu.context_menu,menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index=info.position;
        if(item.getItemId()==R.id.btnsua)
        {
            Intent intent=new Intent(MainActivity.this,MainActivity2.class);
            chon=adapter.getItem(index);
            intent.putExtra("chon",chon);
            startActivityForResult(intent,requestcode);
        }
        else if(item.getItemId()==R.id.btnxoa){
            chon=adapter.getItem(index);
            helper.deleteSach(chon.getMa()+"");
            hienthisach();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==this.requestcode){

            if(data.hasExtra("tra")){
                Sach s= (Sach) data.getSerializableExtra("tra");
                if(s.getMa()==0){
                    helper.insertSach(s);
                }
                else {
                    helper.updateSach(s);
                }
                hienthisach();
            }
        }
    }

    private void hienthisach() {

        listsach = (ArrayList<Sach>) helper.getAllSach();

        adapter = new AdapterSach(
                MainActivity.this,
                R.layout.item_sach,
                listsach,
                helper
        );
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onDestroy() {
        helper.close();
        super.onDestroy();
    }
}