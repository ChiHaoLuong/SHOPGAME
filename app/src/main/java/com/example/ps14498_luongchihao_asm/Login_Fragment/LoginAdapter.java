package com.example.ps14498_luongchihao_asm.Login_Fragment;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class LoginAdapter extends FragmentPagerAdapter {
     Context context;
    int totalTab;
    public LoginAdapter(@NonNull FragmentManager fm, Context context, int totalTab) {
        super(fm);
        this.context = context;
        this.totalTab = totalTab;
    }

    @Override
    public int getCount() {
        return totalTab;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        Log.i("checktab", position+"");
        switch (position)
        {
            case 0:

                Log.i("checktab", "login");
                return new LoginTabFragmnet();

            case 1:
                Log.i("checktab", "signup");
                return new SignupTabFragment();

            default: return null;
        }
    }
}
