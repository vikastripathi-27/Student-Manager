package com.nextlink.studentmanager.utils;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.nextlink.studentmanager.R;
import com.nextlink.studentmanager.adapters.FragmentsTabAdapter;
import com.nextlink.studentmanager.adapters.WeekAdapter;
import com.nextlink.studentmanager.model.Add_Subject;
import com.nextlink.studentmanager.model.Week;

//import org.xdty.preference.colorpicker.ColorPickerDialog;
//import org.xdty.preference.colorpicker.ColorPickerSwatch;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



public class AlertDialogsHelper {

    public static void getEditSubjectDialog(final Activity activity, final View alertLayout, final ArrayList<Week> adapter, final ListView listView, int position) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject = alertLayout.findViewById(R.id.subject_dialog);
        editTextHashs.put(R.string.subject, subject);
        final EditText teacher = alertLayout.findViewById(R.id.teacher_dialog);
        editTextHashs.put(R.string.teacher, teacher);
        final EditText room = alertLayout.findViewById(R.id.room_dialog);
        editTextHashs.put(R.string.room, room);
        final TextView from_time = alertLayout.findViewById(R.id.from_time);
        final TextView to_time = alertLayout.findViewById(R.id.to_time);
        final Button select_color = alertLayout.findViewById(R.id.select_color);
        final Week week = adapter.get(position);

        subject.setText(week.getSubject());
        teacher.setText(week.getTeacher());
        room.setText(week.getRoom());
        from_time.setText(week.getFromTime());
        to_time.setText(week.getToTime());
        select_color.setBackgroundColor(week.getColor() != 0 ? week.getColor() : Color.WHITE);

        from_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                from_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                week.setFromTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show();
            }
        });

        to_time.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                to_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                week.setToTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, hour, minute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show();
            }
        });

//        select_color.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int mSelectedColor = ContextCompat.getColor(activity, R.color.white);
//                select_color.setBackgroundColor(mSelectedColor);
//                int[] mColors = activity.getResources().getIntArray(R.array.default_colors);
//                ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
//                        mColors,
//                        mSelectedColor,
//                        5,
//                        ColorPickerDialog.SIZE_SMALL,
//                        true
//                );
//
//                dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
//                    @Override
//                    public void onColorSelected(int color) {
//                        select_color.setBackgroundColor(color);
//                    }
//                });
//                dialog.show(activity.getFragmentManager(), "color_dialog");
//            }
//        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
//        alert.setTitle(R.string.edit_subject);
        alert.setCancelable(false);
        final Button cancel = alertLayout.findViewById(R.id.cancel);
        final Button save = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(subject.getText()) || TextUtils.isEmpty(teacher.getText()) || TextUtils.isEmpty(room.getText())) {
                    for (Map.Entry<Integer, EditText> entry : editTextHashs.entrySet()) {
                        if(TextUtils.isEmpty(entry.getValue().getText())) {
                            entry.getValue().setError(activity.getResources().getString(entry.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            entry.getValue().requestFocus();
                        }
                    }
                } else if(!from_time.getText().toString().matches(".*\\d+.*") || !to_time.getText().toString().matches(".*\\d+.*")) {
                    Snackbar.make(alertLayout, R.string.time_error, Snackbar.LENGTH_LONG).show();
                } else {
                    DbHelper db = new DbHelper(activity);
                    WeekAdapter weekAdapter = (WeekAdapter) listView.getAdapter(); // In order to get notifyDataSetChanged() method.
                    ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    week.setSubject(subject.getText().toString());
                    week.setTeacher(teacher.getText().toString());
                    week.setRoom(room.getText().toString());
                    week.setColor(buttonColor.getColor());
                    db.updateWeek(week);
                    weekAdapter.notifyDataSetChanged();
                    dialog.dismiss();
                }
            }
        });
    }

    public static void getAddSubjectDialog(final Activity activity, final View alertLayout, final FragmentsTabAdapter adapter, final ViewPager viewPager) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject = alertLayout.findViewById(R.id.subject_dialog);
        editTextHashs.put(R.string.subject, subject);
        final EditText teacher = alertLayout.findViewById(R.id.teacher_dialog);
        editTextHashs.put(R.string.teacher, teacher);
        final EditText room = alertLayout.findViewById(R.id.room_dialog);
        editTextHashs.put(R.string.room, room);
        final TextView from_time = alertLayout.findViewById(R.id.from_time);
        final TextView to_time = alertLayout.findViewById(R.id.to_time);
        final Button select_color = alertLayout.findViewById(R.id.select_color);
        final Week week = new Week();

        from_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                from_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                week.setFromTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show(); }});

        to_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                int hour = c.get(Calendar.HOUR_OF_DAY);
                int minute = c.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog = new TimePickerDialog(activity,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                to_time.setText(String.format("%02d:%02d", hourOfDay, minute));
                                week.setToTime(String.format("%02d:%02d", hourOfDay, minute));
                            }
                        }, hour, minute, true);
                timePickerDialog.setTitle(R.string.choose_time);
                timePickerDialog.show();
            }
        });

