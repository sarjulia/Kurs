package com.example.julia.booking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.EventLog;
import android.view.View;
import android.widget.AdapterView;
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

    List<EventItem> myEvent;
    EventItem chosen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);

        Resources res = getResources();
        EventsView = (ListView) findViewById(R.id.EventsView);

        thisContext = this;

        myEvent = new ArrayList<>();




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


            if (myEvent.size() > 0 )
            {
                evad = new ItemAd_Event(getApplicationContext(), myEvent );
                EventsView.setAdapter(evad);

                EventsView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                  {
                                                      public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                      {
                                                          chosen = myEvent.get(position);
                                                          AlertDialog.Builder a_build = new AlertDialog.Builder(Events.this);
                                                          a_build.setMessage("Do you want to join "+ chosen.getName().toString() + " ?")
                                                                  .setCancelable(false)
                                                                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(DialogInterface dialog, int which) {
                                                                          // inserting here
                                                                          InsertEvent retrievedata2 = new InsertEvent();
                                                                          retrievedata2.execute();

                                                                          //closing here
                                                                          dialog.cancel();
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
    private class InsertEvent extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask

        }

        @Override
        protected String doInBackground(String... param) {

            try {


                // new select from booking table
                String toParse = reciveJSONforQuery("update Events set currentAmountOfPeople = currentAmountOfPeople + 1 where name = '"+ chosen.getName() +"'; ");
                String toParse2 = reciveJSONforQuery("INSERT INTO Guests VALUES ('"+chosen.getName()+"', '"+currentUserData.getLogin()+"');");

                List<Object> result = JSONtoList(toParse);




            } catch (IOException e) {
            }

            return "2";
        }


        @Override
        protected void onPostExecute(String smth) {


        }
    }

}