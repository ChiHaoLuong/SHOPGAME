package com.example.ps14498_luongchihao_asm;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.Cart_Fragment;
import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.Category_Fragment;
import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.Home_Fragment;
import com.example.ps14498_luongchihao_asm.BottomNav_Fragment.User_Fragment;
import com.example.ps14498_luongchihao_asm.Models.User_Models;

import nl.joery.animatedbottombar.AnimatedBottomBar;

public class MainActivity extends AppCompatActivity {
    AnimatedBottomBar nav;
    FragmentManager fm;
    private String name;
    private  String username;
    private  String  password;
    private  int userId;
    private  int money;

    Cart_Fragment cart_fragment = new Cart_Fragment();
    Category_Fragment category_fragment = new Category_Fragment();
    Home_Fragment home_fragment = new Home_Fragment();
    User_Fragment user_fragment = new User_Fragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();

        layDulieuIntent();

//        Truyền dữ liệu bundle vào các fragment

        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("username", username);
        bundle.putString("password", password);
        bundle.putInt("userId", userId);
        bundle.putInt("money", money);
        User_Models models = new User_Models(userId, username, name, password, money);
        Log.i("KTDL", models.toString());
        cart_fragment.setArguments(bundle);
        category_fragment.setArguments(bundle);
        home_fragment.setArguments(bundle);
        user_fragment.setArguments(bundle);



//        bottomBar
         getSupportFragmentManager().beginTransaction().add(R.id.frl, new Home_Fragment()).commit();
        nav.setOnTabSelectListener(new AnimatedBottomBar.OnTabSelectListener() {
            @Override
            public void onTabSelected(int i, @Nullable AnimatedBottomBar.Tab lastTab, int newIndex, @NonNull AnimatedBottomBar.Tab newTab) {
                Fragment fragment = null;
                switch (newTab.getId())
                {
                    case R.id.tab_home:
                        fragment = home_fragment;


                        break;

                    case R.id.tab_category:
                        fragment = category_fragment;
                        break;

                    case R.id.tab_cart:
                        fragment = cart_fragment;
                        break;

                    case R.id.tab_user:
                        fragment = user_fragment;
                        break;
                }
                if (fragment!=null)
                {

                    fm = getSupportFragmentManager();
                    fm.beginTransaction().add(R.id.frl, fragment).commit();
                }
                else
                {
                    Log.e("Loi", "ERRO to creat Fragment");
                }
            }

            @Override
            public void onTabReselected(int i, @NonNull AnimatedBottomBar.Tab tab) {

            }
        });
    }

    private void layDulieuIntent() {
        Intent intent = getIntent();
        if (intent==null)
        {
            Log.i("dữ liệu", "NULLLLLL");
        }
        else
        {
             name = intent.getStringExtra("name");
             username = intent.getStringExtra("username");
             userId = intent.getIntExtra("userId", 100);
             password = intent.getStringExtra("password");
             money = intent.getIntExtra("money", 0);

        }
    }


    private void AnhXa() {
        nav = findViewById(R.id.bottom_bar);
    }
}