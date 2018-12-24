package com.example.julia.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class work extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
    }
    public void addGameBtn(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(), addGame.class);
        startActivity(startIntent);
    }
    public void evAdd(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(), addEvent.class);
        startActivity(startIntent);
    }
    public void leaveMain(View view)
    {
        Intent startIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(startIntent);
    }

}
