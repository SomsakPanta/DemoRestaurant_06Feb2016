package com.sp.sprestaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by 268771 on 6/2/2559.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;


    public MyManage(Context context) {

        //Create and Connect database
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }// Constructor

}//Main class
