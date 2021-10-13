package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ps14498_luongchihao_asm.Adapter.Cartgame_Adapter;
import com.example.ps14498_luongchihao_asm.Adapter.Categorygame_Adapter;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Cart_Fragment extends Fragment {
    ArrayList<Game_Models> listgame;
    Cartgame_Adapter adapter;
    RecyclerView rcv;
    EditText edtsearch;
    int userId;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_cart_, container, false);

        //        Lấy dữ liệu User
        Bundle bundle = getArguments();
        if (bundle!=null) userId = bundle.getInt("userId");

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
        adapter = new Cartgame_Adapter(getActivity(),listgame, userId);
        rcv.setAdapter(adapter);

    }


    private void addList() {
        listgame = new ArrayList<>();
        APIService.apiservice.getUserGame(userId).enqueue(new Callback<Responegameresult>() {
            @Override
            public void onResponse(Call<Responegameresult> call, Response<Responegameresult> response) {
                if (response.code()==200)
                {
                    Responegameresult responegameresult = response.body();
                    if (responegameresult.getResult()==1)
                    {
                        listgame = responegameresult.getListgame_models();
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
}