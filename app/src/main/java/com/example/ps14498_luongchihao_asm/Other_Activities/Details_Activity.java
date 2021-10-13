package com.example.ps14498_luongchihao_asm.Other_Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ps14498_luongchihao_asm.MainActivity;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Details_Activity extends AppCompatActivity {

    TextView tvname, tvdes, tvdev, tvrat, tvpri;
    Button btnbuy;
    boolean isbought;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        anhXa();

//        Lấy dữ liệu từ fragment
       Intent i = getIntent();
       if (i==null) Log.i("i", "Không có dữ liệu");
       String name = i.getStringExtra("name");
       String des = i.getStringExtra("des");
       String dev = i.getStringExtra("dev");
       String linkimg = i.getStringExtra("img");
       int rat = i.getIntExtra("rat", 0);
       int pri = i.getIntExtra("pri", 0);
       int gameId = i.getIntExtra("gameId", 1000);
       int userId = i.getIntExtra("userId", 1000);
       isbought = i.getBooleanExtra("isBought", false);

//       Gán giá trị cho các layout
        Glide.with(Details_Activity.this).load(linkimg).centerCrop().into(iv);
       tvname.setText(name);
       tvpri.setText("$: "+ pri+"USD");
       tvrat.setText("Rating: "+rat+"/100");
       tvdes.setText(des);
       tvdev.setText("Developer: "+dev);
       if (isbought)
       {
           btnbuy.setBackgroundColor(Color.GRAY);
           btnbuy.setEnabled(false);
       }

       Log.i("i", name+des+dev+rat+pri+isbought);


//       Kích hoạt mua
       btnbuy.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               AlertDialog.Builder builder = new AlertDialog.Builder(Details_Activity.this);
               builder.setMessage("Do you want to buy ?");
               builder.setTitle("BUY GAME");
               builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       Log.i("CHECKID", "http://192.168.9.106/gameshopphp/buyGame.php?edtGameId="+gameId+"&edtUserId="+userId);
                       APIService.apiservice.buyGame(gameId, userId).enqueue(new Callback<Responegameresult>() {
                           @Override
                           public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                               if (response.code()==200)
                               {
                                   Responegameresult responegameresult = response.body();
                                   if (responegameresult.getResult()==1)
                                   {
                                       Toast.makeText(Details_Activity.this, responegameresult.getMes(), Toast.LENGTH_SHORT).show();
                                        btnbuy.setEnabled(false);
                                        btnbuy.setBackgroundColor(Color.GRAY);

                                   }
                                   else  Toast.makeText(Details_Activity.this, responegameresult.getMes(), Toast.LENGTH_SHORT).show();
                               }
                               else Log.i("bugg", response.errorBody()+"");
                           }

                           @Override
                           public void onFailure(Call<Responegameresult> call, Throwable t) {
                               Toast.makeText(Details_Activity.this, "Lỗi: "+t, Toast.LENGTH_SHORT).show();
                               Log.i("bugg", t.getMessage());
                           }
                       });

                   }
               });

               builder.setNegativeButton("CANCLE", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {

                   }
               });

               builder.create().show();
           }
       });



    }




    private void anhXa() {
        tvname = findViewById(R.id.tvname_details);
        tvdes = findViewById(R.id.tvdescrible_details);
        tvdev = findViewById(R.id.tvdeveloper_details);
        tvrat = findViewById(R.id.tvrating_details);
        tvpri = findViewById(R.id.tvprice_details);
        btnbuy = findViewById(R.id.btn_buy_details);
        iv = findViewById(R.id.iv_details);
    }
}