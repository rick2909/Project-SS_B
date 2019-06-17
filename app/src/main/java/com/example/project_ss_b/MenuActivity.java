package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);


        configurePatientsButton();
        configureRapportageButton();
        configureLogoutButton();
        configureRoosterButton();
    }

    private void configurePatientsButton(){
        Button button = findViewById(R.id.patients_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MenuActivity.this, PatientsActivity.class));
            }
        });
    }

    private void configureRapportageButton(){
        Button button = findViewById(R.id.rapportage);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MenuActivity.this, Rapportage.class));
            }
        });
    }

    private void configureLogoutButton(){
        Button button = findViewById(R.id.log_out_btn);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MenuActivity.this, MainActivity.class));
            }
        });
    }

    private void configureRoosterButton(){
        Button button = findViewById(R.id.Rooster);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(new Intent(MenuActivity.this, Rooster.class));
            }
        });
    }
}
