package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Adapter.Categorygame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.R;

import java.util.ArrayList;

public class Category_Fragment extends Fragment {
    ArrayList<Game_Models> listgame;
    Categorygame_Adapter categorygame_adapter;
    RecyclerView rcvfree, rcvpay, rcvall;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_category_, container, false);

            anhXa(root);
            addList();
        setRCV(listgame);

        return root;
    }

    private void anhXa(ViewGroup root) {
        rcvall = root.findViewById(R.id.rcv_allgame);
        rcvfree = root.findViewById(R.id.rcv_gamefree);
        rcvpay = root.findViewById(R.id.rcv_gamepaying);
    }

    private void setRCV(ArrayList<Game_Models> listgame) {
//        Free
        rcvfree.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listgame);
        rcvfree.setAdapter(categorygame_adapter);

//        Pay
        rcvpay.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listgame);
        rcvpay.setAdapter(categorygame_adapter);

//        All
        rcvall.setLayoutManager(new GridLayoutManager(getContext(), 2));
        categorygame_adapter = new Categorygame_Adapter(getContext(),listgame);
        rcvall.setAdapter(categorygame_adapter);
    }


    private void addList() {
        listgame = new ArrayList<>();
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
    }
}