//        select_color.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int mSelectedColor = ContextCompat.getColor(activity, R.color.white);
//                select_color.setBackgroundColor(mSelectedColor);
//                int[] mColors = activity.getResources().getIntArray(R.array.default_colors);
//                ColorPickerDialog dialog = ColorPickerDialog.newInstance(R.string.color_picker_default_title,
//                        mColors,
//                        mSelectedColor,
//                        5,
//                        ColorPickerDialog.SIZE_SMALL,
//                        true
//                );
//
//                dialog.setOnColorSelectedListener(new ColorPickerSwatch.OnColorSelectedListener() {
//                    @Override
//                    public void onColorSelected(int color) {
//                        select_color.setBackgroundColor(color);
//                    }
//                });
//                dialog.show(activity.getFragmentManager(), "color_dialog");
//            }
//        });

        final AlertDialog.Builder alert = new AlertDialog.Builder(activity);
//        alert.setTitle(R.string.add_subject);
        alert.setCancelable(false);
        Button cancel = alertLayout.findViewById(R.id.cancel);
        Button submit = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        FloatingActionButton fab = activity.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(subject.getText()) || TextUtils.isEmpty(teacher.getText()) || TextUtils.isEmpty(room.getText())) {
                    for (Map.Entry<Integer, EditText> entry : editTextHashs.entrySet()) {
                        if(TextUtils.isEmpty(entry.getValue().getText())) {
                            entry.getValue().setError(activity.getResources().getString(entry.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            entry.getValue().requestFocus();
                        }
                    }
                } else if(!from_time.getText().toString().matches(".*\\d+.*") || !to_time.getText().toString().matches(".*\\d+.*")) {
                    Snackbar.make(alertLayout, R.string.time_error, Snackbar.LENGTH_LONG).show();
                } else {
                    DbHelper dbHelper = new DbHelper(activity);
                    Matcher fragment = Pattern.compile("(.*Fragment)").matcher(adapter.getItem(viewPager.getCurrentItem()).toString());
                   // ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    week.setSubject(subject.getText().toString());
                    week.setFragment(fragment.find() ? fragment.group() : null);
                    week.setTeacher(teacher.getText().toString());
                    week.setRoom(room.getText().toString());
                    week.setMarked(0);
                   // week.setColor(buttonColor.getColor());
                    dbHelper.insertWeek(week);
                    adapter.notifyDataSetChanged();
                    subject.getText().clear();
                    teacher.getText().clear();
                    room.getText().clear();
                    from_time.setText(R.string.select_time);
                    to_time.setText(R.string.select_time);
                    //select_color.setBackgroundColor(Color.WHITE);
                    subject.requestFocus();
                    dialog.dismiss();
                }
            }
        });
    }


    public static void getEditSubject1Dialog(final Activity activity, final View alertLayout, final ArrayList<Add_Subject> adapter, final ListView listView, int listposition) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject2 = alertLayout.findViewById(R.id.sub_name);
        editTextHashs.put(R.string.subject, subject2);
        final EditText description = alertLayout.findViewById(R.id.desctname);
        editTextHashs.put(R.string.tname, description);
      //  final Button select_color = alertLayout.findViewById(R.id.select_color);
        final Add_Subject subject1 = adapter.get(listposition);

        subject2.setText(subject1.getSubject());
        description.setText(subject1.getDescription());

        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
