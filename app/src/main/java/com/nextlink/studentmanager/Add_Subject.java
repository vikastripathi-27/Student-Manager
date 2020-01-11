package com.nextlink.studentmanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nextlink.studentmanager.utils.AlertDialogsHelper;
import com.nextlink.studentmanager.utils.DbHelper;

import java.util.ArrayList;


public class Add_Subject extends AppCompatActivity {

    private Context context = this;
    private ListView listView;
    private com.nextlink.studentmanager.adapters.Add_Subject adapter;
    private DbHelper db;
    private int listposition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        initAll();
    }

    private void initAll() {
        setupAdapter();
        setupListViewMultiSelect();
        setupCustomDialog();
    }

    private void setupAdapter() {
        db = new DbHelper(context);
        listView = findViewById(R.id.sublist);
        ImageView img=findViewById(R.id.imageView4);
        adapter = new com.nextlink.studentmanager.adapters.Add_Subject(Add_Subject.this, listView, R.layout.listview_subjects_adapter, db.getSubject());
        listView.setAdapter(adapter);
        if(!adapter.isEmpty()){
            img.setVisibility(View.INVISIBLE);
        }


    }

    private void setupListViewMultiSelect() {
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                listposition = position;
                final int checkedCount = listView.getCheckedItemCount();
                mode.setTitle(checkedCount + " " + getResources().getString(R.string.selected));
                if(checkedCount == 0) mode.finish();
            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.toolbar_action_mode, menu);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_delete:
                        ArrayList<com.nextlink.studentmanager.model.Add_Subject> removelist = new ArrayList<>();
                        SparseBooleanArray checkedItems = listView.getCheckedItemPositions();
                        for (int i = 0; i < checkedItems.size(); i++) {
                            int key = checkedItems.keyAt(i);
                            if (checkedItems.get(key)) {
                                db.deleteSubjectById(adapter.getItem(key));
                                removelist.add(adapter.getSubjectList().get(key));
                                //db.delete_lec(adapter.getSubject());
                            }
                        }
                        adapter.getSubjectList().removeAll(removelist);
                        db.updateSubject(adapter.getSubject());
                        adapter.notifyDataSetChanged();
                        mode.finish();
                        return true;

                    default:
                        return false;
                }
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {
            }
        });
    }

    private void setupCustomDialog() {
        final View alertLayout = getLayoutInflater().inflate(R.layout.dialog_add_subjects, null);
        AlertDialogsHelper.getAddSubjectDialog(Add_Subject.this, alertLayout, adapter);
    }
}
