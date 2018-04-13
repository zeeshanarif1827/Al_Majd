package com.example.zeeshan_pc.al_majd00.ui.distibutors;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.activity.DashboardActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DistibutorsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    TextView headerText;
    Typeface ubuntu_light_font;
    ImageView distibutorsBackBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distibutors);

        font();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        headerText = findViewById(R.id.headerText);
        distibutorsBackBtn = findViewById(R.id.distibutorsBackBtn);

        headerText.setText("Distibutors");
        headerText.setTypeface(ubuntu_light_font);

        distibutorsBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(DistibutorsActivity.this, DashboardActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    void font() {
        ubuntu_light_font = Typeface.createFromAsset(DistibutorsActivity.this.getAssets(), "font/ubuntu_light.ttf");
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

        // Add a marker in Sydney and move the camera
        LatLng riyadh = new LatLng(24.715937, 46.671804);
        LatLng al_munsiyah = new LatLng(24.831935, 46.770171);
        LatLng an_narjis = new LatLng(24.893330, 46.645726);
        LatLng as_sulay = new LatLng(24.659048, 46.834392);

        mMap.addMarker(new MarkerOptions().position(riyadh).title("Riyadh"));
        mMap.addMarker(new MarkerOptions().position(al_munsiyah).title("AL MUNSIYAH").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_man_user)));
        mMap.addMarker(new MarkerOptions().position(an_narjis).title("AN NARJIS").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_man_user)));
        mMap.addMarker(new MarkerOptions().position(as_sulay).title("AS SULAY").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_action_man_user)));

        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(24.715937, 46.671804), 10.5f));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        startActivity(new Intent(DistibutorsActivity.this, DashboardActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }
}
