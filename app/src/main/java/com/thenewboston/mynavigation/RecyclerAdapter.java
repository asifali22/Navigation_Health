package com.thenewboston.mynavigation;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.RecyclerViewHolder> {

    String[] country;


    public RecyclerAdapter(String[] country) {
        this.country = country;


    }


    @Override
    public RecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.raw_layout, parent, false);

        RecyclerViewHolder recyclerViewHolder = new RecyclerViewHolder(view);

        return recyclerViewHolder;

    }

    @Override
    public void onBindViewHolder(RecyclerViewHolder holder, int position) {

        holder.country.setText(country[position]);


    }


    @Override
    public int getItemCount() {
        return country.length;
    }


    public static class RecyclerViewHolder extends RecyclerView.ViewHolder  {

        TextView country;

        public RecyclerViewHolder(View view) {
            super(view);
            country = (TextView) view.findViewById(R.id.country);
            country.setClickable(true);


        }

        /*
        implements View.OnClickListener
        country.setOnClickListener(this);
        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(), "You Clicked " + getAdapterPosition(), Toast.LENGTH_SHORT).show();
            final Intent intent;
            if (getAdapterPosition() == 0)
                intent = new Intent(view.getContext(), InnerActivity.class);
            else
                intent = null;
            if (intent != null)
                view.getContext().startActivity(intent);


        }
        */


    }


}

