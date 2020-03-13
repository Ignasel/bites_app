package com.dinner.dinner;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class AdapterDinner extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final String ENTRY = "com.dinner.dinner.ENTRY";

    private Context context;
    private LayoutInflater inflater;
    List<Dinner> data = Collections.emptyList();
    Dinner current;
    int currentPos = 0;

    // create constructor to initialize context and data sent from MainActivity
    public AdapterDinner(Context context, List<Dinner> data) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
        for (Dinner dinner : data) {
            Log.e("bites", dinner.getPletimasType() + " " + dinner.getKomentaras());
        }
    }

    // Inflate the layout when ViewHolder created
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.container_dinner, parent, false);
        MyHolder holder = new MyHolder(view);
        return holder;
    }

    // Bind data
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Get current position of item in RecyclerView to bind data and assign values from list
        MyHolder myHolder = (MyHolder) holder;
        Dinner current = data.get(position);
        myHolder.textPletimasType.setText("Pletimas: " + current.getPletimasType());
        myHolder.textMotinele.setText("Motinele: " + current.getMotinele());
        myHolder.textKomentaras.setText("Komentaras: " + current.getKomentaras());
        myHolder.textMedus.setText("Medus: " + current.getMedus());
        myHolder.textSeimosNr.setText("Numeris: " + current.getSeimosNr());
        Log.e("dinner", current.getPletimasType() + " " + current.getKomentaras());

    }

    // return total item from List
    @Override
    public int getItemCount() {
        return data.size();
    }


    class MyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView textPletimasType;
        TextView textMotinele;
        TextView textSeimosNr;
        TextView textMedus;
        TextView textKomentaras;

        // create constructor to get widget reference
        public MyHolder(View itemView) {
            super(itemView);
            textPletimasType = (TextView) itemView.findViewById(R.id.textPletimasType);
            textMotinele = (TextView) itemView.findViewById(R.id.textMotinele);
            textKomentaras = (TextView) itemView.findViewById(R.id.textKomentaras);
            textSeimosNr = (TextView) itemView.findViewById(R.id.textSeimosNr);
            textMedus = (TextView) itemView.findViewById(R.id.textMedus);
        }

        // Click event for item
        @Override
        public void onClick(View v) {
            int itemPosition = getAdapterPosition();

            Dinner dinner = data.get(itemPosition);

            Intent intent = new Intent(context, NewEntryActivity.class);
            intent.putExtra(ENTRY, dinner);
            context.startActivity(intent);
        }

    }

}