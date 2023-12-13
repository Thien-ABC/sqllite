package stu.edu.vn.sqllite.dao;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;



import java.util.ArrayList;
import java.util.List;

import stu.edu.vn.sqllite.model.Sach;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DB_Name = "dbsach.sqlite";
    private static final int DB_Version = 1;

    public static class TABLE implements BaseColumns {
        public static final String TABLE_NAME = "SACH";
        public static final String COLUMN_M = "MA";
        public static final String COLUMN_Ten = "TEN";
        public static final String COLUMN_Tacgia = "TACGIA";
        public static final String COLUMN_namxuatban = "NAMXUATBAN";
    }


    public static final String SQL_Create_Table = "CREATE TABLE IF NOT EXISTS " + TABLE.TABLE_NAME + " (" + TABLE.COLUMN_M + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TABLE.COLUMN_Ten + " VARCHAR(50), " + TABLE.COLUMN_Tacgia + " VARCHAR(50)," + TABLE.COLUMN_namxuatban + " INTEGER)";

    public  static  final String Drop_table = "DROP TABLE IF EXISTS " + TABLE.TABLE_NAME;


    public void QueryData(String sql)
    {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL(sql);
    }
    public DBHelper(Context context) {
        super(context, DB_Name, null, DB_Version);
    }


    public void insertSach(Sach sach) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DBHelper.TABLE.COLUMN_Ten, sach.getTen());
        values.put(DBHelper.TABLE.COLUMN_Tacgia, sach.getTacgia());
        values.put(DBHelper.TABLE.COLUMN_namxuatban, sach.getNamxuatban());

        long newRow = db.insert(TABLE.TABLE_NAME, null, values);
    }

    public List<Sach> getAllSach() {
        List<Sach> ListSach = new ArrayList<>();

        String[] projection = {
                TABLE.COLUMN_M,
                TABLE.COLUMN_Ten,
                TABLE.COLUMN_Tacgia,
                TABLE.COLUMN_namxuatban
        };
        Cursor cursor = getReadableDatabase().query(
                TABLE.TABLE_NAME,
                projection,
                null,
                null,
                null,
                null,
                null
        );

        while (cursor.moveToNext()) {
            Sach sach = new Sach();
            sach.setMa(cursor.getInt(cursor.getColumnIndexOrThrow(TABLE.COLUMN_M)));
            sach.setTen(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_Ten)));
            sach.setTacgia(cursor.getString(cursor.getColumnIndexOrThrow(TABLE.COLUMN_Tacgia)));
            sach.setNamxuatban(cursor.getInt(cursor.getColumnIndexOrThrow(TABLE.COLUMN_namxuatban)));
            ListSach.add(sach);
        }

        cursor.close();
        return ListSach;
    }




    public Cursor getData(String sql)
    {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public boolean a;


    Context context;

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldver ,int newver) {

    }

    public void deleteSach(String Ma)
    {
        String selection = TABLE.COLUMN_M + " = ?";
        String[] selectionArgs ={Ma};
        int deleteRows =getWritableDatabase().delete(TABLE.TABLE_NAME, selection, selectionArgs);
    }

    public void updateSach(Sach sach)
    {
        ContentValues values = new ContentValues();

        values.put(TABLE.COLUMN_Ten, sach.getTen());
        values.put(TABLE.COLUMN_Tacgia, sach.getTacgia());
        values.put(TABLE.COLUMN_namxuatban, sach.getNamxuatban());
        String selection =TABLE.COLUMN_M + " =?";
        String[] selectionArgs = {sach.getMa()+""};
        int updateRows=getWritableDatabase().update(TABLE.TABLE_NAME, values,selection,selectionArgs);
    }

}
