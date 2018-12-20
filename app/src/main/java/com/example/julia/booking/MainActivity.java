package com.example.julia.booking;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);

        Button regOn = (Button)findViewById(R.id.regButton);
        regOn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(), host.class);
                startActivity(startIntent);
            }
        });

    }

     public void OnLogin(View view) throws InterruptedException, ExecutionException {
        String username = UsernameEt.getText().toString();
        String password = PasswordEt.getText().toString();
        String type = "login";


         String str_result= new BackgroundWorker(this).execute(type,username,password).get();
        if (str_result.equals("y")) {
            Intent startIntent = new Intent(getApplicationContext(), host.class);
            startActivity(startIntent);
        }
        /*else
        {
            Intent startIntent = new Intent(getApplicationContext(), bookpage.class);
            startActivity(startIntent);
        }*/
        //if (backgroundWorker.good)
         //123   startActivity(new Intent(this,host.class));
    }


}
