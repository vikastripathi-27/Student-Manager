package com.nextlink.studentmanager.Attendence;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nextlink.studentmanager.LogPast.LogPastUi;
import com.nextlink.studentmanager.R;
import com.nextlink.studentmanager.Timeline_details;
import com.nextlink.studentmanager.attendence_details;
import com.nextlink.studentmanager.attmodel;
import com.nextlink.studentmanager.home;
import com.nextlink.studentmanager.timeline_model;
import com.nextlink.studentmanager.utils.DbHelper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Attendence_manage extends AppCompatActivity {


        private ViewPager viewPager;
        private FragmentAdapter adapter;
        Integer[] colors = null;
        ArgbEvaluator argbEvaluator = new ArgbEvaluator();
        TextView b1,b2,sub_name_text,t1,t2,t3,t4,t5,t6,t7;
        static String subject_name,x;
        DbHelper myDb = new DbHelper(this);
        public ArrayList<String> List;
    public static  ArrayList<timeline_model> subname;

    public static  ArrayList<attmodel> sub_name;
    DbHelper db;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_attendence_manage);
            b1=(TextView)findViewById(R.id.btnday);
            b2=(TextView)findViewById(R.id.btntime);
            b1.setText(home.time);
            adapter = new FragmentAdapter(getSupportFragmentManager());
            viewPager = (ViewPager) findViewById(R.id.viewPager);
            viewPager.setAdapter(adapter);
            viewPager.setPadding(130, 0, 130, 0);

            Intent intent = getIntent();
            Bundle args = intent.getBundleExtra("BUNDLE");
             List = (ArrayList<String>) args.getSerializable("ARRAYLIST");

            Integer[] colors_temp = {
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary),
                    getResources().getColor(R.color.colorPrimary)


            };

            colors = colors_temp;

            viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    if (position < (adapter.getCount() -1) && position < (colors.length - 1)) {
                        viewPager.setBackgroundColor(

                                (Integer) argbEvaluator.evaluate(
                                        positionOffset,
                                        colors[position],
                                        colors[position + 1]
                                )
                        );
                    }

                    else {
                        viewPager.setBackgroundColor(colors[colors.length - 1]);
                    }
                }

                @Override
                public void onPageSelected(int position) {
                    x = home.myList.get(position).toString();
                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_attendance, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.total:
                db = new DbHelper(getBaseContext());
                sub_name =db.sub_list();
                //x=subname.get(0).toString();
                Intent intent = new Intent(this, attendence_details.class);
                startActivity(intent);
                break;

            case R.id.timeline:
                db = new DbHelper(getBaseContext());

                subname =db.timeline();
                Intent intent2 = new Intent(this, Timeline_details.class);
                startActivity(intent2);
                break;

//            case R.id.log_past:
//                Intent intent3 = new Intent(this, LogPastUi.class);
//                startActivity(intent3);
//                finish();
//                break;

        }
        return super.onOptionsItemSelected(item);
    }

//    public void present(View view) {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            Date date = new Date();
//            String temp = formatter.format(date);
//            if(x == null) {
//                sub_name_text = findViewById(R.id.textView2);
//                subject_name = sub_name_text.getText().toString();
//                x = subject_name;
//            }
//            myDb.present_lecs(x);
//            myDb.present_timeline(x,temp);
//           // Toast.makeText(this,"subject = "+x,Toast.LENGTH_SHORT).show();
//            Toast.makeText(this,"Present marked = "+x,Toast.LENGTH_SHORT).show();
//        }
//
//        public void absent(View view) {
//            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
//            Date date = new Date();
//            String temp = formatter.format(date);
//            if(x == null) {
//                sub_name_text = findViewById(R.id.textView2);
//                subject_name = sub_name_text.getText().toString();
//                x = subject_name;
//            }
//            myDb.absent_lecs(x);
//            myDb.absent_timeline(x,temp);
//            Toast.makeText(this,"Absent marked ",Toast.LENGTH_SHORT).show();
//        }
//
//        public void view_attendance(View view) {
//            Cursor res = myDb.get_data(x);
//            if(res.getCount() == 0) {
//                Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
//                return;
//            }
//            StringBuffer buffer = new StringBuffer();
//            while (res.moveToNext()) {
//                buffer.append("Id : "+res.getString(0)+"\n");
//                buffer.append("Subject : "+res.getString(1)+"\n");
//                buffer.append("Present : "+res.getString(3)+"\n");
//                buffer.append("Total : "+res.getString(4)+"\n\n");
//                //Toast.makeText(this,"val = "+res.getString(1),Toast.LENGTH_SHORT).show();
//            }
//            display("Attendance",buffer.toString());
//
//            //Toast.makeText(this, " subject = "+subject_name,Toast.LENGTH_SHORT).show();
//        }

        public void cancel(View view) {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Date date = new Date();
            String temp = formatter.format(date);
            if(x == null) {
                sub_name_text = findViewById(R.id.textView2);
                subject_name = sub_name_text.getText().toString();
                x = subject_name;
            }
            myDb.cancel_timeline(x,temp);
            Toast.makeText(this,"Cancelled ",Toast.LENGTH_SHORT).show();
        }
//
//        public void display(String title,String data) {
//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setCancelable(true);
//            builder.setTitle(title);
//            builder.setMessage(data);
//            builder.show();
//        }


        //public void timeline(View view) {
//           Cursor res = myDb.get_timeline();
//            if(res.getCount() == 0) {
//                Toast.makeText(this,"No history",Toast.LENGTH_SHORT).show();
//                return;
//            }
//            StringBuffer buffer2 = new StringBuffer();
//            while (res.moveToNext()) {
//                buffer2.append("Id : "+res.getString(0)+"\n");
//                buffer2.append("Subject : "+res.getString(1)+"\n");
//                buffer2.append("Timestamp : "+res.getString(2)+"\n");
//                buffer2.append("Action : "+res.getString(3)+"\n\n");
//            }
//            history("Timeline",buffer2.toString());

//            DbHelper db;
//            db = new DbHelper(getBaseContext());
//
//            subname =db.timeline();
//            Intent intent = new Intent(this, Timeline_details.class);
//            startActivity(intent);
//
//        }
//
//        public void history(String title,String data) {
//            AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
//            builder2.setCancelable(true);
//            builder2.setTitle(title);
//            builder2.setMessage(data);
//            builder2.show();
//        }
    }