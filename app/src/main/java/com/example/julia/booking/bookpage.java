package com.example.julia.booking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;

public class bookpage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookpage);

        Button booktableBtn = (Button)findViewById(R.id.booktable);
        booktableBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),tables.class);
                startActivity(startIntent);
            }
        });

        Button bookeventBtn = (Button)findViewById(R.id.bookevent);
        bookeventBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),Events.class);
                startActivity(startIntent);
            }
        });
    }
}
