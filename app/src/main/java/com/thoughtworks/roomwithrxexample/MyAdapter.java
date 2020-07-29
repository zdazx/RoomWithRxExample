package com.thoughtworks.roomwithrxexample;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter {
    private ArrayList<Person> personSet;

    public MyAdapter() {
        personSet = new ArrayList<>();
    }

    public void setPersonSet(ArrayList<Person> personSet) {
        this.personSet = personSet;
        this.notifyDataSetChanged();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView nameView;
        public TextView ageView;
        public TextView genderView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            nameView = itemView.findViewById(R.id.name_view);
            ageView = itemView.findViewById(R.id.age_view);
            genderView = itemView.findViewById(R.id.gender_view);
        }
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_view_item, parent, false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Person person = personSet.get(position);
        ((MyViewHolder) holder).nameView.setText(person.name);
        ((MyViewHolder) holder).ageView.setText(String.valueOf(person.age));
        ((MyViewHolder) holder).genderView.setText(String.valueOf(person.gender));
    }

    @Override
    public int getItemCount() {
        return personSet.size();
    }
}

