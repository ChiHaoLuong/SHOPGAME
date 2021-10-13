package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.ps14498_luongchihao_asm.Adapter.Topratinggame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment {
    ViewFlipper flipper ;
    RecyclerView rcv;
    Game_Models game_models;
    ArrayList<Game_Models> listgame;
    Topratinggame_Adapter adapter;
    EditText edtsearch;
    int userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_, container, false);

        Anhxa(root);

        //        Lấy dữ liệu User
        Bundle bundle = getArguments();
        if (bundle!=null) userId = bundle.getInt("userId");

//        Flipper
        Log.i("auto", flipper.isAutoStart()+"");
        flipper.startFlipping();
        flipper.setFlipInterval(3000);

        addList();



        searchGame(edtsearch);

        return root;
    }

    private void searchGame(EditText edtsearch) {
        edtsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                filter(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void filter(String text) {
        ArrayList<Game_Models> filerlist = new ArrayList<>();
        for (Game_Models item: listgame )
        {
            if (item.getName().toLowerCase().contains(text.toLowerCase()))
            {
                filerlist.add(item);
            }
        }
        adapter.filterList(filerlist);
    }

    private void setRCV(ArrayList<Game_Models> listgame) {
        rcv.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new Topratinggame_Adapter(getContext(),listgame, userId);
        rcv.setAdapter(adapter);
    }

    private void addList() {
        listgame = new ArrayList<>();
        APIService.apiservice.getTopRatingGame().enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
               if (response.code()==200)
               {
                   Responegameresult responegameresult = response.body();
                   if (responegameresult.getResult()==1)
                   {
                       listgame = responegameresult.listgame_models;
                       setRCV(listgame);
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

    private void Anhxa(ViewGroup root) {
        flipper = root.findViewById(R.id.vf_home);
        rcv = root.findViewById(R.id.rcv_topratinggame);
        edtsearch = root.findViewById(R.id.edt_search_home);

    }
}