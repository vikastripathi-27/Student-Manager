package com.nextlink.studentmanager;

import android.provider.BaseColumns;

public class TaskContract {

    public class TaskEntry implements BaseColumns {
        public static final String TABLE = "tasks";

        public static final String COL_TASK_TITLE = "title";
    }

}
