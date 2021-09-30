package com.example.ps14498_luongchihao_asm.Login_Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupTabFragment extends Fragment {
    EditText edtemail, edtpass, edtname, edtrepass;
    Button btn;
    float v= 0;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.signup_fragment, container, false);

        AnhXa(root);

//            Animation
        edtemail.setTranslationX(800);
        edtpass.setTranslationX(800);
        edtname.setTranslationX(800);
        edtrepass.setTranslationX(800);
        btn.setTranslationX(800);

        edtemail.setAlpha(v);
        edtname.setAlpha(v);
        edtpass.setAlpha(v);
        edtrepass.setAlpha(v);
        btn.setAlpha(v);

        edtemail.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edtpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();
        edtname.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        edtrepass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(900).start();
        btn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(1100).start();


//      Đăng ký
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtemail.getText().toString();
                String name = edtname.getText().toString();
                String pass = edtpass.getText().toString();
                String repass = edtrepass.getText().toString();
                if (pass.equals(repass))
                {
                    register(username, name, pass);
                }
                else Toast.makeText(getContext(), "Mật khẩu không trùng", Toast.LENGTH_SHORT).show();
            }
        });

        return root;



    }

    private void register(String username, String name, String pass) {
        if (username.equals("")||name.equals("")||pass.equals(""))
            Toast.makeText(getContext(), "Vui lòng không bỏ trống thông tin", Toast.LENGTH_SHORT).show();
        else
        {
            APIService.apiservice.InsertUser(username, pass, name).enqueue(new Callback<User_Models>() {
                @Override
                public void onResponse(Call<User_Models> call, Response<User_Models> response) {
                    if (response.code() == 200) {
                        String kq = response.body().getResult();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                        builder.setMessage(kq);
                        builder.create().show();
                    }
                }

                @Override
                public void onFailure(Call<User_Models> call, Throwable t) {
                    Log.i("KT", "Lỗi: " + t);
                }
            });
        }
    }

    private void AnhXa(ViewGroup root) {
        edtemail = root.findViewById(R.id.edt_username_signup);
        edtname = root.findViewById(R.id.edt_name_signup);
        edtpass = root.findViewById(R.id.edt_password_signup);
        edtrepass = root.findViewById(R.id.edt_repassword_signup);
        btn = root.findViewById(R.id.btn_signup);
    }
}
