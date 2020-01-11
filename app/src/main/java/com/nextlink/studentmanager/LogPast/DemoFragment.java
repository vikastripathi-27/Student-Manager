package com.nextlink.studentmanager.LogPast;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nextlink.studentmanager.Attendence.Attendence_manage;
import com.nextlink.studentmanager.R;
import com.nextlink.studentmanager.utils.DbHelper;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class DemoFragment extends Fragment {

    final Calendar c = Calendar.getInstance();
    int mYear = c.get(Calendar.YEAR);
    int mMonth = c.get(Calendar.MONTH);
    int mDay = c.get(Calendar.DAY_OF_MONTH);



    public DemoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        final DbHelper myDb = new DbHelper(getContext());
        View view = inflater.inflate(R.layout.log_fragment_demo, container, false);
        TextView t1 = view.findViewById(R.id.textView2);
        final TextView t8 = view.findViewById(R.id.textView);
        final TextView t9 = view.findViewById(R.id.textView2);
        final TextView t3 = view.findViewById(R.id.textView4);
        final TextView t4 = view.findViewById(R.id.textView5);
        final TextView t5 = view.findViewById(R.id.textView6);
        final TextView t6 = view.findViewById(R.id.textView7);
        final TextView t7 = view.findViewById(R.id.textView8);
        final TextView t10 = view.findViewById(R.id.textView10);
        final TextView t11 = view.findViewById(R.id.textView11);
        final TextView t12 = view.findViewById(R.id.textView12);
        final TextView t13 = view.findViewById(R.id.textView13);
        final TextView t14 = view.findViewById(R.id.textView14);

        String message1 = getArguments().getString("message1");
        String message2 = getArguments().getString("message2");
        Log.i("TAG",message2);
        final Integer message3 = getArguments().getInt("message3");
        final String message4 = getArguments().getString("message4");
//        final Integer message5 = getArguments().getInt("message5");

        t1.setText(message1);
        Log.i("TAG","id "+message3.toString());

        if (message2.contentEquals("Present")) {
            t8.setVisibility(View.INVISIBLE);
            t6.setVisibility(View.INVISIBLE);
            t7.setVisibility(View.INVISIBLE);
            t4.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            t9.setVisibility(View.INVISIBLE);
            t10.setVisibility(View.VISIBLE);
            t11.setVisibility(View.VISIBLE);
            t12.setVisibility(View.INVISIBLE);
            t13.setVisibility(View.INVISIBLE);
            t14.setVisibility(View.VISIBLE);

        }

        if (message2.contentEquals("Absent")) {
            t8.setVisibility(View.INVISIBLE);
            t6.setVisibility(View.INVISIBLE);
            t7.setVisibility(View.INVISIBLE);
            t4.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            t9.setVisibility(View.INVISIBLE);
            t12.setVisibility(View.VISIBLE);
            t13.setVisibility(View.VISIBLE);
            t10.setVisibility(View.INVISIBLE);
            t11.setVisibility(View.INVISIBLE);
            t14.setVisibility(View.VISIBLE);
        }

        if (message2.contentEquals("Cancelled")) {
            t8.setVisibility(View.INVISIBLE);
            t6.setVisibility(View.INVISIBLE);
            t7.setVisibility(View.VISIBLE);
            t4.setVisibility(View.INVISIBLE);
            t5.setVisibility(View.INVISIBLE);
            t3.setVisibility(View.INVISIBLE);
            t9.setVisibility(View.INVISIBLE);
            t12.setVisibility(View.INVISIBLE);
            t13.setVisibility(View.INVISIBLE);
            t10.setVisibility(View.INVISIBLE);
            t11.setVisibility(View.INVISIBLE);
            t14.setVisibility(View.VISIBLE);
        }

        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                t8.setVisibility(View.INVISIBLE);
                t6.setVisibility(View.INVISIBLE);
                t7.setVisibility(View.VISIBLE);
                t4.setVisibility(View.INVISIBLE);
                t5.setVisibility(View.INVISIBLE);
                t3.setVisibility(View.INVISIBLE);
                t9.setVisibility(View.INVISIBLE);
                t12.setVisibility(View.INVISIBLE);
                t13.setVisibility(View.INVISIBLE);
                t10.setVisibility(View.INVISIBLE);
                t11.setVisibility(View.INVISIBLE);
                t14.setVisibility(View.VISIBLE);

                SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date();
                String temp = formatter.format(date);
                if (LogPastUi.x == null) {
                    LogPastUi.x = t9.getText().toString();
                }
                myDb.updateMarked('5',message3);
                myDb.cancel_timeline(message3,LogPastUi.x,message4);
                //Toast.makeText(this,"Cancelled ",Toast.LENGTH_SHORT).show();





            }
        });




        t4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t8.setVisibility(View.INVISIBLE);
                    t6.setVisibility(View.INVISIBLE);
                    t7.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t9.setVisibility(View.INVISIBLE);
                    t10.setVisibility(View.VISIBLE);
                    t11.setVisibility(View.VISIBLE);
                    t12.setVisibility(View.INVISIBLE);
                    t13.setVisibility(View.INVISIBLE);
                    t14.setVisibility(View.VISIBLE);


                    if (LogPastUi.x == null) {
                        LogPastUi.x = t9.getText().toString();
                    }
                    myDb.present_lecs(LogPastUi.x);
                    myDb.present_timeline(message3,LogPastUi.x, message4);
                    // Toast.makeText(this,"subject = "+x,Toast.LENGTH_SHORT).show();
                    //Toast.makeText(this,"Present marked = "+,Toast.LENGTH_SHORT).show();

                    myDb.updateMarked('1',message3);
                    int result=myDb.getMarked(message3);
                    Log.i("TAG","On present Marked "+result+ "id "+message3);

                }
            });

            t5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    t8.setVisibility(View.INVISIBLE);
                    t6.setVisibility(View.INVISIBLE);
                    t7.setVisibility(View.INVISIBLE);
                    t4.setVisibility(View.INVISIBLE);
                    t5.setVisibility(View.INVISIBLE);
                    t3.setVisibility(View.INVISIBLE);
                    t9.setVisibility(View.INVISIBLE);
                    t12.setVisibility(View.VISIBLE);
                    t13.setVisibility(View.VISIBLE);
                    t10.setVisibility(View.INVISIBLE);
                    t11.setVisibility(View.INVISIBLE);
                    t14.setVisibility(View.VISIBLE);

                    if (LogPastUi.x == null) {
                        LogPastUi.x = t9.getText().toString();
                    }
                    myDb.absent_lecs(LogPastUi.x);
                    myDb.absent_timeline(message3,LogPastUi.x,message4);
                    //Toast.makeText(this,"Absent marked ",Toast.LENGTH_SHORT).show();

                    myDb.updateMarked('2',message3);
                    int result=myDb.getMarked(message3);
                    Log.i("TAG","On absent Marked" + result+ "id "+message3);
                }
            });

            t14.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (t10.getVisibility() == View.VISIBLE){

                        t8.setVisibility(View.VISIBLE);
                        t6.setVisibility(View.VISIBLE);
                        t7.setVisibility(View.INVISIBLE);
                        t4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        t9.setVisibility(View.VISIBLE);
                        t12.setVisibility(View.INVISIBLE);
                        t13.setVisibility(View.INVISIBLE);
                        t10.setVisibility(View.INVISIBLE);
                        t11.setVisibility(View.INVISIBLE);
                        t14.setVisibility(View.INVISIBLE);
                        myDb.updateMarked('3',message3);
                        int result=myDb.getMarked(message3);
                        Log.i("TAG","on present undo "+result+"id "+message3);
                        if (LogPastUi.x == null) {
                            LogPastUi.x = t9.getText().toString();
                        }
                        myDb.delete_present_lecs(LogPastUi.x);
                        myDb.delete_timeline(message3);

                    }

                    else if(t12.getVisibility()==View.VISIBLE){

                        t8.setVisibility(View.VISIBLE);
                        t6.setVisibility(View.VISIBLE);
                        t7.setVisibility(View.INVISIBLE);
                        t4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        t9.setVisibility(View.VISIBLE);
                        t12.setVisibility(View.INVISIBLE);
                        t13.setVisibility(View.INVISIBLE);
                        t10.setVisibility(View.INVISIBLE);
                        t11.setVisibility(View.INVISIBLE);
                        t14.setVisibility(View.INVISIBLE);
                       // home.myList.get(message4).setColor(1);
                        //Log.i("TAG",String.valueOf(home.myList.get(message4).getColor()));
                        myDb.updateMarked('4',message3);
                        int result=myDb.getMarked(message3);
                        Log.i("TAG","On absent undo "+result+"id "+message3);
                        if (LogPastUi.x == null) {
                            LogPastUi.x = t9.getText().toString();
                        }
                        myDb.delete_absent_lecs(LogPastUi.x);
                        myDb.delete_timeline(message3);

                    }

                    else if(t7.getVisibility()==View.VISIBLE){

                        t8.setVisibility(View.VISIBLE);
                        t6.setVisibility(View.VISIBLE);
                        t7.setVisibility(View.INVISIBLE);
                        t4.setVisibility(View.VISIBLE);
                        t5.setVisibility(View.VISIBLE);
                        t3.setVisibility(View.VISIBLE);
                        t9.setVisibility(View.VISIBLE);
                        t12.setVisibility(View.INVISIBLE);
                        t13.setVisibility(View.INVISIBLE);
                        t10.setVisibility(View.INVISIBLE);
                        t11.setVisibility(View.INVISIBLE);
                        t14.setVisibility(View.INVISIBLE);
                        if (LogPastUi.x == null) {
                            LogPastUi.x = t9.getText().toString();
                        }
                        myDb.updateMarked('6',message3);
                        myDb.delete_cancelled_lecs(LogPastUi.x);
                        myDb.delete_timeline(message3);


                    }


                }
            });



            return view;
        }


}
