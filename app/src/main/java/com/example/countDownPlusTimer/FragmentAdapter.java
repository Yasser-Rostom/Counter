package com.example.countDownPlusTimer;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;


public class FragmentAdapter extends  FragmentStateAdapter{


    public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 1)
        {
            return new StopWatch();
        }
        return new Counter();

    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
