package com.example.julia.booking;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.content.Context;
import android.widget.TextView;
import android.widget.ListView;
import android.os.AsyncTask;
import android.widget.Toast;

import java.io.IOException;


import java.util.ArrayList;
import java.util.List;
import java.util.LinkedHashMap;


import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;


public class games extends AppCompatActivity {
    String id;
    ItemAd_Game itad;
    Context thisContext;
    ListView GameView;
    GameItem chosen;
    List<GameItem> myGames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        Resources res = getResources();
        GameView = (ListView) findViewById(R.id.GameView);

        thisContext = this;

        Context context;

        myGames = new ArrayList<>();


       GetData retrievedata = new GetData();
        retrievedata.execute("");





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

                String toParse = reciveJSONforQuery("SELECT * FROM Games");
                List<Object> result = JSONtoList(toParse);

                for (int i =0 ; i < result.size(); i+=1)
                {
                    LinkedHashMap item = (LinkedHashMap<String,Object>) result.get(i);
                    myGames.add(new GameItem(i+1,item.get("name"),item.get("description"),item.get("minPlayers"),item.get("maxPlayers"),item.get("tags")));
                }

                msg = "complete";

            } catch (IOException e) {
            }

        return msg;
     }




        @Override
        protected void onPostExecute(String msg)
        {


            if (myGames.size() > 0 )
            {
                itad = new ItemAd_Game(getApplicationContext(), myGames );
                GameView.setAdapter(itad);

                // click function
                GameView.setOnItemClickListener(new AdapterView.OnItemClickListener()
                                            {
                                                public void onItemClick(AdapterView<?> adapterView, View view,int position, long l)
                                                {
                                                    chosen = myGames.get(position);
                                                    AlertDialog.Builder a_build = new AlertDialog.Builder(games.this);
                                                    a_build.setMessage("Do you want to book "+ chosen.getName().toString() + "?")
                                                            .setCancelable(false)
                                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                                @Override
                                                                public void onClick(DialogInterface dialog, int which) {
                                                                    // inserting here
                                                                    goAdd retrievedata = new goAdd();
                                                                    retrievedata.execute("");
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
    public void dontBook(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(),host.class);
        startActivity(startIntent);
    }

    private class goAdd extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... param) {

            try {

                String toParse = reciveJSONforQuery("select id from Booking where roomName='"+currentUserData.booking_name+"'and tableId='"+currentUserData.booking_id+"'and userLogin='"+currentUserData.booking_login+"'and bookingDate='"+currentUserData.booking_stDate+"'and beginTime='"+currentUserData.booking_stTime+"';");
                List<Object> result = JSONtoList(toParse);


                LinkedHashMap item = (LinkedHashMap<String,Object>) result.get(0);
                id = item.get("id").toString();

                toParse = reciveJSONforQuery("insert into bookingGames values ('"+id+"', '"+chosen.getName()+"');");

            } catch (IOException e) {
            }

            return Boolean.FALSE;
        }
    }

}
