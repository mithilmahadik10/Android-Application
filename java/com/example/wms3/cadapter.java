package com.example.wms3;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class cadapter extends RecyclerView.Adapter<cadapter.MyViewHolder> {

    static Context context;
    static ArrayList<clothes> clothesArrayList;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    ArrayList<Integer> deletedItemPositions; // List to store positions of deleted items

    public cadapter(Context context, ArrayList<clothes> clothesArrayList) {
        this.context = context;
        this.clothesArrayList = clothesArrayList;
        this.deletedItemPositions = new ArrayList<>();
    }

    @NonNull
    @Override
    public cadapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.citem, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull cadapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (!deletedItemPositions.contains(position)) { // Check if the item is not deleted
            clothes clothes = clothesArrayList.get(position);

            holder.quantity.setText(clothes.Quantity);
            holder.time.setText(clothes.Time);
            holder.address.setText(clothes.Address);

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
                                    clothesArrayList.remove(position);
                                    deletedItemPositions.add(position); // Add the deleted position
                                    notifyItemRemoved(position);

                                    Intent intent = new Intent(context, cdelivery.class);
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
        return clothesArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView quantity, time, address;
        LinearLayout llrow;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            quantity = itemView.findViewById(R.id.rcq);
            time = itemView.findViewById(R.id.rct);
            address = itemView.findViewById(R.id.rca);
            llrow = itemView.findViewById(R.id.llrow);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Toast.makeText(context, "item:" + position, Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(context, cdelivery.class);
            context.startActivity(intent);
            //intent.putExtra("Address",clothesArrayList.get(position).getAddress());
        }
    }
}
