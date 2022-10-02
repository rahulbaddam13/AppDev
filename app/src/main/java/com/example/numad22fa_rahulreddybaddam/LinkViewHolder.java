package com.example.numad22fa_rahulreddybaddam;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class LinkViewHolder extends RecyclerView.ViewHolder {
    public TextView name;
    public TextView url;

    public LinkViewHolder(View itemView, LinkItemClickListener linkListener) {
        super(itemView);
        name = itemView.findViewById(R.id.link_name);
        url = itemView.findViewById(R.id.link_url);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linkListener != null) {
                    int pos = getLayoutPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        linkListener.linkClick(url.getText().toString());
                    }
                }
            }
        });
    }


}
