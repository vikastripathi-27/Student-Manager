package com.nextlink.studentmanager.LogPast;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class FragmentAdapter extends FragmentStatePagerAdapter {


    public FragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        DemoFragment demoFragment = new DemoFragment();
        Bundle bundle = new Bundle();


            bundle.putString("message1", LogPastUi.timeline.get(i).getSub_name());
            bundle.putString("message2",LogPastUi.timeline.get(i).getAction() );
        bundle.putInt("message3",LogPastUi.timeline.get(i).getId() );
        bundle.putString("message4",LogPastUi.timeline.get(i).getTimestamp() );


        demoFragment.setArguments(bundle);
            return demoFragment;


    }

    @Override
    public int getCount() {

        return LogPastUi.timeline.size();

    }
}



