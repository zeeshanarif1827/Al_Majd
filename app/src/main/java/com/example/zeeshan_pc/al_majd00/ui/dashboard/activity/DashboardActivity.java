package com.example.zeeshan_pc.al_majd00.ui.dashboard.activity;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.fragment.NavigationDrawer;

public class DashboardActivity extends AppCompatActivity {

    ImageView menuBtn;
    FragmentManager fragmentManager;
    VideoView promoVieo;
    boolean flag = false;
    MediaController media_control;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menuBtn = findViewById(R.id.menuBtn);
        promoVieo = findViewById(R.id.promoVideo);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.promo_video);

        menuBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!flag) {
                    flag = true;

                    fragmentManager = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right, R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmentTransaction.add(R.id.home_container, new NavigationDrawer());
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();

                } else {
                    onBackPressed();
                }
            }
        });

        if (promoVieo != null) {

            media_control = new MediaController(this);
            promoVieo.setMediaController(media_control);
            promoVieo.setVideoURI(uri);
            promoVieo.start();

        } else {
            Toast.makeText(this, "No Video Found", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        if (fragmentManager != null) {

            flag = false;
            fragmentManager.popBackStack();
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            menuBtn.setBackground(getResources().getDrawable(R.drawable.ic_action_menu));
        }
    }

}