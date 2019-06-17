package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText name;
    private EditText pass;
    private TextView tView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.username);
        pass = findViewById(R.id.password);
        tView = findViewById(R.id.textView);

        configureLoginButton();

        pass.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    send();
                    handled = true;
                }
                return handled;
            }
        });
    }

    //function that is called after response
    private void responder(String response){
        //try to create json object of the response
        try{
            JSONObject obj = new JSONObject(response);

            System.out.println("HELP");

            String toast;

            if(!obj.getBoolean("error")){
                toast = "succes: " + obj.getString("message");
                System.out.println("GOOD");
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            }else {
                toast = "Error: " + obj.getString("message");
                System.out.println("ERROR");

                tView.setText("Gebruikersnaam of wachtwoord is verkeerd. Probeer het nog eens.");
            }

            // a small message to notify the user what happened
            Toast message = Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT);
            message.show();
        }catch (Throwable t) {
            System.out.println(response + " was not json " + t);
        }
    }

    public void send(){

        //get data from inputs my case not used :)
        final String email = name.getText().toString();
        final String password = pass.getText().toString();

        //create Volley queue
        RequestQueue queue = Volley.newRequestQueue(this);

        //url to API
        String url ="http://145.24.222.132/api.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        responder(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //textView.setText("That didn't work!");
                System.out.println("That didn't work! " + error);
            }
        }
        ){
            @Override
            protected Map<String, String> getParams()
            {
                //place the paremeters
                Map<String, String>  params = new HashMap<String, String>();
                //the function you want to call
                params.put("function", "login");
                //the arguments it needs read README for that
                params.put("email", email);
                params.put("password", password);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

        System.out.println(email);
        System.out.println(password);
    }

    private void configureLoginButton(){
        Button button = findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                send();
            }
        });
    }

    //disable back button vof the phone
    @Override
    public void onBackPressed(){
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
