package com.example.zeeshan_pc.al_majd00.ui.dashboard.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.aboutUs.AboutUsActivity;
import com.example.zeeshan_pc.al_majd00.ui.channel.activity.ChannelsActivity;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.activity.DashboardActivity;
import com.example.zeeshan_pc.al_majd00.ui.distibutors.DistibutorsActivity;
import com.example.zeeshan_pc.al_majd00.ui.liveStreaming.LiveStreamingActivity;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawer extends Fragment {

    Button backBtn;
    View v;
    LinearLayout channelLayout, distibutorsLayout, aboutUsLayout, dashboardLayout, liveLayout;

    public NavigationDrawer() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_navigation_drawer, container, false);

        init();

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().onBackPressed();
            }
        });

        channelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getContext(), ChannelsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        distibutorsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getContext(), DistibutorsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        aboutUsLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getContext(), AboutUsActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        liveLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getContext(), LiveStreamingActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        dashboardLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().finish();
                startActivity(new Intent(getContext(), DashboardActivity.class));
                getActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });

        return v;
    }

    void init(){
        backBtn = v.findViewById(R.id.back_arrow);
        channelLayout = v.findViewById(R.id.channels);
        distibutorsLayout = v.findViewById(R.id.distibutors);
        aboutUsLayout = v.findViewById(R.id.aboutUs);
        dashboardLayout = v.findViewById(R.id.dashboard);
        liveLayout = v.findViewById(R.id.live);
    }
}