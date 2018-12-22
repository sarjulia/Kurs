package com.example.julia.booking;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class tables extends AppCompatActivity {

    ItemAd_Table tad;
    Context thisContext;
    ListView TablesView;
    List<TablesItem> myTables;

    Time startTime;
    Time endTime;
    Date day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        Resources res = getResources();
        TablesView = (ListView) findViewById(R.id.TableView);
        thisContext = this;

        Context context;

        myTables = new ArrayList<>();

        Button tablechosenBtn = (Button) findViewById(R.id.tablechosen);
        tablechosenBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                GetData retrievedata = new GetData();
                retrievedata.execute();

                //Intent startIntent = new Intent(getApplicationContext(),games.class);
                //startActivity(startIntent);
            }
        });
    }


    private class GetData extends AsyncTask<Void, Void, Void> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask

        }

        @Override
        protected Void doInBackground(Void... param) {

            try {

                String toParse = reciveJSONforQuery("SELECT * FROM Tables");
                List<Object> result = JSONtoList(toParse);

                for (int i = 0; i < result.size(); i += 1) {
                    LinkedHashMap item = (LinkedHashMap<String, Object>) result.get(i);
                    myTables.add(new TablesItem(i + 1, item.get("id"), item.get("RoomName"), item.get("MaxCapacity")));
                }


            } catch (IOException e) {
            }

          return null;
        }


        @Override
        protected void onPostExecute(Void smth) {


            if (myTables.size() > 0) {
                tad = new ItemAd_Table(getApplicationContext(), myTables);
                TablesView.setAdapter(tad);
            }
        }
    }

}