//        alert.setTitle(R.string.edit_subject);
        alert.setCancelable(false);
        final Button cancel = alertLayout.findViewById(R.id.cancel);
        final Button save = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        final AlertDialog dialog = alert.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(subject2.getText()) || TextUtils.isEmpty(description.getText())) {
                    for (Map.Entry<Integer, EditText> editText : editTextHashs.entrySet()) {
                        if (TextUtils.isEmpty(editText.getValue().getText())) {
                            editText.getValue().setError(activity.getResources().getString(editText.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            editText.getValue().requestFocus();
                        }
                    }
                }
//                else if (!date.getText().toString().matches(".*\\d+.*")) {
//                    Snackbar.make(alertLayout, R.string.deadline_snackbar, Snackbar.LENGTH_LONG).show();
//                }
                else {
                    DbHelper dbHelper = new DbHelper(activity);
                    com.nextlink.studentmanager.adapters.Add_Subject addSubject = (com.nextlink.studentmanager.adapters.Add_Subject) listView.getAdapter();
                   // ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    subject1.setSubject(subject2.getText().toString());
                    subject1.setDescription(description.getText().toString());
                   // subject.setColor(buttonColor.getColor());
                    dbHelper.updateSubject(subject1);
                   // dbHelper.update_lec(subject2.getText().toString());
                    addSubject.notifyDataSetChanged();
                  //
                    dialog.dismiss();
                }
            }
        });
    }

    public static void getAddSubjectDialog(final Activity activity, final View alertLayout, final com.nextlink.studentmanager.adapters.Add_Subject adapter) {
        final HashMap<Integer, EditText> editTextHashs = new HashMap<>();
        final EditText subject2 = alertLayout.findViewById(R.id.sub_name);
        editTextHashs.put(R.string.subject, subject2);
        final EditText description = alertLayout.findViewById(R.id.desctname);
        editTextHashs.put(R.string.tname, description);
        final Button select_color = alertLayout.findViewById(R.id.select_color);
        final Add_Subject subject1 = new Add_Subject();
        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
//        alert.setTitle(R.string.add_subject);
        final Button cancel = alertLayout.findViewById(R.id.cancel);
        final Button save = alertLayout.findViewById(R.id.save);
        alert.setView(alertLayout);
        alert.setCancelable(false);
        final AlertDialog dialog = alert.create();
        FloatingActionButton fab = activity.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(subject2.getText()) || TextUtils.isEmpty(description.getText())) {
                    for (Map.Entry<Integer, EditText> editText : editTextHashs.entrySet()) {
                        if(TextUtils.isEmpty(editText.getValue().getText())) {
                            editText.getValue().setError(activity.getResources().getString(editText.getKey()) + " " + activity.getResources().getString(R.string.field_error));
                            editText.getValue().requestFocus();
                        }
                    }
                }
//                else if(!date.getText().toString().matches(".*\\d+.*")) {
//                    Snackbar.make(alertLayout, R.string.deadline_snackbar, Snackbar.LENGTH_LONG).show();
//                }
                else {
                    DbHelper dbHelper = new DbHelper(activity);
                   // ColorDrawable buttonColor = (ColorDrawable) select_color.getBackground();
                    subject1.setSubject(subject2.getText().toString());
                    subject1.setDescription(description.getText().toString());
                   // subject2.setColor(buttonColor.getColor());
                    dbHelper.insertSubject(subject1);
                    //dbHelper.add_lectures(subject2.getText().toString());

                    adapter.clear();
                    adapter.addAll(dbHelper.getSubject());
                    adapter.notifyDataSetChanged();

                   // subject.getText().clear();
                    description.getText().clear();
                   // date.setText(R.string.select_date);
                  //  select_color.setBackgroundColor(Color.BLUE);
                  //  subject.requestFocus();
                    dialog.dismiss();
                }
            }
        });
    }



}
