package com.example.julia.booking;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Context;
import android.widget.TextView;
import android.widget.ListView;
import android.os.AsyncTask;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.sql.Statement;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class Events extends AppCompatActivity {

    ItemAd_Event evad;
    Context thisContext;
    ListView EventsView;
    TextView progressTextView;
    List<EventItem> myEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Resources res = getResources();
        EventsView = (ListView) findViewById(R.id.EventsView);
        progressTextView = (TextView) findViewById(R.id.progressTextView);
        thisContext = this;

        myEvent = new ArrayList<>();



        progressTextView.setText("");
        GetData retrievedata = new GetData();
        retrievedata.execute("");

        Button eventchosenBtn = (Button)findViewById(R.id.eventchosen);
        eventchosenBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),host.class);
                startActivity(startIntent);
            }
        });
    }

    private  class GetData extends AsyncTask< String,Void ,String>
    {String msg = "";


        @Override
        protected void onPreExecute()
        {
            progressTextView.setText("Connecting ...");
        }

        @Override
        protected String doInBackground(String... param)
        {

            try {

                String toParse = reciveJSONforQuery("SELECT * FROM Events");
                List<Object> result = JSONtoList(toParse);

                for (int i =0 ; i < result.size(); i+=1)
                {
                    LinkedHashMap item = (LinkedHashMap<String,Object>) result.get(i);
                    myEvent.add(new EventItem(i+1,item.get("name"),item.get("description"),item.get("currentAmountOfPeople"),item.get("maxAmountOfPeople"),item.get("Date"),item.get("beginTime"),item.get("endTime")));
                }

                msg = "complete";

            } catch (IOException e) {
            }

            return msg;
        }




        @Override
        protected void onPostExecute(String msg)
        {
            progressTextView.setText(msg);

            if (myEvent.size() > 0 )
            {
                evad = new ItemAd_Event(getApplicationContext(), myEvent );
                EventsView.setAdapter(evad);
            }
        }
    }

}