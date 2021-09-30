package com.example.covidtracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    int m = 1;
    Context context;
    List<ModelClass> countryList;

    public Adapter(Context context, List<ModelClass> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout_item, null, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        ModelClass modelClass = countryList.get(position);
        if(m == 1) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases())));
        } else if(m == 2) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
        } else if(m == 3) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getDeaths())));
        } else {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive())));
        }
        holder.country.setText(modelClass.getCountry());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cases, country;
        public ViewHolder(View itemView) {
            super(itemView);
            cases=itemView.findViewById(R.id.countrycase);
            country = itemView.findViewById(R.id.countryname);
        }
    }

    public void filter(String chartext) {
        if(chartext.equals("cases")) {
            m = 1;
        } else if(chartext.equals("recovered")) {
            m = 2;
        } else if(chartext.equals("deaths")) {
            m = 3;
        } else {
            m = 4;
        }
        notifyDataSetChanged();
    }
}
