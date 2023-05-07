package com.example.project2final;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    // variable for our array list and context
    private ArrayList<GetAndSet> dataArrayList;
    private Context context;

    // constructor
    public DataAdapter(ArrayList<GetAndSet> dataArrayList, Context context) {
        this.dataArrayList = dataArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.data_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // on below line we are setting data to our views of recycler view item.
        GetAndSet modal = dataArrayList.get(position);
        holder.userDate.setText(modal.getUserDate());
        holder.userWeight.setText(modal.getUserWeight());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(context, UpdateDataActivity.class);

                // Passing all our values.
                i.putExtra("date", modal.getUserDate());
                i.putExtra("weight", modal.getUserWeight());

                // starting our activity.
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView userDate, userWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // initializing our text views
            userDate = itemView.findViewById(R.id.idDateA);
            userWeight = itemView.findViewById(R.id.idWeightA);
        }
    }
}
