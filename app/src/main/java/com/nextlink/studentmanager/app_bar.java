package com.nextlink.studentmanager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.nextlink.studentmanager.adapters.FragmentsTabAdapter;
import com.nextlink.studentmanager.fragments.FridayFragment;
import com.nextlink.studentmanager.fragments.MondayFragment;
import com.nextlink.studentmanager.fragments.SaturdayFragment;
import com.nextlink.studentmanager.fragments.SundayFragment;
import com.nextlink.studentmanager.fragments.ThursdayFragment;
import com.nextlink.studentmanager.fragments.TuesdayFragment;
import com.nextlink.studentmanager.fragments.WednesdayFragment;
import com.nextlink.studentmanager.utils.AlertDialogsHelper;
import com.nextlink.studentmanager.utils.AlertDialogsHelper;
//import com.nextlink.studentmanager.utils.DailyReceiver;

import java.util.Calendar;

public class app_bar extends AppCompatActivity {

    private FragmentsTabAdapter adapter;
    private ViewPager viewPager;
  //  private boolean switchSevenDays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_bar);
        initAll();
    }
    private void initAll() {
      //  PreferenceManager.setDefaultValues(this, R.xml.settings, false);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


       setupFragments();
        setupCustomDialog();
       // setupSevenDaysPref();

      //  if(switchSevenDays==true) changeFragments(true);

        //setDailyAlarm();
    }

    private void setupFragments() {
        adapter = new FragmentsTabAdapter(getSupportFragmentManager());
        viewPager = findViewById(R.id.viewPager);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        adapter.addFragment(new MondayFragment(), getResources().getString(R.string.monday));
        adapter.addFragment(new TuesdayFragment(), getResources().getString(R.string.tuesday));
        adapter.addFragment(new WednesdayFragment(), getResources().getString(R.string.wednesday));
        adapter.addFragment(new ThursdayFragment(), getResources().getString(R.string.thursday));
        adapter.addFragment(new FridayFragment(), "Friday");
        adapter.addFragment(new SaturdayFragment(), "Saturday");
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(day == 1 ? 6 : day-2, true);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void changeFragments(boolean isChecked) {
        if(isChecked) {
            TabLayout tabLayout = findViewById(R.id.tabLayout);
            Calendar calendar = Calendar.getInstance();
            int day = calendar.get(Calendar.DAY_OF_WEEK);
            adapter.addFragment(new SaturdayFragment(), getResources().getString(R.string.saturday));
            adapter.addFragment(new SundayFragment(), getResources().getString(R.string.sunday));
            viewPager.setAdapter(adapter);
            viewPager.setCurrentItem(day == 1 ? 6 : day-2, true);
            tabLayout.setupWithViewPager(viewPager);
        } else {
            if(adapter.getFragmentList().size() > 5) {
                adapter.removeFragment(new SaturdayFragment(), 5);
                adapter.removeFragment(new SundayFragment(), 5);
            }
        }
        adapter.notifyDataSetChanged();
    }

    private void setupCustomDialog() {
    final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subject, null);
        AlertDialogsHelper.getAddSubjectDialog(this, alertLayout, adapter, viewPager);
    }

//    private void setupSevenDaysPref() {
//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
//        switchSevenDays = sharedPref.getBoolean(SettingsActivity.KEY_SEVEN_DAYS_SETTING, false);
//    }



//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.action_settings:
//                Intent settings = new Intent(getApplicationContext(), SettingsActivity.class);
//                startActivity(settings);
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }

}
