package com.example.project_ss_b;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PatientsActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Patienten");
        //add backButton
        actionBar.setDisplayHomeAsUpEnabled(true);

        send();
    }

    private void placing(final String[] clienten, final JSONObject[] clientenArray){
        lv = findViewById(R.id.ListView);

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, clienten);

        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long l) {
                Toast.makeText(PatientsActivity.this, clienten[i] , Toast.LENGTH_SHORT).show();
                Intent patientA  = new Intent(view.getContext(), PatientA.class);
                patientA.putExtra("client", clientenArray[i].toString());
                startActivityForResult(patientA, 0);
            }
        });
    }

    //function that is called after response
    private void responder(String response){
        //try to create json object of the response
        try{
            System.out.println("begin");
            JSONObject obj = new JSONObject(response);
            System.out.println("voor array");
            JSONArray clienten = obj.getJSONArray("clienten");

            System.out.println("For loop");
            String[] strings = new String[clienten.length()];
            JSONObject[] clientenArray = new JSONObject[clienten.length()];

            for (int i=0 ;i < clienten.length(); i++){
                JSONObject client = clienten.getJSONObject(i);
                clientenArray[i] = client;
                strings[i] = client.getString("achternaam");
            }

            String toast;

            System.out.println(!obj.getBoolean("error"));

            if(!obj.getBoolean("error")){
                System.out.println("GOOD");
                placing(strings, clientenArray);
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

    public void send(){
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
                params.put("function", "getAllClienten");
                //the arguments it needs read README for that
                params.put("id", "1");

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