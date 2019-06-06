package com.example.project_ss_b;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class VollyExample extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volly_example);
    }

    public void send(View view){

        //get data from inputs
        //EditText editEmail = findViewById(R.id.email);
        final String email = "test@test.nl";//editEmail.getText().toString();
        //EditText editPassword = findViewById(R.id.wachtwoord);
        final String password = "geheim123";//editPassword.getText().toString();

        //create Volley queue
        RequestQueue queue = Volley.newRequestQueue(this);

        String url ="http://145.24.222.132/login.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //try to create json object of the response
                        try{
                            JSONObject obj = new JSONObject(response);

                            //JSONObject user = obj.getJSONObject("user");

                            //System.out.println(user);

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
                Map<String, String>  params = new HashMap<String, String>();
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
