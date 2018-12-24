package com.example.julia.booking;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
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
    //Context thisContext;
    ListView TablesView;
    List<TablesItem> myTables;
    TablesItem chosen;
    EditText bookdate_tab,starttime_tab,endtime_tab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);
        bookdate_tab = (EditText)findViewById(R.id.bookdata);
        starttime_tab = (EditText)findViewById(R.id.bookstart);
        endtime_tab = (EditText)findViewById(R.id.bookend);
        Resources res = getResources();
        TablesView = (ListView) findViewById(R.id.TableView);
        //thisContext = this;

        Context context;

        myTables = new ArrayList<>();



    }

    public void viewTables(View view)
    {
        String day_tab = bookdate_tab.getText().toString();
        String start_tab = starttime_tab.getText().toString();
        String end_tab  = endtime_tab.getText().toString();
        GetData retrievedata = new GetData();
        retrievedata.execute(day_tab,start_tab,end_tab);

    }
    private class GetData extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask


        }

        @Override
        protected String doInBackground(String...param) {

            String day = param[0];
            String start = param[1];
            String end = param[2];

            try {


                 String toParse = reciveJSONforQuery("select id, maxCapacity, roomName from (select t.id id, t.maxCapacity maxCapacity, t.roomName roomName ,count(case when ('"+ start+ "'>b.beginTime and '"+ start+"'<b.endTime or '"+ end+"'>b.beginTime and '"+ end+"'<b.endTime or '"+ start+"'<b.beginTime and '"+ end+"'>b.endTime) and '"+ day+"'=b.bookingDate then 1 end) book from Tables t left join Booking b on t.id=b.tableId group by t.id) x where book=0 order by id;");
                List<Object> result = JSONtoList(toParse);

                for (int i = 0; i < result.size(); i += 1) {
                    LinkedHashMap item = (LinkedHashMap<String, Object>) result.get(i);
                    myTables.add(new TablesItem(i + 1, item.get("id"), item.get("roomName"), item.get("maxCapacity")));
                }


            } catch (IOException e) {
            }

          return "";
        }


        @Override
        protected void onPostExecute(String smth) {


            if (myTables.size() > 0) {
                tad = new ItemAd_Table(getApplicationContext(), myTables);
                TablesView.setAdapter(tad);

                TablesView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                {
                                                    public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                    {
                            chosen = myTables.get(position);
                            AlertDialog.Builder a_build = new AlertDialog.Builder(tables.this);
                            a_build.setMessage("Do you want to book table "+ chosen.getTableid().toString() + " ?")
                                    .setCancelable(false)
                                    .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // inserting here
                                            goAdd retrievedata = new goAdd();
                                            retrievedata.execute();
                                            //closing here
                                            dialog.cancel();
                                            Intent startIntent = new Intent(getApplicationContext(),games.class);
                                            startActivity(startIntent);
                                        }
                                    })
                                    .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialog.cancel();
                                        }
                                    });
                            AlertDialog choice = a_build.create();
                            choice.setTitle("Comfirmation of choice");
                            choice.show();
                        }
                    }
                );

            }
        }
    }
    private class goAdd extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... param) {

            try {

                String toParse = reciveJSONforQuery("insert into Booking (roomName, tableId, userLogin, bookingDate, beginTime, endTime) values ('"+chosen.getRoomName()+"', "+chosen.getTableid()+", '"+currentUserData.getLogin()+"', '"+bookdate_tab.getText()+"', '"+starttime_tab.getText()+"', '"+endtime_tab.getText()+"');");
                currentUserData.booking_id = chosen.getTableid().toString();
                currentUserData.booking_name = chosen.getRoomName().toString();
                currentUserData.booking_stDate = bookdate_tab.getText().toString();
                currentUserData.booking_stTime = starttime_tab.getText().toString();
                currentUserData.booking_login = currentUserData.getLogin();
                //currentUserData.booking_id =
            } catch (IOException e) {
            }

            return Boolean.FALSE;
        }
    }
}