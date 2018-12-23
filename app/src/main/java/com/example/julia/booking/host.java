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
import android.widget.ListView;


import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class host extends AppCompatActivity {

    ItemAd_Book bad;
    Context thisContext;
    ListView BookView;
    List<BookItem> myBooks;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        Resources res = getResources();
        BookView = (ListView) findViewById(R.id.BookView);
        thisContext = this;

        Context context;

        myBooks = new ArrayList<>();

        GetData retrievedata = new GetData();
        retrievedata.execute();

        Button createbookBtn = (Button)findViewById(R.id.exit);
        createbookBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(startIntent);
            }
        });

        Button booktableBtn = (Button)findViewById(R.id.booktable);
        booktableBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),tables.class);
                startActivity(startIntent);
            }
        });

        Button bookeventBtn = (Button)findViewById(R.id.bookevent);
        bookeventBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Events.class);
                startActivity(startIntent);
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

                String login = "1234";
                // new select from booking table
                String toParse = reciveJSONforQuery("SELECT * FROM Booking WHERE userLogin = " + login + " and bookingDate>=date(now())");
                List<Object> result = JSONtoList(toParse);

                for (int i = 0; i < result.size(); i += 1) {
                    LinkedHashMap item = (LinkedHashMap<String, Object>) result.get(i);
                    myBooks.add(new BookItem(i + 1, item.get("tableId"), item.get("RoomName"), item.get("bookingDate"),item.get("beginTime"),item.get("endTime")));
                }


            } catch (IOException e) {
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void smth) {


            if (myBooks.size() > 0) {
                bad = new ItemAd_Book(getApplicationContext(), myBooks);
                BookView.setAdapter(bad);

                BookView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                  {
                                                      public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                      {
                                                          BookItem chosen = myBooks.get(position);
                                                          AlertDialog.Builder a_build = new AlertDialog.Builder(host.this);
                                                          a_build.setMessage("Do you want to delete reservation ?")
                                                                  .setCancelable(false)
                                                                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                      @Override
                                                                      public void onClick(DialogInterface dialog, int which) {
                                                                          // deleting here

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

