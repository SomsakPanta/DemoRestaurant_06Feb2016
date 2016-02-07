package com.sp.sprestaurant;

import android.database.sqlite.SQLiteDatabase;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

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

        //Synchronize JSON to SQLite
        synJSONtoSQLite();

    } //Main Method

    private void synJSONtoSQLite() {

        //Change policy
        StrictMode.ThreadPolicy myThreadPolicy = new StrictMode.ThreadPolicy
                .Builder().permitAll().build();
        //กำหนด StrictMode ให้กับ policy ที่เรากำหนดเอง
        StrictMode.setThreadPolicy(myThreadPolicy);

        int intTable = 1;
        String tag = "Restaurant";

        
        //การ Syn มีขึ้นตอน 3 Step
        while (intTable <= 2) {

            //1. Create InputStream
            InputStream objInputStream = null;
            String strURLuser = "http://swiftcodingthai.com/6feb/php_get_data_sp.php";
            String strURLfood = "http://swiftcodingthai.com/6feb/php_get_data_food.php";

            HttpPost objHttpPost = null;

            try {

                HttpClient objHttpClient = new DefaultHttpClient();
                switch (intTable) {
                    case 1:
                        objHttpPost = new HttpPost(strURLuser);
                        break;
                    case 2:
                        objHttpPost = new HttpPost(strURLfood);
                        break;
                    default:
                        break;
                }// switch

                //This is input InputStream from HttpPost to HttpResponse
                HttpResponse objHttpResponse = objHttpClient.execute(objHttpPost);
                HttpEntity objHttpEntity = objHttpResponse.getEntity();
                objInputStream = objHttpEntity.getContent();


            } catch (Exception e) {
                Log.d(tag, "InputStream ==>" + e.toString());
            }


            //2. Create JSON String
            String strJSON = null;
            try {
                //ทำหน้าที่อ่านข้อมูลจาก objInputStream ต้องเข้ารหัสให้สามารถอ่านภาษาไทยได้โดยใช้การเข้ารหัส UTF-8
                //ต้องประกาศ BufferedReader เพื่อรับ InputStream
                BufferedReader objBufferedReader = new BufferedReader(new InputStreamReader(objInputStream,"UTF-8"));

                //StringBuilder ทำหน้าที่ผูก InputStream เป็นชิ้นเดียวกัน
                StringBuilder objStringBuilder = new StringBuilder();

                String strLine = null;
                while ((strLine = objBufferedReader.readLine())  != null) {
                    objStringBuilder.append(strLine);
                } //while

                objInputStream.close();
                strJSON = objStringBuilder.toString();


            } catch (Exception e) {
                Log.d(tag, "strJSON ==>" + e.toString());
            }

            //3. Update SQLite
            try {
                //อ่านข้อมูล Format Json
                JSONArray objJsonArray = new JSONArray(strJSON);

                for (int i=0;i<objJsonArray.length();i++) {
                    JSONObject jsonObject = objJsonArray.getJSONObject(i);

                    switch (intTable) {
                        case 1:

                            //Get value from userTable

                            String strUser = jsonObject.getString(MyManage.column_user);
                            String strPassword = jsonObject.getString(MyManage.column_password);
                            String strName = jsonObject.getString(MyManage.column_name);

                            objMyManage.addNewValue(0, strUser, strPassword, strName);

                            break;
                        case 2:

                            //Get value from foodTable
                            String strFood = jsonObject.getString(MyManage.column_food);
                            String strPrice = jsonObject.getString(MyManage.column_price);
                            String strSource = jsonObject.getString(MyManage.column_source);

                            objMyManage.addNewValue(1, strFood, strPrice, strSource);

                            break;
                    }// switch

                }// for

            } catch (Exception e) {
                Log.d(tag, "Update ==>" + e.toString());
            } // try


            intTable += 1;

        } // while

    }// synJSONtoSQLite

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
