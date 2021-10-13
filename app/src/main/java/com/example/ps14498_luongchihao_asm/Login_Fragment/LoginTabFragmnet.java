package com.example.ps14498_luongchihao_asm.Login_Fragment;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.User_Fragment;
import com.example.ps14498_luongchihao_asm.MainActivity;
import com.example.ps14498_luongchihao_asm.Models.Game_Models;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;
import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.example.ps14498_luongchihao_asm.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginTabFragmnet extends Fragment {
    EditText edtusername, edtpass;
    Button btn;
    float v =0;
    TextView tv_forget;
    ProgressBar pb;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.login_fragment, container, false);

        AnhXa(root);
//            Animation
        edtusername.setTranslationX(800);
        edtpass.setTranslationX(800);
        btn.setTranslationX(800);

        edtusername.setAlpha(v);
        edtpass.setAlpha(v);
        btn.setAlpha(v);

        edtusername.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(300).start();
        edtpass.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(500).start();
        btn.animate().translationX(0).alpha(1).setDuration(800).setStartDelay(700).start();




//        Login
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edtusername.getText().toString();
                String password = edtpass.getText().toString();
                login(username, password);
                pb.setVisibility(View.VISIBLE);
            }
        });


//        Forget password
        tv_forget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Dialog dialog = new Dialog(getContext());
                dialog.setContentView(R.layout.forget_pass_dialog);
                EditText edt = dialog.findViewById(R.id.edt_resetpass_dialog);
                Button btn = dialog.findViewById(R.id.btn_resetpass_dialog);

                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        resetPass(edt, dialog);
                    }
                });
                dialog.show();
            }
        });

        return root;
    }

    private void resetPass(EditText edt, Dialog dialog) {
        String username = edt.getText().toString();
        if (username.equals("")) Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
        else
        {
            APIService.apiservice.resetPass(username).enqueue(new Callback<User_Models>() {
                @Override
                public void onResponse(Call<User_Models> call, Response<User_Models> response) {
                    if (response.code()==200)
                    {
                        String kq = response.body().getResult();
                        Toast.makeText(getContext(), kq, Toast.LENGTH_SHORT).show();
                        dialog.dismiss();

                    }
                    else
                    {
                        dialog.dismiss();
                        Log.i("KT", "Lỗi reset: ");
                    }
                }

                @Override
                public void onFailure(Call<User_Models> call, Throwable t) {
                    Log.i("KT", "Lỗi reset: "+t);
                }
            });
        }


    }

    private void login(String username, String password) {
        if (username.equals("") || password.equals("")) {
            pb.setVisibility(View.INVISIBLE);
            Toast.makeText(getContext(), "Vui lòng không để trống", Toast.LENGTH_SHORT).show();
        }
        else
        {
            APIService.apiservice.getUser(username, password).enqueue(new Callback<User_Models>() {
                @Override
                public void onResponse(Call<User_Models> call, Response<User_Models> response) {

                        if (response.code() == 200) {
                            if (response.body().getResult().equals("OK")) {
                                User_Models model = response.body();
                                Intent i = new Intent(getContext(), MainActivity.class);
                                i.putExtra("userId", model.getUserId());
                                i.putExtra("username", model.getUsername());
                                i.putExtra("name", model.getName());
                                i.putExtra("password", model.getPassword());
                                i.putExtra("money", model.getMoney());
                                startActivity(i);
                                pb.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                            } else {
                                pb.setVisibility(View.INVISIBLE);
                                Toast.makeText(getContext(), "Tạo tài khoản thất bại: " + response.body().getResult(), Toast.LENGTH_SHORT).show();
                                Log.i("KT", "Sai tài khoản mật khẩu  Link: http://192.168.9.106/gameshopphp/loginUser.php?edtUsername=" + username + "&edtPassword=" + password);
                            }
                        } else {
                            Log.i("KT", "Lỗi");
                            displayLoginInfor("Something went wrong");
                        }

                }

                @Override
                public void onFailure(Call<User_Models> call, Throwable t) {
                    Log.i("KT", "loi: " + t);
                }
            });
        }
    }

    private void displayLoginInfor(String text) {
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
        pb.setVisibility(View.VISIBLE);
    }

    public void AnhXa(ViewGroup root) {
        edtusername = root.findViewById(R.id.edt_username_login);
        edtpass = root.findViewById(R.id.edt_password_login);
        btn = root.findViewById(R.id.btn_login);
        pb = root.findViewById(R.id.pb_loginfragment);
        tv_forget = root.findViewById(R.id.tv_resetpass_login);
    }


}
