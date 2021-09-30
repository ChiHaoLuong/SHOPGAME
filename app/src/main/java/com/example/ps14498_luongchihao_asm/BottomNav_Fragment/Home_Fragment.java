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
import android.widget.ViewFlipper;

import com.example.ps14498_luongchihao_asm.Adapter.Topratinggame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class Home_Fragment extends Fragment {
    ViewFlipper flipper ;
    RecyclerView rcv;
    Game_Models game_models;
    ArrayList<Game_Models> listgame;
    Topratinggame_Adapter adapter;
    EditText edtsearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_home_, container, false);

        Anhxa(root);

//        Flipper
        Log.i("auto", flipper.isAutoStart()+"");
        flipper.startFlipping();
        flipper.setFlipInterval(3000);

        addList();

        setRCV(listgame);

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
        adapter = new Topratinggame_Adapter(getContext(),listgame);
        rcv.setAdapter(adapter);
    }

    private void addList() {
        listgame = new ArrayList<>();
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTB", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTC", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTD", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTE", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTF", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
    }

    private void Anhxa(ViewGroup root) {
        flipper = root.findViewById(R.id.vf_home);
        rcv = root.findViewById(R.id.rcv_topratinggame);
        edtsearch = root.findViewById(R.id.edt_search_home);

    }
}