package com.example.ps14498_luongchihao_asm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Other_Activities.Details_Activity;
import com.example.ps14498_luongchihao_asm.R;

import java.util.ArrayList;

public class Categorygame_Adapter extends RecyclerView.Adapter<Categorygame_Adapter.viewHolder> {
    Context context;
    ArrayList<Game_Models> list = new ArrayList<>();


    public Categorygame_Adapter(Context context, ArrayList<Game_Models> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.categorygame_items, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Game_Models models = list.get(position);
        holder.tvname.setText(models.getName());
        holder.tvprice.setText("$"+models.getPrice()+"USD");
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, Details_Activity.class);
                i.putExtra("gameId", models.getGameId());
                i.putExtra("name", models.getName());
                i.putExtra("des", models.getDescrible());
                i.putExtra("dev", models.getDeveloper());
                i.putExtra("rat", models.getRating());
                i.putExtra("pri", models.getPrice());
                i.putExtra("isBought", false);


                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvname, tvprice;
        LinearLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_freegame_item);
            tvname = itemView.findViewById(R.id.tv_name_freegame_item);
            tvprice = itemView.findViewById(R.id.tv_price_freegame_item);
            layout = itemView.findViewById(R.id.ln_category);
        }
    }
}
