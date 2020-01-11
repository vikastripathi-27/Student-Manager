package com.nextlink.studentmanager.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;


import com.nextlink.studentmanager.TaskContract;
import com.nextlink.studentmanager.attmodel;
import com.nextlink.studentmanager.model.Add_Subject;
import com.nextlink.studentmanager.model.Week;
import com.nextlink.studentmanager.timeline_model;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class DbHelper extends SQLiteOpenHelper {


    private static final int DB_VERSION = 6;

    int result;

    private static final String DB_NAME = "timetabledb";
    private static final String TIMETABLE = "timetable";
    private static final String WEEK_ID = "id";
    private static final String WEEK_SUBJECT = "subject";
    private static final String WEEK_FRAGMENT = "fragment";
    private static final String WEEK_TEACHER = "teacher";
    private static final String WEEK_ROOM = "room";
    private static final String WEEK_FROM_TIME = "fromtime";
    private static final String WEEK_TO_TIME = "totime";
    private static final String WEEK_COLOR = "color";
    private static final String WEEK_MARKED = "marked";


//    private static final String Add_Subject = "Subject";
//    private static final String Add_Subject_ID  = "id";
//    private static final String Add_Subject_SUBJECT = "subject_name";
//    private static final String Add_Subject_Tname = "tname";

    private static final String TABLE_1 = "Subject";
    private static final String COLUMN_11  = "id";
    private static final String COLUMN_12 = "subject_name";
    private static final String COLUMN_13 = "tname";
    private static final String COLUMN_14 = "present";
    private static final String COLUMN_15 = "total";

//
//    private static final String Table_name = "Attendance";
//    private static final String id_column = "id";
//    private static final String sub_name_column = "subject";
//    private static final String present_column = "present";
//    private static final String total_column = "total";

      private static final String Table_2 = "History";
      private static final String COL_21 = "id_history";
      private static final String COL_22 = "sub_history";
      private static final String COL_23 = "timne_history";
      private static final String COL_24 = "action_history";


    public DbHelper(Context context){
        super(context , DB_NAME, null, DB_VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        String CREATE_TIMETABLE = "CREATE TABLE " + TIMETABLE + "("
                + WEEK_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + WEEK_SUBJECT + " TEXT,"
                + WEEK_FRAGMENT + " TEXT,"
                + WEEK_TEACHER + " TEXT,"
                + WEEK_ROOM + " TEXT,"
                + WEEK_FROM_TIME + " TEXT,"
                + WEEK_TO_TIME + " TEXT,"
                + WEEK_COLOR + " INTEGER,"
                + WEEK_MARKED + "INTEGER NOT NULL DEFAULT(0)"+  ")";


//         String CREATE_Add_Subject = "CREATE TABLE " + Add_Subject + "("
//                 + Add_Subject_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                 + Add_Subject_SUBJECT + " TEXT,"
//                 + Add_Subject_Tname + " TEXT" + ")";
//
//         String CREATE_Add_Attendance = "CREATE TABLE " + Table_name + "("
//                 + id_column + " INTEGER PRIMARY KEY AUTOINCREMENT,"
//                 + sub_name_column + " TEXT,"
//                 + present_column + " INTEGER,"
//                 + total_column + " INTEGER" + ")";

        String CREATE_Add_Subject = "CREATE TABLE " + TABLE_1 + "("
                + COLUMN_11 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_12 + " TEXT,"
                + COLUMN_13 + " TEXT,"
                + COLUMN_14 + " INTEGER,"
                + COLUMN_15 + " INTEGER" + ")";

        String Timeline = "CREATE TABLE " + Table_2 + "("
                + COL_21 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_22 + " TEXT,"
                + COL_23 + " TEXT,"
                + COL_24 + " TEXT" + ")";

        String createTable = "CREATE TABLE " + TaskContract.TaskEntry.TABLE + " ( " +
                TaskContract.TaskEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                TaskContract.TaskEntry.COL_TASK_TITLE + " TEXT NOT NULL);";


         db.execSQL(CREATE_TIMETABLE);
         db.execSQL(CREATE_Add_Subject);
//         db.execSQL(CREATE_Add_Attendance);
         db.execSQL(Timeline);
        db.execSQL(createTable);

     }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        switch (oldVersion) {
            case 1:
                db.execSQL("DROP TABLE IF EXISTS " + TIMETABLE);
            case 2:
                db.execSQL("DROP TABLE IF EXISTS " + TABLE_1);
            case 3:
                db.execSQL("DROP TABLE IF EXISTS " + Table_2);
            case 4:
                db.execSQL("DROP TABLE IF EXISTS "+ TaskContract.TaskEntry.TABLE);
                break;

        }
        onCreate(db);
    }

    /**
     * Methods for Week fragments
     **/
    public void insertWeek(Week week){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_SUBJECT, week.getSubject());
        contentValues.put(WEEK_FRAGMENT, week.getFragment());
        contentValues.put(WEEK_TEACHER, week.getTeacher());
        contentValues.put(WEEK_ROOM, week.getRoom());
        contentValues.put(WEEK_FROM_TIME, week.getFromTime());
        contentValues.put(WEEK_TO_TIME, week.getToTime());
       // contentValues.put(WEEK_COLOR, week.getColor());
        db.insert(TIMETABLE,null, contentValues);
        db.update(TIMETABLE, contentValues, WEEK_FRAGMENT, null);
        db.close();
    }

    public void deleteWeekById(Week week) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TIMETABLE, WEEK_ID + " = ? ", new String[]{String.valueOf(week.getId())});
        db.close();
    }

    public void updateWeek(Week week) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_SUBJECT, week.getSubject());
        contentValues.put(WEEK_TEACHER, week.getTeacher());
        contentValues.put(WEEK_ROOM, week.getRoom());
        contentValues.put(WEEK_FROM_TIME,week.getFromTime());
        contentValues.put(WEEK_TO_TIME, week.getToTime());
        contentValues.put(WEEK_COLOR, week.getColor());
       // contentValues.put(WEEK_MARKED, week.getMarked());
        db.update(TIMETABLE, contentValues, WEEK_ID + " = " + week.getId(), null);
        db.close();
    }

    public void updateMarked(int marked,int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WEEK_COLOR,marked);
        db.update(TIMETABLE, contentValues, WEEK_ID+ " = " + id, null);
        db.close();
    }

    public int getMarked(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT "+ WEEK_COLOR +" FROM "+ TIMETABLE +" WHERE "+ WEEK_ID +" = "+ id  ,null);
        while (cursor.moveToNext()){
            result = cursor.getInt(cursor.getColumnIndex(WEEK_COLOR));
        }
        cursor.close();
        db.close();
        return  result;
    }


    public   ArrayList<Week> getWeek(String fragment){
        SQLiteDatabase db = this.getWritableDatabase();

        ArrayList<Week> weeklist = new ArrayList<>();
        Week week;
        Cursor cursor = db.rawQuery("SELECT * FROM ( SELECT * FROM "+TIMETABLE+" ORDER BY " + WEEK_FROM_TIME + " ) WHERE "+ WEEK_FRAGMENT +" LIKE '"+fragment+"%'",null);
        while (cursor.moveToNext()){
            week = new Week();
            week.setId(cursor.getInt(cursor.getColumnIndex(WEEK_ID)));
            week.setSubject(cursor.getString(cursor.getColumnIndex(WEEK_SUBJECT)));
            week.setTeacher(cursor.getString(cursor.getColumnIndex(WEEK_TEACHER)));
            week.setRoom(cursor.getString(cursor.getColumnIndex(WEEK_ROOM)));
            week.setFromTime(cursor.getString(cursor.getColumnIndex(WEEK_FROM_TIME)));
            week.setToTime(cursor.getString(cursor.getColumnIndex(WEEK_TO_TIME)));
            week.setColor(cursor.getInt(cursor.getColumnIndex(WEEK_COLOR)));
//            week.setMarked(cursor.getInt(cursor.getColumnIndex(WEEK_MARKED)));
            weeklist.add(week);
        }
        return  weeklist;
    }


    public void insertSubject(Add_Subject subject) {
        int present_lecs = 0;
        int total_lecs = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_12, subject.getSubject());
        contentValues.put(COLUMN_13, subject.getDescription());
        contentValues.put(COLUMN_14, present_lecs);
        contentValues.put(COLUMN_15, total_lecs);
        db.insert(TABLE_1,null, contentValues);
        db.close();
    }

    public void updateSubject(Add_Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_12, subject.getSubject());
        contentValues.put(COLUMN_13, subject.getDescription());
        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + subject.getId(), null);
        db.close();
    }



    public void deleteSubjectById(Add_Subject subject) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_1,COLUMN_11 + " = ? ", new String[]{String.valueOf(subject.getId())});
        db.close();
    }


    public ArrayList<Add_Subject> getSubject() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Add_Subject> sublist = new ArrayList<>();
        Add_Subject subject;
        Cursor cursor = db.rawQuery("SELECT * FROM "+ TABLE_1 ,null);
        while (cursor.moveToNext()){
            subject = new Add_Subject();
            subject.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_11)));
            subject.setSubject(cursor.getString(cursor.getColumnIndex(COLUMN_12)));
            subject.setDescription(cursor.getString(cursor.getColumnIndex(COLUMN_13)));
            sublist.add(subject);
        }
        cursor.close();
        db.close();
        return  sublist;
    }

    public int subject_count() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE_1,null);
        if(cursor.getCount() == 0) {
            return 0;
        }
        else {
            return 1;
        }
    }

