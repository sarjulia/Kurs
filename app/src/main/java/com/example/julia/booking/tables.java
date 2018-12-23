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
    Context thisContext;
    ListView TablesView;
    List<TablesItem> myTables;

    EditText bookdate_tab = (EditText)findViewById(R.id.bookdata);
    EditText starttime_tab = (EditText)findViewById(R.id.bookstart);
    EditText endtime_tab = (EditText)findViewById(R.id.bookend);


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

                String day_tab = bookdate_tab.getText().toString();
                String start_tab = starttime_tab.getText().toString();
                String end_tab  = endtime_tab.getText().toString();

                GetData retrievedata = new GetData();
                retrievedata.execute(day_tab,start_tab,end_tab);

                //Intent startIntent = new Intent(getApplicationContext(),games.class);
                //startActivity(startIntent);
            }
        });
    }


    private class GetData extends AsyncTask<String, Void, Void> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask


        }

        @Override
        protected Void doInBackground(String...param) {

            String day = param[0];
            String start = param[1];
            String end = param[2];

            try {


                //запрос взяла из курсача, т.е. от Даши
                String toParse = reciveJSONforQuery("select * from tables t left join booking b on t.id=b.tableId where count(case when"  + start +">b.beginTime and "+start +"<b.endTime or "+ end + ">b.beginTime and "+end +"<b.endTime then 1 and "+day+"=b.bookingDate end) = 0");
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

                TablesView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                {
                                                    public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                    {
                                                        TablesItem chosen = myTables.get(position);
                                                        AlertDialog.Builder a_build = new AlertDialog.Builder(tables.this);
                                                        a_build.setMessage("Do you want to book table "+ chosen.getTableid().toString() + " ?")
                                                                .setCancelable(false)
                                                                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                    @Override
                                                                    public void onClick(DialogInterface dialog, int which) {
                                                                        // inserting here

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

}