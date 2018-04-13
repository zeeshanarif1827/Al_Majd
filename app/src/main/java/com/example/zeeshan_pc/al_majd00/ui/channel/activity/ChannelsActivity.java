package com.example.zeeshan_pc.al_majd00.ui.channel.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.channel.adapter.ChannelAdapter;

import java.util.ArrayList;

public class ChannelsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDistributors;
    private ArrayList<String> title = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        // Inflate the layout for this fragment
        recyclerViewDistributors = findViewById(R.id.recyclerViewDistributors);

        title.add("Almajd Al Tabeeaiyah");
        title.add("Almajd Holy Quran");
        title.add("Almajd Public");
        title.add("Basmah");
        title.add("Rawdatan");
        title.add("Almajd Al Hadeeth Al Nabawy");
        title.add("Almajd Documentary");
        title.add("Almajd Kids");
        title.add("Almajd Scientific");
        title.add("Masah Channel");


        ChannelAdapter adapter = new ChannelAdapter(this);
        recyclerViewDistributors.setAdapter(adapter);

    }
}
