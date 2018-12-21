package com.example.julia.booking;

import android.app.AlertDialog;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.sql.Statement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

import static com.example.julia.booking.daoJSON.JSONtoList;
import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class games extends AppCompatActivity {

    ItemAd_Game itad;
    Context thisContext;
    ListView GameView;
    TextView progressTextView;
    List<GameItem> myGames;
   // Map <String, Double>  gamesMap = new LinkedHashMap<String,Double>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);


        Resources res = getResources();
        GameView = (ListView) findViewById(R.id.GameView);
        progressTextView = (TextView) findViewById(R.id.progressTextView);
        thisContext = this;

        Context context;

        myGames = new ArrayList<>();

        // Checking with custom varies
      // myGames.add(new GameItem(1,"Gnoms","best",4,8,"#cool #lesha"));
        //myGames.add(new GameItem(2,"Trains","chuchuchu",4,8,"#cool #roads"));


        progressTextView.setText("");
        GetData retrievedata = new GetData();
        retrievedata.execute("");

        Button bookgameBtn = (Button)findViewById(R.id.bookgame);
        bookgameBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),host.class);
                startActivity(startIntent);
            }
        });



    }

    private  class GetData extends AsyncTask<String,String,String>
    {
        String msg = "";

        //static  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        //static final String DB_URL = "jdbc:mysql://http://kursach.cisaodjoj1fj.us-east-1.rds.amazonaws.com:3306/Kursach";



        @Override
        protected void onPreExecute()
        {
            progressTextView.setText("Connecting ...");
        }

        @Override
        protected String doInBackground(String... param)
        {

            try {

                String toParse = reciveJSONforQuery("SELECT * FROM Games");
                List<Object> res = JSONtoList(toParse);

                for (int i =0 ; i < res.size(); i+=1)
                {
                    LinkedHashMap item = (LinkedHashMap<String,Object>) res.get(0);
                    myGames.add(new GameItem(i+1,item.get("name"),item.get("description"),item.get("minPlayers"),item.get("maxPlayers"),item.get("tags")));
                }

                msg = "complete";

            } catch (IOException e) {
            }
          //Connection conn = null;
         // Statement stt = null;

          /*try
          {
              Class.forName(JDBC_DRIVER);
              conn = DriverManager.getConnection(DB_URL, DbStrings.USERNAME, DbStrings.PASSWORD);

              stt = conn.createStatement();
              String sql = "SELECT * FROM games";
              ResultSet rs = stt.executeQuery(sql);
              int i =1;

              while (rs.next())
              {
                  String name = rs.getString("name");
                  String desc = rs.getString("description");
                  int minpeop = rs.getInt("MinPlayers");
                  int maxpeop = rs.getInt("MaxPlayers");
                  String tags = rs.getString("tags");


                  myGames.add(new GameItem(i,name,desc,minpeop,maxpeop,tags));

                  i+=1;

                 // gamesMap.put(name,minpeop);
              }
              msg = "Complete";

              rs. close();
              stt.close();
              conn.close();

          }catch(SQLException connError)
          {
             msg = " error for JBDC";
             connError.printStackTrace();
          }catch(ClassNotFoundException e)
          {
              msg = " class not found";
              e.printStackTrace();
          }finally {
              try{
                  if (stt != null){
                      stt.close();
                  }
              }catch (SQLException e)
              {
                  e.printStackTrace();
              }

              try{
                  if (conn != null){
                      conn.close();
                  }
              }catch (SQLException e)
              {
                  e.printStackTrace();
              }
          }*/

            return null;
        }




        @Override
        protected void onPostExecute(String msg)
        {
            progressTextView.setText(this.msg);

            if (myGames.size() > 0 )
            {
                itad = new ItemAd_Game(getApplicationContext(), myGames );
                GameView.setAdapter(itad);
            }
        }
    }

}
