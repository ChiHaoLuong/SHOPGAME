package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Adapter.Cartgame_Adapter;
import com.example.ps14498_luongchihao_asm.Adapter.Categorygame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.R;

import java.util.ArrayList;

public class Cart_Fragment extends Fragment {
    ArrayList<Game_Models> listgame;
    Cartgame_Adapter adapter;
    RecyclerView rcv;
    EditText edtsearch;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_cart_, container, false);

        anhXa(root);
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

    private void anhXa(ViewGroup root) {
        rcv = root.findViewById(R.id.rcv_cart);
        edtsearch = root.findViewById(R.id.edt_search_cart);
    }

    private void setRCV(ArrayList<Game_Models> listgame) {

        rcv.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        adapter = new Cartgame_Adapter(getContext(),listgame);
        rcv.setAdapter(adapter);

    }


    private void addList() {
        listgame = new ArrayList<>();
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "GTA", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
        listgame.add(new Game_Models(0, "ABC", 200, "https://cdn.tgdd.vn/Files/2020/05/15/1255578/gta5-free_800x450.jpg", "The Grand Theft Auto V: Premium Edition includes the complete GTAV story, Grand Theft Auto Online and all existing gameplay upgrades and content. You’ll also get the Criminal Enterprise Starter Pack, the fastest way to jumpstart your criminal empire in GTA Online.", "Rockstar Games", 0, 97));
    }
}