package com.example.julia.booking;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.example.julia.booking.daoJSON.JSONtoList;
import com.example.julia.booking.userData;
import com.example.julia.booking.currentUserData;

import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    String username,password;
    userData currentUser;
    AlertDialog alertDialog;

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
                Intent startIntent = new Intent(getApplicationContext(), register.class);
                startActivity(startIntent);
            }
        });

    }

    private  class GetUser extends AsyncTask< String,Void ,Boolean>
    {
        @Override
        protected Boolean doInBackground(String... param)
        {

            try {

                String toParse = reciveJSONforQuery("SELECT * FROM Users where login like '" + username + "' and password like '" + password + "';");

                if (toParse.equals("[]")) {

                    return Boolean.FALSE;
                }
                else {
                    List<Object> result = JSONtoList(toParse);

                    LinkedHashMap item = (LinkedHashMap<String,String>) result.get(0);
                    currentUser = new userData(item.get("firstName"),item.get("surname"),item.get("email"),item.get("tel"),item.get("login"),item.get("password"),item.get("role"));
                    currentUserData.setCurrentUserData(currentUser.getName().toString(),currentUser.getLogin().toString(),currentUser.getRole().toString());
                    return Boolean.TRUE;
                }
            } catch (IOException e) {
            }

            return Boolean.FALSE;
        }
    }
     public void OnLogin(View view) throws InterruptedException, ExecutionException, IOException {
        username = UsernameEt.getText().toString();
        password = PasswordEt.getText().toString();

        Boolean res= new GetUser().execute().get();
        if (res) {
            if (currentUser.getRole().toString().equals("1"))
            {
                Intent startIntent = new Intent(getApplicationContext(), work.class);
                startActivity(startIntent);
            }
            else if (currentUser.getRole().toString().equals("0")) {
                Intent startIntent = new Intent(getApplicationContext(), host.class);
                startActivity(startIntent);
            }
        }
        else
        {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Вход в систему");
            alertDialog.setMessage("Неверный логин/пароль");
            alertDialog.show();
        }

    }


}
