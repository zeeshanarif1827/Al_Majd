package com.example.zeeshan_pc.al_majd00.ui.aboutUs;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.channel.activity.ChannelsActivity;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.activity.DashboardActivity;

public class AboutUsActivity extends AppCompatActivity {

    ImageView aboutBackBtn;
    Typeface ubuntu_light_font;
    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        aboutBackBtn = findViewById(R.id.aboutUsBackBtn);
        headerText = findViewById(R.id. headerText);

        headerText.setText("About Us");
        aboutBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(AboutUsActivity.this, DashboardActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        startActivity(new Intent(AboutUsActivity.this, DashboardActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    void font() {
        ubuntu_light_font = Typeface.createFromAsset(AboutUsActivity.this.getAssets(), "font/ubuntu_light.ttf");
    }
}
