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

public class wadapter extends RecyclerView.Adapter<wadapter.MyViewHolder> {

    static Context context;
    ArrayList<waste> wasteArrayList;
    ArrayList<Integer> deletedItemPositions; // List to store positions of deleted items

    public wadapter(Context context, ArrayList<waste> wasteArrayList) {
        this.context = context;
        this.wasteArrayList = wasteArrayList;
        this.deletedItemPositions = new ArrayList<>();
    }

    @NonNull
    @Override
    public wadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.witem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull wadapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!deletedItemPositions.contains(position)) { // Check if the item is not deleted
            waste waste = wasteArrayList.get(position);

            holder.quantity.setText(waste.Quantity);
            holder.time.setText(waste.Time);
            holder.address.setText(waste.Address);

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
                                    wasteArrayList.remove(position);
                                    deletedItemPositions.add(position); // Add the deleted position
                                    notifyItemRemoved(position);
                                    Intent intent = new Intent(context, wdelivery.class);
                                    context.startActivity(intent);
                                }
                            })
                            .setNegativeButton("no", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int which) {
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
        return wasteArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView quantity, time, address;
        LinearLayout llrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.rwq);
            time = itemView.findViewById(R.id.rwt);
            address = itemView.findViewById(R.id.rwa);
            llrow = itemView.findViewById(R.id.llrow);
        }
    }
}
