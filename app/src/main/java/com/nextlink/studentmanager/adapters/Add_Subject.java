package com.nextlink.studentmanager.adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.PopupMenu;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.nextlink.studentmanager.R;
import com.nextlink.studentmanager.utils.AlertDialogsHelper;
import com.nextlink.studentmanager.utils.DbHelper;

import java.util.ArrayList;
import java.util.Objects;

public class Add_Subject extends ArrayAdapter<com.nextlink.studentmanager.model.Add_Subject> {

    private Activity mActivity;
    private int mResource;
    private ArrayList<com.nextlink.studentmanager.model.Add_Subject> sublist;
    private com.nextlink.studentmanager.model.Add_Subject subject1;
    private ListView mListView;

    private static class ViewHolder {
        TextView subject;
        TextView description;
        TextView date;
        CardView cardView;
        ImageView popup;
    }

    public Add_Subject(Activity activity, ListView listView, int resource, ArrayList<com.nextlink.studentmanager.model.Add_Subject> objects) {
        super(activity, resource, objects);
        mActivity = activity;
        mListView = listView;
        mResource = resource;
        sublist = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        String subject = Objects.requireNonNull(getItem(position)).getSubject();
        String description = Objects.requireNonNull(getItem(position)).getDescription();
//        String date = Objects.requireNonNull(getItem(position)).getDate();
//        int color = Objects.requireNonNull(getItem(position)).getColor();

        subject1 = new com.nextlink.studentmanager.model.Add_Subject(subject, description);
        final ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mActivity);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.subject = convertView.findViewById(R.id.sub_name);
            holder.description = convertView.findViewById(R.id.desctname);
            holder.cardView = convertView.findViewById(R.id.subjects_cardview);
            holder.popup = convertView.findViewById(R.id.popupbtn);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }
        holder.subject.setText(subject1.getSubject());
        holder.description.setText(subject1.getDescription());

        holder.cardView.setCardBackgroundColor(subject1.getColor());
        holder.popup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final PopupMenu popup = new PopupMenu(mActivity, holder.popup);
                final DbHelper db = new DbHelper(mActivity);
                popup.getMenuInflater().inflate(R.menu.popup_menu, popup.getMenu());
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.delete_popup:
                                db.deleteSubjectById(getItem(position));
                                db.updateSubject(getItem(position));
                                sublist.remove(position);
                                notifyDataSetChanged();
                                return true;

//                            case R.id.edit_popup:
//                                final View alertLayout = mActivity.getLayoutInflater().inflate(R.layout.dialog_add_subjects, null);
//                                AlertDialogsHelper.getEditSubject1Dialog(mActivity, alertLayout, sublist, mListView, position);
//                                notifyDataSetChanged();
//                                return true;
                            default:
                                return onMenuItemClick(item);
                        }
                    }
                });
                popup.show();
            }
        });

        hidePopUpMenu(holder);

        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public ArrayList<com.nextlink.studentmanager.model.Add_Subject> getSubjectList() {
        return sublist;
    }

    public com.nextlink.studentmanager.model.Add_Subject getSubject() {
        return subject1;
    }

    private void hidePopUpMenu(ViewHolder holder) {
        SparseBooleanArray checkedItems = mListView.getCheckedItemPositions();
        if (checkedItems.size() > 0) {
            for (int i = 0; i < checkedItems.size(); i++) {
                int key = checkedItems.keyAt(i);
                if (checkedItems.get(key)) {
                    holder.popup.setVisibility(View.INVISIBLE);
                }
            }
        } else {
            holder.popup.setVisibility(View.VISIBLE);
        }
    }
}

