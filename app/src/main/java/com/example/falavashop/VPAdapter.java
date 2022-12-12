package com.example.falavashop;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class VPAdapter extends FragmentStateAdapter {
    public VPAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch(position){
            case 0:
                return new BlankFragment();
            case 1:
                return new BlankFragment2();
            case 2:
                return new BlankFragment3();
            default:
                return new BlankFragment();
        }

    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
