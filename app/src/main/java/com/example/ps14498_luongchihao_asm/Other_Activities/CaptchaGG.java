package com.example.ps14498_luongchihao_asm.Other_Activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;


import com.example.ps14498_luongchihao_asm.R;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.safetynet.SafetyNet;
import com.google.android.gms.safetynet.SafetyNetApi;

public class CaptchaGG extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks {
    CheckBox cb;
    GoogleApiClient googleApiClient;

//    Lấy sitekey
    String sitekey = "6LcV8s4cAAAAAEjegGQb9MtiG0__a5QVot_edzER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_captcha_gg);
        cb = findViewById(R.id.cb_captcha);

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
                                    }
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


    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }
}