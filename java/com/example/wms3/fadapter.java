package com.example.wms3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class fadapter extends RecyclerView.Adapter<fadapter.MyViewHolder> {

    static Context context;
    ArrayList<food> foodArrayList;
    ArrayList<Integer> deletedItemPositions; // List to store positions of deleted items

    public fadapter(Context context, ArrayList<food> foodArrayList) {
        this.context = context;
        this.foodArrayList = foodArrayList;
        this.deletedItemPositions = new ArrayList<>();
    }

    @NonNull
    @Override
    public fadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull fadapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!deletedItemPositions.contains(position)) { // Check if the item is not deleted
            food food = foodArrayList.get(position);

            holder.fooditems.setText(food.Fooditems);
            holder.quantity.setText(food.Quantity);
            holder.time.setText(food.Time);
            holder.address.setText(food.Address);

            holder.llrow.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context)
                            .setTitle("CONFIRM")
                            .setIcon(R.drawable.baseline_check_24)
                            .setMessage("Do you want to confirm?")
                            .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    foodArrayList.remove(position);
                                    deletedItemPositions.add(position); // Add the deleted position
                                    notifyItemRemoved(position);

                                    Intent intent = new Intent(context, fdelivery.class);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
                                    // Handle negative button click or do nothing
                                }
                            });

                    builder.show();
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return foodArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView fooditems, quantity, time, address;
        LinearLayout llrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            fooditems = itemView.findViewById(R.id.rff);
            quantity = itemView.findViewById(R.id.rfq);
            time = itemView.findViewById(R.id.rft);
            address = itemView.findViewById(R.id.rfa);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }
}
