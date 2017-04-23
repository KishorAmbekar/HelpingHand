package com.example.kishor.helpinghand;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.GoogleMap;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.android.gms.location.places.*;

import java.util.HashMap;
import java.util.Map;

import static android.R.attr.data;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tvEmail;
    private FirebaseAuth mAuth;
    private Button buttonLogout;
    private DatabaseReference mDatabase;
    private Button buttonSave;
    private EditText editTextLocation;
    private EditText editTextProfession;
    private EditText editTextLatitude;
    private EditText editTextLongitude;
    private Button btnproceed;
  private Location mLocations;
    private Map mCoordinate;
    private Map mCurrentLocation;
    private GoogleMap mMap;
    private LatLng mLatLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        btnproceed = (Button) findViewById(R.id.btnproceed);
        tvEmail = (TextView) findViewById(R.id.tvEmailProfile);
        tvEmail.setText(getIntent().getExtras().getString("Email"));
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        editTextProfession = (EditText) findViewById(R.id.editTextProfession);
        editTextLatitude=(EditText) findViewById(R.id.editTextLatitude);
        editTextLongitude=(EditText)findViewById(R.id.editTextLongitude);
        buttonSave = (Button) findViewById(R.id.buttonSave);
        FirebaseUser user = mAuth.getCurrentUser();
        buttonLogout = (Button) findViewById(R.id.buttonLogout);
        btnproceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DashboardActivity.this, gpsActivity.class);
                startActivity(i);
            }
        });
        buttonLogout.setOnClickListener(this);
        buttonSave.setOnClickListener(this);
        if (mAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

    }

    private void saveUserInformation() {
        String profession = editTextProfession.getText().toString().trim();
        String location = editTextLocation.getText().toString().trim();
        double latitude= Double.parseDouble(editTextLatitude.getText().toString().trim());
        double longitude= Double.parseDouble(editTextLongitude.getText().toString().trim());
        UserInformation userInformation = new UserInformation(profession,location,latitude,longitude);
        FirebaseUser user = mAuth.getCurrentUser();
        mDatabase.child(user.getUid()).setValue(userInformation);
        Toast.makeText(this, "Information Saved...", Toast.LENGTH_LONG).show();


    }


    @Override
    public void onClick(View view) {

        if (view == buttonLogout) {
            mAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));
        }

        if (view == buttonSave) {
            saveUserInformation();
        }
    }
    }

