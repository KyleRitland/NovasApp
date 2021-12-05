package com.example.novasapp;

import android.view.View;
import android.widget.TextView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class eventsViewHolder extends RecyclerView.ViewHolder {

    TextView event_type,event_date,event_time,event_notes,event_id;
    ImageView eventPic,editItem;

    public eventsViewHolder(@NonNull View v) {
        super(v);

        event_type = v.findViewById(R.id.event_type);
        event_date = v.findViewById(R.id.event_date);
        event_time = v.findViewById(R.id.event_time);
        event_notes = v.findViewById(R.id.event_notes);
        event_id = v.findViewById(R.id.event_id);
        eventPic = v.findViewById(R.id.eventPic);
        editItem = v.findViewById(R.id.editEvent);


    }
}
