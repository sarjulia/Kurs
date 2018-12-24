package com.example.julia.booking;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class addEvent extends AppCompatActivity {
    EditText eventName, desc, adminLogin, maxAm, eDate,eStart,eEnd;

    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);
        eventName = (EditText) findViewById(R.id.eventName);
        desc = (EditText) findViewById(R.id.description);
        maxAm = (EditText) findViewById(R.id.maxAm);
        adminLogin = (EditText) findViewById(R.id.eLogText);
        eDate = (EditText) findViewById(R.id.eDateText);
        eStart = (EditText) findViewById(R.id.eStartText);
        eEnd = (EditText) findViewById(R.id.eEndText);

    }
    public void onAdd(View view) throws InterruptedException, ExecutionException, IOException {


            Boolean res = new goAdd().execute().get();
            Intent startIntent = new Intent(getApplicationContext(), work.class);
            startActivity(startIntent);


    }

    private class goAdd extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... param) {

            try {

                String toParse = reciveJSONforQuery("INSERT INTO Events VALUES ('" + eventName.getText() + "', '" + desc.getText() + "', '"
                        + adminLogin.getText() + "', '" + maxAm.getText() + "', '" + "0" + "', '"+ eDate.getText() + "', '" + eStart.getText() + "', '"+ eEnd.getText() + "');");

            } catch (IOException e) {
            }

            return Boolean.FALSE;
        }
    }
}
