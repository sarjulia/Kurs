package com.example.julia.booking;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Button regdoneBtn = (Button)findViewById(R.id.tablechosen);
        regdoneBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent startIntent = new Intent(getApplicationContext(),host.class);
                startActivity(startIntent);
            }
        });
    }


}
