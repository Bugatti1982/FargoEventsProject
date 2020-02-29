package com.welch.fargoeventspage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

class EventAdapter extends RecyclerView.Adapter<EventAdapter.MyViewHolder> {
    private ArrayList<DataModel> dataSet;
    protected Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleView;
        TextView locationView;
        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.titleView = (TextView) itemView.findViewById(R.id.event_title);
            this.locationView = (TextView) itemView.findViewById(R.id.event_location);
            this.imageView = (ImageView) itemView.findViewById(R.id.event_image);
        }
    }

    public EventAdapter(ArrayList<DataModel> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        context = parent.getContext();
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView titleView = holder.titleView;
        TextView locationView = holder.locationView;
        ImageView imageView = holder.imageView;

        titleView.setText(dataSet.get(listPosition).getTitle());
        locationView.setText(dataSet.get(listPosition).getLocation());
        Picasso.with(context).load(dataSet.get(listPosition).getImage_url()).into(imageView);
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}