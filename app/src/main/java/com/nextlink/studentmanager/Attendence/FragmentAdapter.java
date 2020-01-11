package com.nextlink.studentmanager.Attendence;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import com.nextlink.studentmanager.home;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {




    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        DemoFragment demoFragment=new DemoFragment();
        Bundle bundle=new Bundle();


        bundle.putString("message1",home.myList.get(i).toString());
        bundle.putString("message2",home.myList.get(i).getFromTime());
        bundle.putString("message3",home.myList.get(i).getToTime());
        bundle.putInt("message4",home.myList.get(i).getId());
        bundle.putInt("message5",home.myList.get(i++).getColor());

        demoFragment.setArguments(bundle);
        return demoFragment;
    }

    @Override
    public int getCount() {
        return home.myList.size();
    }
}


