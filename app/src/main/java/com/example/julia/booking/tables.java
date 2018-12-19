package com.example.julia.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class tables extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tables);

        Button tablechosenBtn = (Button)findViewById(R.id.tablechosen);
        tablechosenBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),games.class);
                startActivity(startIntent);
            }
        });
    }
}
