package com.example.zeeshan_pc.al_majd00.ui.dashboard.activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.fragment.NavigationDrawer;

public class DashboardActivity extends AppCompatActivity {

    ImageView menuBtn;
    FragmentManager fragmentManager;
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        menuBtn = findViewById(R.id.menuBtn);

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