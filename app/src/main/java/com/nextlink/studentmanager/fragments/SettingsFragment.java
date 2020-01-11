package com.nextlink.studentmanager.fragments;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;


import com.nextlink.studentmanager.R;


public class SettingsFragment extends PreferenceFragmentCompat {

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}
