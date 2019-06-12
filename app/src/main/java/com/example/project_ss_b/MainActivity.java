package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button rapportage;
    Button rooster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    rapportage = findViewById(R.id.Rapportage);
    rooster = findViewById(R.id.Rooster);


        // Capture button clicks
            rapportage.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Rapportage.class);
                startActivity(myIntent);
            }
        });
        // Capture button clicks
        rooster.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {

                // Start NewActivity.class
                Intent myIntent = new Intent(MainActivity.this,
                        Rooster.class);
                startActivity(myIntent);
            }
        });
    }
}


