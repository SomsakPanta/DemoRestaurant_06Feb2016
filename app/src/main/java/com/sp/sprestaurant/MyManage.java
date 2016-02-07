package com.sp.sprestaurant;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

/**
 * Created by 268771 on 6/2/2559.
 */
public class MyManage {

    //Explicit
    private MyOpenHelper objMyOpenHelper;
    private SQLiteDatabase writeSqLiteDatabase, readSqLiteDatabase;

    public static final String user_TABLE = "userTABLE";
    public static final String food_TABLE = "foodTABLE";

    public static final String column_id = "_id";
    public static final String column_user = "User";
    public static final String column_password = "Password";
    public static final String column_name = "Name";

    public static final String column_food = "Food";
    public static final String column_price = "Price";
    public static final String column_source = "Source";


    public MyManage(Context context) {

        //Create and Connect database
        objMyOpenHelper = new MyOpenHelper(context);
        writeSqLiteDatabase = objMyOpenHelper.getWritableDatabase();
        readSqLiteDatabase = objMyOpenHelper.getReadableDatabase();

    }// Constructor

    public String[] serchUser(String strUser) {

        try {

            String[] resultStrings = null;

            //Cursor คือ การ Copy Data จากข้อมูลที่เราหา ไปไว้ใน Ram
            //หา Table ไหน เอา Column อะไร และมีเงือนไขค้นหาจาก Column อะไร
            Cursor objCursor = readSqLiteDatabase.query(user_TABLE,
                    new String[]{column_id,column_user,column_password,column_name},
                    column_user + "=?",
                    new String[]{String.valueOf(strUser)},
                    null,null,null,null);

            if (objCursor != null) {
                if (objCursor.moveToFirst()) {
                    resultStrings = new String[4];
                    for (int i=0;i<4;i++) {
                        resultStrings[i] = objCursor.getString(i);
                    }
                }// if2
            }//If Main

            objCursor.close();
            return resultStrings;

        } catch (Exception e) {
            return null;
        }

        //return new String[0];
        //Alt Enter คือการ Get return ค่า
    }

    //Step 3.
    //Add method
    public long addNewValue(int intTABLE,
                            String strColumn2,
                            String strColumn3,
                            String strColumn4) {

        ContentValues objContentValues = new ContentValues();

        long longReturn = 0;


        switch (intTABLE) {

            case 0:

                objContentValues.put(column_user,strColumn2);
                objContentValues.put(column_password,strColumn3);
                objContentValues.put(column_name,strColumn4);
                //Add data to table user
                writeSqLiteDatabase.insert(user_TABLE, null, objContentValues);


                break;
            case 1:

                objContentValues.put(column_food,strColumn2);
                objContentValues.put(column_price,strColumn3);
                objContentValues.put(column_source,strColumn4);
                //Add data to table user
                writeSqLiteDatabase.insert(food_TABLE, null, objContentValues);

                break;
        } //switch

        return longReturn;
    }




}//Main class
