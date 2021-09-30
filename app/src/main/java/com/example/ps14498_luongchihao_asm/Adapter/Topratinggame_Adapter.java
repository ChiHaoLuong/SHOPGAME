package com.example.ps14498_luongchihao_asm.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Models.Game_Models;
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
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_topratinggame_item);
            tvname = itemView.findViewById(R.id.tv_name_topratinggame_item);
            tvrating = itemView.findViewById(R.id.tv_rating_topratinggame_item);
            tvdescrible = itemView.findViewById(R.id.tv_describle_topratinggame_item);
            tvprice = itemView.findViewById(R.id.tv_price_topratinggame_item);
        }
    }
}
