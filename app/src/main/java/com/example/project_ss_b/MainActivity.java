package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText name;
    EditText pass;
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        tView = findViewById(R.id.textView);
    }

    public void Login(View view){
        String username = name.getText().toString();
        String password = pass.getText().toString();

        String loginName = "admin";
        String loginPass = "admin";

        if(username.equals(loginName) && password.equals(loginPass)){
            //Intent intent = new Intent(this, MenuActivity.class);
            //startActivity(intent);
        }else {
            tView.setText("Gebruikersnaam of wachtwoord is verkeerd. Probeer het nog eens.");
        }
    }
}