//    public void add_lectures (String sub_name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        int present_lecs = 0;
//        int total_lecs = 0;
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(sub_name_column, sub_name);
//        contentValues.put(present_column, present_lecs);
//        contentValues.put(total_column, total_lecs);
//        db.insert(Table_name,null,contentValues);
//        db.close();
//    }

    public Cursor get_data(String sub_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_1,null);
        return res;
    }

    public void present_lecs(String subject_name) {
        String id_value = null;
        String present_value = null;
        String total_value = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = value(subject_name);
        if(res.getCount() == 0) {
            return ;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            id_value = res.getString(0);
            present_value = res.getString(3);
            total_value = res.getString(4);
        }

        int pres = Integer.parseInt(present_value);
        int total = Integer.parseInt(total_value);
        int id = Integer.parseInt(id_value);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_11, id);
        //contentValues.put(sub_name_column, subject_name);
        contentValues.put(COLUMN_14, pres+1);
        contentValues.put(COLUMN_15, total+1);
        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + id, null);
        db.close();
    }

    public void absent_lecs(String subject_name) {
        String id_value = null;
        String present_value = null;
        String total_value = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = value(subject_name);
        if(res.getCount() == 0) {
            return ;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            id_value = res.getString(0);
            present_value = res.getString(3);
            total_value = res.getString(4);
        }

        int pres = Integer.parseInt(present_value);
        int total = Integer.parseInt(total_value);
        int id = Integer.parseInt(id_value);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_11, id);
        //contentValues.put(sub_name_column, subject_name);
        contentValues.put(COLUMN_14, pres);
        contentValues.put(COLUMN_15, total+1);
        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + id, null);
        db.close();
    }

    public void delete_present_lecs(String subject_name) {
        String id_value = null;
        String present_value = null;
        String total_value = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = value(subject_name);
        if(res.getCount() == 0) {
            return ;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            id_value = res.getString(0);
            present_value = res.getString(3);
            total_value = res.getString(4);
        }

        int pres = Integer.parseInt(present_value);
        int total = Integer.parseInt(total_value);
        int id = Integer.parseInt(id_value);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_11, id);
        //contentValues.put(sub_name_column, subject_name);
        if(pres!=0 && total!=0) {
            contentValues.put(COLUMN_14, pres - 1);
            contentValues.put(COLUMN_15, total- 1);
        }

        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + id, null);
        db.close();
    }


    public void delete_absent_lecs(String subject_name) {
        String id_value = null;
        String present_value = null;
        String total_value = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = value(subject_name);
        if(res.getCount() == 0) {
            return ;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            id_value = res.getString(0);
            present_value = res.getString(3);
            total_value = res.getString(4);
        }

        int pres = Integer.parseInt(present_value);
        int total = Integer.parseInt(total_value);
        int id = Integer.parseInt(id_value);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_11, id);
        //contentValues.put(sub_name_column, subject_name);
        contentValues.put(COLUMN_14, pres);
        contentValues.put(COLUMN_15, total-1);
        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + id, null);
        db.close();
    }

    public void delete_cancelled_lecs(String subject_name) {
        String id_value = null;
        String present_value = null;
        String total_value = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = value(subject_name);
        if(res.getCount() == 0) {
            return ;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()) {
            id_value = res.getString(0);
            present_value = res.getString(3);
            total_value = res.getString(4);
        }

        int pres = Integer.parseInt(present_value);
        int total = Integer.parseInt(total_value);
        int id = Integer.parseInt(id_value);

        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_11, id);
        //contentValues.put(sub_name_column, subject_name);
        contentValues.put(COLUMN_14, pres);
        contentValues.put(COLUMN_15, total);
        db.update(TABLE_1, contentValues, COLUMN_11 + " = " + id, null);
        db.close();
    }



    public Cursor value(String subject_name) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_1+ " where "+COLUMN_12+ " = "+"'"+subject_name+"'",null);
        return res;
    }

    public ArrayList<attmodel> sub_list() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<attmodel> subname = new ArrayList<>();
        attmodel sdetails;
        Cursor cursor = db.rawQuery("select * from " +TABLE_1,null);
        while (cursor.moveToNext()){
            sdetails = new attmodel();
            sdetails.setId(cursor.getInt(cursor.getColumnIndex(COLUMN_11)));
            sdetails.setSubjectName(cursor.getString(cursor.getColumnIndex(COLUMN_12)));
            sdetails.settAttendance(cursor.getInt(cursor.getColumnIndex(COLUMN_15)));
            sdetails.setpAttendance(cursor.getInt(cursor.getColumnIndex(COLUMN_14)));
            subname.add(sdetails);
        }
        cursor.close();
        db.close();
        return  subname;
    }


