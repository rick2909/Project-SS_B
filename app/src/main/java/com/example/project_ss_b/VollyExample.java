package com.example.project_ss_b;

import android.app.ProgressDialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VollyExample extends AppCompatActivity {

    private TextView txtResponse;
    private String jsonResponse;

    // URL to get contacts JSON
    private static String url = "https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly_example);

        txtResponse = findViewById(R.id.txtResponse);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Volley test");
        //add backButton
        actionBar.setDisplayHomeAsUpEnabled(true);
    }

    //make the backbutton go on step back
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    //function that is called after response
    private void responder(String response){
        //try to create json object of the response
        try{
            JSONObject obj = new JSONObject(response);

            JSONObject user = obj.getJSONObject("user");
            String name = user.getString("achternaam");
            String email = user.getString("email");
            String id = user.getString("id");

            System.out.println(user);

            jsonResponse = "";
            jsonResponse += "Name: " + name + "\n\n";
            jsonResponse += "Email: " + email + "\n\n";
            jsonResponse += "Id: " + id + "\n\n";

            txtResponse.setText(jsonResponse);

            String toast;

            if(!obj.getBoolean("error")){
                toast = "succes: " + obj.getString("message");
            }else {
                toast = "Error: " + obj.getString("message");
            }

            // a small message to notify the user what happened
            Toast message = Toast.makeText(getApplicationContext(), toast, Toast.LENGTH_SHORT);
            message.show();
        }catch (Throwable t) {
            System.out.println(response + " was not json " + t);
        }
    }

    public void send(View view){

        //get data from inputs my case not used :)
        //EditText editEmail = findViewById(R.id.email);
        final String email = "test@test.nl";//editEmail.getText().toString();
        //EditText editPassword = findViewById(R.id.wachtwoord);
        final String password = "geheim123";//editPassword.getText().toString();

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
}
