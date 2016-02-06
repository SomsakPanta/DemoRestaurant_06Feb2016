package com.sp.sprestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 268771 on 6/2/2559.
 */

//impliment SQLiteOpenHelper โดยการกด Alt Enter

public class MyOpenHelper extends SQLiteOpenHelper{

    //Explicit
    //final คือตัวแปรที่ประกาศแล้วไม่สามารถแก้ไขค่าของมันได้
    public static final String database_name = "Restaurant.db";
    private static final int database_version = 1;
    private static final String create_user_table = "create table userTABLE(" +
            "_id integer primary key, " +
            "User text, " +
            "Password text, " +
            "Name text);";

    private static final String create_food_table = "create table foodTABLE(" +
            "_id integer primary key, " +
            "Food text, " +
            "Price text, " +
            "Source text);";


    //Alt Insert for build Constructure
    //หลังจาก Imprement MyOpenHelper แล้วต้องสร้าง super
    //เอา curson ไว้ที่ รหว่าง Context กับ context แล้ว Alt Enter
    //ใส่ตัวแปรเข้าไป

    public MyOpenHelper(Context context) {
        super(context,database_name,null,database_version);

    }//Constructure


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(create_user_table);
        db.execSQL(create_food_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}//Main class
