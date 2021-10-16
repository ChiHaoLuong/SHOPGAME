package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Adapter.Categorygame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;
import com.example.ps14498_luongchihao_asm.tranformUserId;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Category_Fragment extends Fragment {
    ArrayList<Game_Models> listfreegame, listpaygame, listallgame;
    Categorygame_Adapter categorygame_adapter;
    RecyclerView rcvfree, rcvpay, rcvall;
    int userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_category_, container, false);


        //        Lấy dữ liệu User
        userId = tranformUserId.getUserId(getContext());


            anhXa(root);
            addList();


        return root;
    }

    private void anhXa(ViewGroup root) {
        rcvall = root.findViewById(R.id.rcv_allgame);
        rcvfree = root.findViewById(R.id.rcv_gamefree);
        rcvpay = root.findViewById(R.id.rcv_gamepaying);
    }

    private void setRcvpay(ArrayList<Game_Models> listpaygame) {

        rcvpay.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listpaygame, userId);
        rcvpay.setAdapter(categorygame_adapter);


    }

    public void setRcvfree(ArrayList<Game_Models> listfreegame)
    {
        rcvfree.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listfreegame, userId);
        rcvfree.setAdapter(categorygame_adapter);
    }

    public void setRcvall(ArrayList<Game_Models> listallgame)
    {

        rcvall.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listallgame, userId);
        rcvall.setAdapter(categorygame_adapter);
    }


    private void addList() {
//        List Free game
        listfreegame = new ArrayList<>();
        APIService.apiservice.getFreeGame().enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                if (response.code()==200)
                {
                    Responegameresult responegameresult = response.body();
                    if (responegameresult.getResult()==1)
                    {
                        listfreegame = responegameresult.listgame_models;
                        setRcvfree(listfreegame);
                    }
                    else  Toast.makeText(getContext(), responegameresult.getMes(), Toast.LENGTH_SHORT).show();
                }
                else Log.i("bugg", response.errorBody()+"");

            }

            @Override
            public void onFailure(Call<Responegameresult> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi: "+t, Toast.LENGTH_SHORT).show();
                Log.i("bugg", t.getMessage());
            }
        });


//        List All Game
        listallgame = new ArrayList<>();
        APIService.apiservice.getAllGame().enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                if (response.code()==200)
                {
                    Responegameresult responegameresult = response.body();
                    if (responegameresult.getResult()==1)
                    {
                        listallgame = responegameresult.listgame_models;
                        setRcvall(listallgame);
                    }
                    else  Toast.makeText(getContext(), responegameresult.getMes(), Toast.LENGTH_SHORT).show();
                }
                else Log.i("bugg", response.errorBody()+"");

            }

            @Override
            public void onFailure(Call<Responegameresult> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi: "+t, Toast.LENGTH_SHORT).show();
                Log.i("bugg", t.getMessage());
            }
        });


//        List Pay game
        listpaygame = new ArrayList<>();
        APIService.apiservice.getPayGame().enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                if (response.code()==200)
                {
                    Responegameresult responegameresult = response.body();
                    if (responegameresult.getResult()==1)
                    {
                        listpaygame = responegameresult.listgame_models;
                        setRcvpay(listpaygame);
                    }
                    else  Toast.makeText(getContext(), responegameresult.getMes(), Toast.LENGTH_SHORT).show();
                }
                else Log.i("bugg", response.errorBody()+"");

            }

            @Override
            public void onFailure(Call<Responegameresult> call, Throwable t) {
                Toast.makeText(getContext(), "Lỗi: "+t, Toast.LENGTH_SHORT).show();
                Log.i("bugg", t.getMessage());
            }
        });
    }
}