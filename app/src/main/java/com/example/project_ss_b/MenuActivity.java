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
}
