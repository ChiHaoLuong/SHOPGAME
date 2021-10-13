package com.example.ps14498_luongchihao_asm.BottomNav_Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.example.ps14498_luongchihao_asm.Other_Activities.Login_Activity;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class User_Fragment extends Fragment {
    Button btngetmonney;
    TextView tvchangepass, tvlogout, tvusername, tvname, tvusermoney;
    String name, username, password;
    int money, userId;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_user_, container, false);

        anhXa(root);

        dangXuat(tvlogout);

//        Lấy dữ liệu Bundle
        Bundle bundle = getArguments();
        if (bundle!=null)
        {
            name = bundle.getString("name");
            username = bundle.getString("username");
            password = bundle.getString("password");
            money = bundle.getInt("money");
            userId = bundle.getInt("userId");
            User_Models models = new User_Models(userId, username, name, password, money);
            Log.i("User_fragment", models.toString());
        }
        else Log.i("User_fragment", "Không nhận được dữ liệu");


//        Gán dữ liệu
        APIService.apiservice.getUserById(userId).enqueue(new Callback<User_Models>() {
            @Override
            public void onResponse(Call<User_Models> call, Response<User_Models> response) {
                if (response.isSuccessful())
                {
                    User_Models models = response.body();
                    Log.i("USERSSSSS", models.toString());
                }
                else  Log.i("USERSSSSS", response.errorBody().toString());
            }

            @Override
            public void onFailure(Call<User_Models> call, Throwable t) {
                Log.i("USERSSSSS", t.toString());
            }
        });


//        Change pass
        tvchangepass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.change_pass_dialog);
                EditText edtnew = dialog.findViewById(R.id.edt_newpass_dialog);
                EditText edtold = dialog.findViewById(R.id.edt_oldpass_dialog);
                EditText edtre = dialog.findViewById(R.id.edt_repass_dialog);
                Button btn = dialog.findViewById(R.id.btn_changepass_dialog);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        changePass(userId,edtold,edtnew, edtre, dialog);
                    }
                });
                dialog.show();
            }
        });


        return root;
    }

    private void changePass(int userId, EditText edtold, EditText edtnew, EditText edtre, Dialog dialog) {
        String oldpass = edtold.getText().toString();
        String newpass = edtnew.getText().toString();
        String repass = edtre.getText().toString();

        if (oldpass.equals("")||newpass.equals("")||repass.equals(""))
        {
            Toast.makeText(getContext(), "Không để trống dữ liệu", Toast.LENGTH_SHORT).show();
        }
        else
        {
            if (newpass.equals(repass))
            {
                APIService.apiservice.changePass(userId,oldpass, newpass).enqueue(new Callback<User_Models>() {
                    @Override
                    public void onResponse(Call<User_Models> call, Response<User_Models> response) {
                        if (response.code()==200)
                        {
                            String kq = response.body().getResult();
                            Toast.makeText(getContext(), kq, Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                        else  Log.i("KT", "Lỗi đổi MK");
                    }

                    @Override
                    public void onFailure(Call<User_Models> call, Throwable t) {
                        Log.i("KT", "Lỗi đổi MK: "+t);
                    }
                });

            }
            else Toast.makeText(getContext(), "2 Mật khẩu mới không trùng nhau", Toast.LENGTH_SHORT).show();
        }
    }


    private void dangXuat(TextView tvlogout) {
        tvlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Do you want to logout ?");
                builder.setTitle("LOGOUT");
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(getContext(), Login_Activity.class);
                        startActivity(intent);

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
    }

    private void anhXa(ViewGroup root) {
        btngetmonney = root.findViewById(R.id.btn_getmoney);
        tvchangepass = root.findViewById(R.id.tv_user_change_pass);
        tvlogout = root.findViewById(R.id.tv_user_logout);
        tvname = root.findViewById(R.id.tv_user_name);
        tvusername = root.findViewById(R.id.tv_user_username);
        tvusermoney = root.findViewById(R.id.tv_user_money);
    }
}