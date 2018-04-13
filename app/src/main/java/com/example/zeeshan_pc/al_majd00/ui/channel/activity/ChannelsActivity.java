package com.example.zeeshan_pc.al_majd00.ui.channel.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.dashboard.activity.DashboardActivity;

import java.util.ArrayList;

public class ChannelsActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDistributors;
    private ArrayList<String> title = new ArrayList<>();
    ImageView channelBackBtn;
    Typeface ubuntu_light_font;
    TextView headerText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channels);

        font();
        channelBackBtn = findViewById(R.id.channelBackBtn);
        headerText = findViewById(R.id. headerText);

        headerText.setText("Channels");

        channelBackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(ChannelsActivity.this, DashboardActivity.class));
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            }
        });

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


        ChannelAdapter adapter = new ChannelAdapter();
        recyclerViewDistributors.setAdapter(adapter);
    }

    class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.VH>{

        @Override
        public VH onCreateViewHolder(ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_items,parent,false));
        }

        @Override
        public void onBindViewHolder(VH holder, int position) {
        //    holder.img.setImageResource(images.get(position));
            holder.lbl.setText(title.get(position));
        }

        @Override
        public int getItemCount() {
            return title.size();
        }

        class VH extends RecyclerView.ViewHolder{

            TextView lbl;
            ImageView img;

            public VH(View itemView) {
                super(itemView);
            //    img = itemView.findViewById(R.id.img);
                lbl = itemView.findViewById(R.id.lbl);
//                itemView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent = new Intent(this,ScheduleActivity.class);
//                        startActivity(intent);
//                    }
//                });
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        finish();
        startActivity(new Intent(ChannelsActivity.this, DashboardActivity.class));
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
    }

    void font() {
        ubuntu_light_font = Typeface.createFromAsset(ChannelsActivity.this.getAssets(), "font/ubuntu_light.ttf");
    }
}