//    public void delete_lec(String sub_name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//       // String sub_name = String.valueOf(subject.getSubject());
//       //String sub_name = "AA";
//        String id_value = null;
//
//
//        Cursor res = value(sub_name);
//        if(res.getCount() == 0) {
//            return ;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()) {
//            id_value = res.getString(0);
//        }
//
//        db.delete(Table_name,id_column + " = ? ", new String[] {id_value});
//        db.close();
//    }

//    public void update_lec(String sub_name) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String id_value = null;
//        String present_value = null;
//        String total_value = null;
//        Cursor res = value(sub_name);
//        if(res.getCount() == 0) {
//            return ;
//        }
//
//        StringBuffer buffer = new StringBuffer();
//        while (res.moveToNext()) {
//            id_value = res.getString(0);
//            present_value = res.getString(2);
//            total_value = res.getString(3);
//        }
//        int id = Integer.parseInt(id_value);
//        int pres = Integer.parseInt(present_value);
//        int total = Integer.parseInt(total_value);
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(id_column, id);
//        contentValues.put(sub_name_column, sub_name);
//        contentValues.put(present_column, pres);
//        contentValues.put(total_column, total);
//        db.update(Table_name, contentValues, id_column + " = " + id, null);
//        db.close();
//
//    }

//    public void present_timeline(String subject_name,String val) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_22, subject_name);
//        contentValues.put(COL_23, val);
//        contentValues.put(COL_24, "Present");
//        db.insert(Table_2,null, contentValues);
//        db.close();
//    }

    public void cancel_timeline(String subject_name,String val) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_22, subject_name);
        contentValues.put(COL_23, val);
        contentValues.put(COL_24, "Cancelled");
        db.insert(Table_2,null, contentValues);
        db.close();
    }

    public void clear_timeline() {

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_2 ,"1",null);
        db.close();
    }
