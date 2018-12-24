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

import com.example.julia.booking.currentUserData;
import com.example.julia.booking.BookItem;
import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class host extends AppCompatActivity {

    ItemAd_Book bad;

    Context thisContext;
    ListView BookView;
    BookItem chosen;
    List<BookItem> myBooks;
    BookItem book;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        Resources res = getResources();
        BookView = (ListView) findViewById(R.id.BookView);

        //thisContext = this;

        Context context;

        myBooks = new ArrayList<>();


        GetData retrievedata = new GetData();
        retrievedata.execute();


    }
    public void bookTable(View view)
    {

        Intent startIntent = new Intent(getApplicationContext(), tables.class);
        startActivity(startIntent);
    }
    public void bookEvent(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(),Events.class);
        startActivity(startIntent);
    }
    public void exitToMain(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startIntent);
    }
    private class GetData extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask

        }

        @Override
        protected String doInBackground(String... param) {

            try {


                // new select from booking table
                String toParse = reciveJSONforQuery("SELECT * FROM Booking WHERE userLogin = " + currentUserData.getLogin() + " and bookingDate>=date(now())");
                List<Object> result = JSONtoList(toParse);

                for (int i = 0; i < result.size(); i++) {
                    LinkedHashMap item = (LinkedHashMap<String, Object>) result.get(i);
                    book = new BookItem(item.get("id"),  item.get("roomName"),item.get("tableId"),item.get("userLogin"), item.get("bookingDate"),item.get("beginTime"),item.get("endTime"));
                    myBooks.add(book);
                   //myEvent.add(new EventItem());

                }


            } catch (IOException e) {
            }

            return "2";
        }


        @Override
        protected void onPostExecute(String smth) {

            if (myBooks.size() > 0) {

                bad = new ItemAd_Book(getApplicationContext(), myBooks);
                BookView.setAdapter(bad);

                BookView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                                  {
                                                      public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                      {
                          chosen = myBooks.get(position);
                          AlertDialog.Builder a_build = new AlertDialog.Builder(host.this);
                          a_build.setMessage("Do you want to delete reservation ?")
                                  .setCancelable(false)
                                  .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                      @Override
                                      public void onClick(DialogInterface dialog, int which) {
                                          // deleting here

                                          DeleteData retrievedata2 = new DeleteData();
                                          retrievedata2.execute();
                                          Intent startIntent = new Intent(getApplicationContext(), host.class);
                                          startActivity(startIntent);

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
    private class DeleteData extends AsyncTask<String, Void, String> {



        @Override
        protected void onPreExecute() {
            // initialize varies for sql ask

        }

        @Override
        protected String doInBackground(String... param) {

            try {


                // new select from booking table
                String toParse = reciveJSONforQuery("DELETE FROM bookingGames WHERE bookingId="+ chosen.getId() + ";");
                String toParse2 = reciveJSONforQuery("DELETE FROM Booking WHERE id = "+ chosen.getId() + ";");

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

