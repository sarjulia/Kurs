package com.example.julia.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.app.AlertDialog;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import static com.example.julia.booking.daoJSON.reciveJSONforQuery;

public class register extends AppCompatActivity {

    EditText textName, textPass, textMail, textTel, textSur, textLogin;
    userData currUser;
    AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        textName = (EditText) findViewById(R.id.name_ev);
        textLogin = (EditText) findViewById(R.id.login_txt);
        textPass = (EditText) findViewById(R.id.password);
        textMail = (EditText) findViewById(R.id.email);
        textTel = (EditText) findViewById(R.id.phone);
        textSur = (EditText) findViewById(R.id.surname);
    }


    public void onReg(View view) throws InterruptedException, ExecutionException, IOException {

        if (!textName.getText().toString().isEmpty() || !textSur.getText().toString().isEmpty() || !textTel.getText().toString().isEmpty() || !textPass.getText().toString().isEmpty() || !textMail.getText().toString().isEmpty() || !textLogin.getText().toString().isEmpty()) {
            currUser = new userData(textName.getText(), textSur.getText(), textMail.getText(), textTel.getText(), textLogin.getText(), textPass.getText(), "0");
            Boolean res = new RegUser().execute().get();
            if (res) {
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Регистрация");
                alertDialog.setMessage("Регистрация прошла успешно!");
                alertDialog.show();
                Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(startIntent);
            } else {
                alertDialog = new AlertDialog.Builder(this).create();
                alertDialog.setTitle("Регистрация");
                alertDialog.setMessage("Пользователь с данным логином/e-mail/телефоном уже зарегестрирован!");
                alertDialog.show();
            }
        } else {
            alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("Регистрация");
            alertDialog.setMessage("Заполните все поля");
            alertDialog.show();
        }

    }

    private class RegUser extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... param) {

            try {

                String toParse = reciveJSONforQuery("INSERT INTO Users VALUES ('" + currUser.getName() + "', '" + currUser.getSurname() + "', '"
                        + currUser.getMail() + "', '" + currUser.getTel() + "', '" + currUser.getLogin() + "', '" + currUser.getPass() + "', 0);");
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