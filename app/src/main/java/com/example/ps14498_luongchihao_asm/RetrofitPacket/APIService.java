package com.example.ps14498_luongchihao_asm.RetrofitPacket;

import com.example.ps14498_luongchihao_asm.Models.Responegameresult;
import com.example.ps14498_luongchihao_asm.Models.User_Models;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {

    String link = "http://192.168.9.106/gameshopphp/";

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
            @Query("edtUserId") int userId,
            @Query("edtOldpass") String oldpassword,
            @Query("edtNewpass") String newpassword
    );

    @GET("getALLGame.php")
    Call<Responegameresult> getAllGame(
    );

    @GET("getTopRatingGame.php")
    Call<Responegameresult> getTopRatingGame(
    );

    @GET("getFreeGame.php")
    Call<Responegameresult> getFreeGame(
    );

    @GET("getPayGame.php")
    Call<Responegameresult> getPayGame(
    );

    @GET("buyGame.php")
    Call<Responegameresult> buyGame(
        @Query("edtGameId") int gameId,
        @Query("edtUserId") int userId
    );

    @GET("getUserGame.php")
    Call<Responegameresult> getUserGame(
            @Query("edtUserId") int userId
    );

    @GET("getUserById.php")
    Call<User_Models> getUserById(
            @Query("edtUserId") int userId
    );

    @GET("dropGame.php")
    Call<Responegameresult> dropGame(
            @Query("edtGameId") int gameId,
            @Query("edtUserId") int userId
    );

    @GET("getMoney.php")
    Call<User_Models> getMoney(
            @Query("edtUserId") int userId
    );

}
