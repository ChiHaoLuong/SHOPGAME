package com.example.ps14498_luongchihao_asm.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Other_Activities.Details_Activity;
import com.example.ps14498_luongchihao_asm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Topratinggame_Adapter extends RecyclerView.Adapter<Topratinggame_Adapter.viewHolder> {
    Context context;
    ArrayList<Game_Models> list = new ArrayList<>();


    public Topratinggame_Adapter(Context context, ArrayList<Game_Models> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topratinggame_items, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Game_Models models = list.get(position);
        holder.tvname.setText(models.getName());
        holder.tvdescrible.setText(models.getDescrible());
        holder.tvprice.setText("$"+models.getPrice()+"USD");
        holder.tvrating.setText(models.getRating()+"/100");
        String link = models.getImg();
        Glide.with(context).load(link).centerCrop().into(holder.iv);
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
                i.putExtra("img", models.getImg());
                i.putExtra("isBought", false);


                context.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<Game_Models> filerlist) {
        list = filerlist;
        notifyDataSetChanged();
    }

    class viewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvrating, tvname, tvdescrible, tvprice;
        RelativeLayout layout;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_topratinggame_item);
            tvname = itemView.findViewById(R.id.tv_name_topratinggame_item);
            tvrating = itemView.findViewById(R.id.tv_rating_topratinggame_item);
            tvdescrible = itemView.findViewById(R.id.tv_describle_topratinggame_item);
            tvprice = itemView.findViewById(R.id.tv_price_topratinggame_item);
            layout = itemView.findViewById(R.id.rl_home);
        }
    }
}
