package com.example.project_ss_b;

import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Rooster extends AppCompatActivity {

    //private TextView txtResponse;
    private String jsonResponse;
    private TextView Username;
    LinearLayout layout;



    // URL to get contacts JSON
    private static String url = "https://api.androidhive.info/contacts/";

    ArrayList<HashMap<String, String>> user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rooster);
        //Date
        Calendar cal =  Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance(DateFormat.FULL).format(cal.getTime());


        TextView Date = (TextView) findViewById(R.id.Date);
        Date.setText(currentDate);

        //Username = findViewById(R.id.Username);




//      txtResponse = findViewById(R.id.txtResponse);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Rooster");
        //add backButton
        actionBar.setDisplayHomeAsUpEnabled(true);

        send();


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

            JSONArray rooster = obj.getJSONArray("rooster");

            layout = (LinearLayout) findViewById(R.id.layout);
            jsonResponse = "";

            for(int i = 0; i < rooster.length(); i++){
                JSONObject client = rooster.getJSONObject(i);
                String name = client.getString("gebruikers_id");
                String dag = client.getString("dag_van_de_week");
                String tijd = client.getString("tijd");

                TextView text = new TextView(this);
                text.setText("  Naam: " + name + "\n  " + dag + " " + tijd + "\n\n" );
                text.setPadding(32,0,32,0);
                text.setTextSize(20);
                text.setTextColor(Color.BLACK);
                text.setGravity(Gravity.LEFT);
                text.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
                layout.addView(text);

                jsonResponse += name;
                jsonResponse += dag + "   " + tijd;

            }

            System.out.println(rooster);

//            T0000.setText(jsonResponse);
//            TV00.setText(jsonResponse);
            //txtResponse.setText(jsonResponse);

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
                params.put("function", "getRooster");
                //the arguments it needs read README for that
                params.put("id", "1");
                //params.put("password", password);

                return params;
            }
        };

        // Add the request to the RequestQueue.
        queue.add(stringRequest);

//        System.out.println(email);
//        System.out.println(password);
    }
}





