package com.sp.sprestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Explicit
    //Step 1.
    private MyManage objMyManage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Step 2.
        //Request database
        objMyManage = new MyManage(this);


        //Test Add value
        //testAddValue();

        //Clean data
        cleanData();


    } //Main Method

    private void cleanData() {
        SQLiteDatabase objSqLiteDatabase = openOrCreateDatabase(MyOpenHelper.database_name,
                MODE_PRIVATE,null);
        //MODE_PRIVATE อนุญาติให้ลบข้อมูล แต่ไม่อนุญาติให้ Drop table

        objSqLiteDatabase.delete(MyManage.food_TABLE, null, null);
        objSqLiteDatabase.delete(MyManage.user_TABLE, null, null);


    }// cleanData

    private void testAddValue() {
        for(int i=0;i<=1;i++) {

            objMyManage.addNewValue(i, "test1", "test2", "test3");


        }// for


    }// testAddValue


}// Main class
