package com.example.ps14498_luongchihao_asm.Other_Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


import com.example.ps14498_luongchihao_asm.MainActivity;
import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.example.ps14498_luongchihao_asm.R;
import com.example.ps14498_luongchihao_asm.RetrofitPacket.APIService;
import com.example.ps14498_luongchihao_asm.tranformUserId;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CaptchaGG extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    CheckBox cb;
    GoogleApiClient googleApiClient;
    int userId;
//    Lấy sitekey
    String sitekey = "6LcdJOYcAAAAAOTOjxTu1Is8uMSoD67EpgmKiwn9";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha_gg);
        cb = findViewById(R.id.cb_captcha);

//        Lấy dữ liệu user
        userId = tranformUserId.getUserId(CaptchaGG.this);

//        Tạo API
        googleApiClient = new GoogleApiClient.Builder(CaptchaGG.this)
                .addApi(SafetyNet.API)
                .addConnectionCallbacks(CaptchaGG.this)
                .build();
        googleApiClient.connect();

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cb.isChecked())
                {
                    SafetyNet.SafetyNetApi.verifyWithRecaptcha(googleApiClient, sitekey)
                            .setResultCallback(new ResultCallback<SafetyNetApi.RecaptchaTokenResult>() {
                                @Override
                                public void onResult(@NonNull SafetyNetApi.RecaptchaTokenResult recaptchaTokenResult) {
                                    Status status = recaptchaTokenResult.getStatus();
                                    if((status != null && status.isSuccess()))
                                    {
                                        Toast.makeText(CaptchaGG.this, "Successfully Varified...", Toast.LENGTH_SHORT).show();
                                        cb.setTextColor(Color.GREEN);
//                                        Viết hàm thành công ở đây nè
                                        getMoney();
                                    }
                                    else Log.i("Loi gg", recaptchaTokenResult.getTokenResult()+"");
                                }

                            });
                }
                else
                {
                    cb.setTextColor(Color.BLACK);
                }
            }
        });
    }

    private void getMoney() {

        APIService.apiservice.getMoney(userId).enqueue(new Callback<User_Models>() {
            @Override
            public void onResponse(Call<User_Models> call, Response<User_Models> response) {
                if (response.isSuccessful())
                {
                    Toast.makeText(CaptchaGG.this, response.body().getResult(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User_Models> call, Throwable t) {
                Log.i("getMoney", t.getMessage());
            }
        });

        Intent i = new Intent(CaptchaGG.this, MainActivity.class);
        startActivity(i);
        finish();
    }


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }


}