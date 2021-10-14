package com.example.ps14498_luongchihao_asm.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.Cart_Fragment;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.Other_Activities.Details_Activity;
import com.example.ps14498_luongchihao_asm.Other_Activities.Login_Activity;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cartgame_Adapter extends RecyclerView.Adapter<Cartgame_Adapter.viewHolder>{
    Context context;
    ArrayList<Game_Models> list = new ArrayList<>();
    int userId;


    public Cartgame_Adapter(Context context, ArrayList<Game_Models> list, int userId) {
        this.context = context;
        this.list = list;
        this.userId = userId;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cartgame_items, parent, false);
        return new viewHolder(view);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        Game_Models models = list.get(position);
        holder.tvname.setText(models.getName());
        String link = models.getImg();
        Glide.with(context).load(link).centerCrop().into(holder.iv);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Do you want to drop this game?");
                builder.setTitle("DROP GAME");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dropGame(userId, models.getGameId());

                    }
                });

                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.create().show();
            }
        });
        holder.rl.setOnClickListener(new View.OnClickListener() {
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
                i.putExtra("userId", userId);
                i.putExtra("isBought", true);


                context.startActivity(i);
            }
        });
    }

    private void dropGame(int userId, int gameId) {
        Log.i("Link Dropgame", "http://192.168.0.103/gameshopphp/dropGame.php?edtUserId="+userId+"&edtGameId="+gameId);
        APIService.apiservice.dropGame(gameId, userId).enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                if (response.isSuccessful())
                {
                    Responegameresult model = response.body();
                   Toast.makeText(context, model.getMes(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Responegameresult> call, Throwable t) {

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void filterList(ArrayList<Game_Models> filterlist){
        list = filterlist;
        notifyDataSetChanged();
    }



    class viewHolder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tvname;
        RelativeLayout rl;
        Button btn;
        public viewHolder(@NonNull View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv_cartgame_item);
            rl = itemView.findViewById(R.id.rl_cart);
            tvname = itemView.findViewById(R.id.tv_name_cartgame_item);
            btn = itemView.findViewById(R.id.btn_drop_cart_item);
        }
    }
}
