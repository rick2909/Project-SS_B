package com.example.project_ss_b;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;

public class clientOnMap extends AppCompatActivity implements OnMapReadyCallback {

    private static final int REQUEST_CODE_FINE_GPS = 1;

    private GoogleMap mMap;
    private JSONObject client;
    private UiSettings mUiSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_on_map);

        ActionBar actionBar = this.getSupportActionBar();
        actionBar.setTitle("Map");
        //add backButton
        actionBar.setDisplayHomeAsUpEnabled(true);

        if (checkPermission()) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE_FINE_GPS);
        } else {
            Toast.makeText(this, "it has not shown", Toast.LENGTH_LONG).show();
        }

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if (intent.hasExtra("client")) {
            System.out.println(intent.getExtras().getString("client"));
            try {
                client = new JSONObject(intent.getExtras().getString("client"));
            } catch (JSONException e) {
                System.out.println(e);
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //settings
        mUiSettings = mMap.getUiSettings();

        mUiSettings.setZoomControlsEnabled(true);
        mUiSettings.setZoomGesturesEnabled(true);
        mUiSettings.setScrollGesturesEnabled(true);
        mUiSettings.setCompassEnabled(true);

        //ask permission
        if (checkPermission()) {
            mMap.setMyLocationEnabled(true);
            mUiSettings.setMyLocationButtonEnabled(true);
        } else {
            requestPermission();
            //Toast.makeText(this, "use Loaction permission allows us to use google maps and give you a route. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        }


        // Add a marker in Sydney and move the camera
        try {
            LatLng clientMarker = new LatLng(client.getDouble("lat"), client.getDouble("lon"));
            mMap.addMarker(new MarkerOptions().position(clientMarker).title(client.getString("naam")));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(clientMarker, 14.0f));

        }catch (JSONException e){
            System.out.println(e);
        }
    }

    private boolean checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }

    private void requestPermission() {

        if (ActivityCompat.shouldShowRequestPermissionRationale(clientOnMap.this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "use Loaction permission allows us to use google maps and give you a route. Please allow this permission in App Settings.", Toast.LENGTH_LONG).show();
        } else {
            ActivityCompat.requestPermissions(clientOnMap.this, new String[]{Manifest.permission.READ_CONTACTS},REQUEST_CODE_FINE_GPS);
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
