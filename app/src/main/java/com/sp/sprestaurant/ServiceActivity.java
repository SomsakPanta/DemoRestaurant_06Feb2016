package com.sp.sprestaurant;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.security.PrivateKey;

public class ServiceActivity extends AppCompatActivity {

    //Explicit
    private TextView showNameTextView;
    private Spinner deskSpinner;
    private ListView foodListView;

    private String officerString,deskString,foodString, amoungString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        //Bind widget
        bindWidget();

        //Show view
        showView();

        //show Desk
        showDesk();

    }//Main Method

    private void showDesk() {
        final String[] showDeskStrings = {"โต๊ะที่ 1","โต๊ะที่ 2","โต๊ะที่ 3","โต๊ะที่ 4","โต๊ะที่ 5",
                "โต๊ะที่ 6","โต๊ะที่ 7","โต๊ะที่ 8","โต๊ะที่ 9","โต๊ะที่ 10"};

        ArrayAdapter<String> deskArrayAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, showDeskStrings);
        deskSpinner.setAdapter(deskArrayAdapter);

        //setOnItemSelectedListener โดยการทำ callback
        deskSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                deskString = showDeskStrings[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                deskString = showDeskStrings[0];

            }
        });

    }//showDesk

    private void showView() {

        //Receive from Intent

        //getStringExtra ต้องตรงกับ objIntent.putExtra("Name", resultStrings[3])
        // ที่อยู่หน้า Main Activity
        officerString = getIntent().getStringExtra("Name");
        showNameTextView.setText(officerString);

    } //showView

    private void bindWidget() {

        //นำค่าจากหน้า Activity_service มา Bide ให้กับตัวแปรที่ประกาศไว้ด้านบน

        showNameTextView = (TextView) findViewById(R.id.textView2);
        deskSpinner = (Spinner) findViewById(R.id.spinner);
        foodListView = (ListView) findViewById(R.id.listView);


    }//  bindWidget

}//Main class
