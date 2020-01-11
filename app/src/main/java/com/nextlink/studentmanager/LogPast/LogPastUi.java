package com.nextlink.studentmanager.LogPast;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;

import com.nextlink.studentmanager.R;
import com.nextlink.studentmanager.timeline_model;
import com.nextlink.studentmanager.utils.DbHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class LogPastUi extends AppCompatActivity {

    public static ArrayList<timeline_model> timeline;
    DbHelper myDb = new DbHelper(this);
    private FragmentAdapter adapter;
    private ViewPager viewPager;
    TextView b1;
    static String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_past_ui);
        b1=(TextView)findViewById(R.id.btnday);


        adapter = new FragmentAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPager);


        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR);
        int mMonth = c.get(Calendar.MONTH);
        int mDay = c.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {
                        int month=monthOfYear+1;
                        switch(month){
                            case 1:
                                b1.setText(dayOfMonth+ " - January - "+year);
                                break;
                            case 2:
                                b1.setText(dayOfMonth+ " - February - "+year);
                                break;
                            case 3:
                                b1.setText(dayOfMonth+ " - March - "+year);
                                break;
                            case 4:
                                b1.setText(dayOfMonth+ " - April - "+year);
                                break;
                            case 5:
                                b1.setText(dayOfMonth+ " - May - "+year);
                                break;
                            case 6:
                                b1.setText(dayOfMonth+ " - June - "+year);
                                break;

                            case 7:
                                b1.setText(dayOfMonth+ " - July - "+year);
                                break;
                            case 8:
                                b1.setText(dayOfMonth+ " - August - "+year);
                                break;
                            case 9:
                                b1.setText(dayOfMonth+ " - September - "+year);
                                break;
                            case 10:
                                b1.setText(dayOfMonth+ " - October - "+year);
                                break;
                            case 11:
                                b1.setText(dayOfMonth+ " - November - "+year);
                                break;
                            case 12:
                                b1.setText(dayOfMonth+ " - December - "+year);
                                break;

                        }
                        Log.i("TAG",dayOfMonth+"/"+month+"/"+year);
                        timeline=myDb.timelineByDate(dayOfMonth+"/"+month+"/"+year);
                        viewPager.setAdapter(adapter);
                        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                            @Override
                            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) { }

                            @Override
                            public void onPageSelected(int position) {
                                x = timeline.get(position).toString();
                            }

                            @Override
                            public void onPageScrollStateChanged(int state) {

                            }
                        });

                    }

                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
    }

