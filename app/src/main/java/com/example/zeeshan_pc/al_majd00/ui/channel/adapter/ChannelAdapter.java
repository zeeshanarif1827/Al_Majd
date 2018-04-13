package com.example.zeeshan_pc.al_majd00.ui.channel.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zeeshan_pc.al_majd00.R;
import com.example.zeeshan_pc.al_majd00.ui.channel.activity.ChannelsActivity;

import java.util.ArrayList;

/**
 * Created by Muhammad Zeeshan on 4/13/2018.
 */

public class ChannelAdapter extends RecyclerView.Adapter<ChannelAdapter.VH> {

    private ArrayList<String> title = new ArrayList<>();
    Context context;

    public ChannelAdapter(Context context) {
        super();
        this.context = context;
    }

    @Override
    public ChannelAdapter.VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return new VH(LayoutInflater.from(parent.getContext()).inflate(R.layout.channel_items,parent,false));
    }

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.lbl.setText(title.get(position));
    }

    @Override
    public int getItemCount() {
        return title.size();
    }

    class VH extends RecyclerView.ViewHolder {

        TextView lbl;

        public VH(View itemView) {
            super(itemView);
            lbl = itemView.findViewById(R.id.lbl);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, ChannelsActivity.class);
                    context.startActivity(intent);
                }
            });
        }
    }

}
