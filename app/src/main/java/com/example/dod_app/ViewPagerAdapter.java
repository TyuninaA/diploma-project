package com.example.dod_app;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new Map();
            case 1: return new Info();
            case 2: return new Home();
            case 3: return new Shedule();
            case 4: return new Robot();
            default: return new Home();
        }
    }

    @Override
    public int getItemCount() {
        return 5;
    }

}