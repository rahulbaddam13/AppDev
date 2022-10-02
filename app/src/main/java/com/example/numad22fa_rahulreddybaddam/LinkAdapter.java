package com.example.numad22fa_rahulreddybaddam;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LinkAdapter extends RecyclerView.Adapter<LinkViewHolder>{
    private final ArrayList<LinkCollectorItem> link;
    private LinkItemClickListener linkListener;


    public LinkAdapter(ArrayList<LinkCollectorItem> link) {
        this.link = link;
    }

    @NonNull
    @Override
    public LinkViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new LinkViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.link_collector_item, parent, false), linkListener);
    }

    @Override
    public void onBindViewHolder(LinkViewHolder holder, int position)  {
        holder.name.setText(link.get(position).getName());
        holder.url.setText(link.get(position).getUrl());
    }

    @Override
    public int getItemCount() {

        return link.size();
    }

    public void setOnLinkItemClickListener(LinkItemClickListener linkListener) {
        this.linkListener = linkListener;
    }


}