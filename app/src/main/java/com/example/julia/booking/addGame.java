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

public class addGame extends AppCompatActivity {
    EditText gameName, desc, minAm, maxAm, tags;

    AlertDialog alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_game);
        gameName = (EditText) findViewById(R.id.gameName);
        desc = (EditText) findViewById(R.id.description);
        minAm = (EditText) findViewById(R.id.minAm);
        maxAm = (EditText) findViewById(R.id.maxAm);
        tags = (EditText) findViewById(R.id.tags);

    }
    public void onAdd(View view) throws InterruptedException, ExecutionException, IOException {

        if (gameName.getText().toString() != "" && desc.getText().toString() != "" && minAm.getText().toString() != "" && maxAm.getText().toString() != "" && tags.getText().toString() != "" ) {

            Boolean res = new goAdd().execute().get();
            Intent startIntent = new Intent(getApplicationContext(), work.class);
            startActivity(startIntent);
        } else {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Процесс");
            alertDialog.setMessage("Заполните все поля");
            alertDialog.show();
        }

    }

    private class goAdd extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... param) {

            try {

                String toParse = reciveJSONforQuery("INSERT INTO Games VALUES ('" + gameName.getText() + "', '" + desc.getText() + "', '"
                        + minAm.getText() + "', '" + maxAm.getText() + "', '" + tags.getText() + "');");
                if (toParse.equals("")) {

                    return Boolean.FALSE;
                } else {

                    return Boolean.TRUE;
                }
            } catch (IOException e) {
            }

            return Boolean.FALSE;
        }
    }
}
