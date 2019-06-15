package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientA extends AppCompatActivity {

    private TextView textView;
    private TextView textView2;
    private TextView textView3;
    private TextView textView5;
//    private TextView textPlaats;
//    private TextView textAdres;
//    private TextView textPostcode;
    private TextView textGeslacht;
    private String naam;
    private String opmerkingen;
    private String updatedAt;
    private String email;
    private String geslacht;
    private JSONObject patient;

    //create Volley queue
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

         queue = Volley.newRequestQueue(this);

        textView = (TextView)findViewById(R.id.textView);
        textView2 = (TextView)findViewById(R.id.textView2);
        textView3 = (TextView)findViewById(R.id.textView3);
        textView5 = (TextView)findViewById(R.id.textView5);
        textGeslacht = (TextView)findViewById(R.id.geslacht);
//        textPlaats = (TextView)findViewById(R.id.plaats);
//        textAdres = (TextView)findViewById(R.id.adres);
//        textPostcode = (TextView)findViewById(R.id.postcode);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Patienten");
        //add backButton
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent.hasExtra("client")) {
            System.out.println(intent.getExtras().getString("client"));
            try {
                patient = new JSONObject(intent.getExtras().getString("client"));
                naam = patient.getString("achternaam");
                email = patient.getString("email");
                geslacht = patient.getString("Geslacht");
                textView.setText("achternaam: " + naam);
                textView5.setText("E-Mail: " + email);
                textView5.setText("E-Mail: " + email);
                textGeslacht.setText("Geslacht: " + geslacht);

                send(patient.getString("id"), "getProtocol");
//                send(patient.getString("locatie_id"), "getClientsLoactien");
            } catch (JSONException e) {
                System.out.println(e);
            }
        }
    }

    private void responderProtocol(String response){
        //try to create json object of the response
        try{
            JSONObject obj = new JSONObject(response);
            JSONObject protocol = obj.getJSONObject("Protocol");
            String toast;

            if(!obj.getBoolean("error")){
                System.out.println("GOOD");
                toast = "succes: " + obj.getString("message");
                opmerkingen = protocol.getString("Protocol");
                updatedAt = protocol.getString("updated_at");
                textView2.setText(opmerkingen);
                textView3.setText("Updated at: " + updatedAt);

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

    private void responderLocatie(String response){
        //try to create json object of the response
        try{
            JSONObject obj = new JSONObject(response);
            JSONObject protocol = obj.getJSONObject("locatie");
            String toast;

            if(!obj.getBoolean("error")){
                System.out.println("GOOD");
                toast = "succes: " + obj.getString("message");
                opmerkingen = protocol.getString("Protocol");
                updatedAt = protocol.getString("updated_at");
                updatedAt = protocol.getString("updated_at");

//                textPlaats.setText("achternaam: " + naam);
//                textAdres.setText("E-Mail: " + email);
//                textPostcode.setText("E-Mail: " + email);

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

    public void send(final String id, final String functionCall){
        //url to API
        String url ="http://145.24.222.132/api.php";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        if(functionCall == "getProtocol"){
                            responderProtocol(response);
                        }else if(functionCall == "getClientsLoactien"){
                            responderLocatie(response);
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
                //place the paremeters
                Map<String, String>  params = new HashMap<String, String>();
                //the function you want to call
                params.put("function", functionCall);
                //the arguments it needs read README for that
                params.put("id", id);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
