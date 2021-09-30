package com.example.ps14498_luongchihao_asm.RetrofitPacket;

import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    String link = "http://192.168.0.105/gameshopphp/";

    Gson gson = new GsonBuilder().setLenient().create();
    APIService apiservice = new Retrofit.Builder()
            .baseUrl(link)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(APIService.class);

    @GET("loginUser.php")
    Call<User_Models> getUser(
            @Query("edtUsername") String username,
            @Query("edtPassword") String password);

    @GET("registerUser.php")
    Call<User_Models> InsertUser(
            @Query("edtUsername") String username,
            @Query("edtPassword") String password,
            @Query("edtName") String name
    );

    @GET("resetPasswordUser.php")
    Call<User_Models> resetPass(
            @Query("edtUsername") String username
    );

    @GET("changePasswordUser.php")
    Call<User_Models> changePass(
            @Query("edtOldpass") String oldpassword,
            @Query("edtNewpass") String newpassword
    );
}