//
//    public void absent_timeline(String subject_name,String val) {
//
//        SQLiteDatabase db = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(COL_22, subject_name);
//        contentValues.put(COL_23, val);
//        contentValues.put(COL_24, "Absent");
//        db.insert(Table_2,null, contentValues);
//        db.close();
//    }

    public Cursor get_timeline() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_2,null);
        return res;
    }

    public ArrayList<timeline_model> timeline() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<timeline_model> subname = new ArrayList<>();
        timeline_model sdetails;
        Cursor cursor = db.rawQuery("select * from " +Table_2,null);
        while (cursor.moveToNext()){
            sdetails = new timeline_model();
            sdetails.setId(cursor.getInt(cursor.getColumnIndex(COL_21)));
            sdetails.setSub_name(cursor.getString(cursor.getColumnIndex(COL_22)));
            sdetails.setTimestamp(cursor.getString(cursor.getColumnIndex(COL_23)));
            sdetails.setAction(cursor.getString(cursor.getColumnIndex(COL_24)));
            subname.add(sdetails);
        }
        cursor.close();
        db.close();
        return  subname;
    }

    public ArrayList<timeline_model> timelineByDate(String date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<timeline_model> subname = new ArrayList<>();
        timeline_model sdetails;
        Cursor cursor = db.rawQuery("select * from " +Table_2 + " where "+COL_23+ " Like "+"'%"+date+"%'",null);
        while (cursor.moveToNext()){
            sdetails = new timeline_model();
            sdetails.setId(cursor.getInt(cursor.getColumnIndex(COL_21)));
            sdetails.setSub_name(cursor.getString(cursor.getColumnIndex(COL_22)));
            sdetails.setTimestamp(cursor.getString(cursor.getColumnIndex(COL_23)));
            sdetails.setAction(cursor.getString(cursor.getColumnIndex(COL_24)));
            subname.add(sdetails);
        }
        cursor.close();
        db.close();
        return  subname;
    }

    public void delete_timeline(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(Table_2, COL_21 + " = ? ", new String[]{String.valueOf(id)});
        db.close();
    }

    public void present_timeline(int id,String subject_name,String val) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_21, id);
        contentValues.put(COL_22, subject_name);
        contentValues.put(COL_23, val);
        contentValues.put(COL_24, "Present");
        db.insert(Table_2,null, contentValues);
        db.close();
    }


    public void absent_timeline(int id,String subject_name,String val) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_21, id);
        contentValues.put(COL_22, subject_name);
        contentValues.put(COL_23, val);
        contentValues.put(COL_24, "Absent");
        db.insert(Table_2,null, contentValues);
        db.close();
    }

    public void cancel_timeline(int id,String subject_name,String val) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_21, id);
        contentValues.put(COL_22, subject_name);
        contentValues.put(COL_23, val);
        contentValues.put(COL_24, "Cancelled");
        db.insert(Table_2,null, contentValues);
        db.close();
    }


}
