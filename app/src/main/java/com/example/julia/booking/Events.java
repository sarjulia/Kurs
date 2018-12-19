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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import  java.sql.Statement;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    private  class GetData extends AsyncTask<String,String,String>
    {
        String msg = "";

        static  final String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        static final String DB_URL = "jdbc:mysql://" +DbStrings.DATABASESE_URL+DbStrings.DATABASE_NAME;

        @Override
        protected void onPreExecute()
        {
            progressTextView.setText("Connecting ...");
        }

        @Override
        protected String doInBackground(String... param)
        {
            Connection conn = null;
            Statement stt = null;

            try
            {
                Class.forName(JDBC_DRIVER);
                conn = DriverManager.getConnection(DB_URL,DbStrings.USERNAME, DbStrings.PASSWORD);

                stt = conn.createStatement();
                String sql = "SELECT * FROM Events";
                ResultSet rs = stt.executeQuery(sql);
                int i =1;

                while (rs.next())
                {
                    String name = rs.getString("name");
                    String desc = rs.getString("description");
                    int cur = rs.getInt("currentAmountOfPeople");
                    int max = rs.getInt("maxAmountOfPeople");
                    Date data = rs.getDate("date");
                    Time start = rs.getTime("beginTime");
                    Time end = rs.getTime("endTime");


                    myEvent.add(new EventItem(i,name,desc,cur,max,data,start,end));

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
            }

            return null;
        }

        @Override
        protected void onPostExecute(String msg)
        {
            progressTextView.setText(this.msg);

            if (myEvent.size() > 0 )
            {
                evad = new ItemAd_Event(getApplicationContext(), myEvent );
                EventsView.setAdapter(evad);
            }
        }
    }

}
