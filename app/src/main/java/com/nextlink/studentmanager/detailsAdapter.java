package com.nextlink.studentmanager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;
import com.nextlink.studentmanager.fragments.SaturdayFragment;

import java.util.List;

    public class detailsAdapter extends RecyclerView.Adapter<detailsAdapter.ProductViewHolder> {

float m;
int n;

        CircularProgressBar circularProgressBar;

        //this context we will use to inflate the layout
        private Context mCtx;

        //we are storing all the products in a list
        public static List<attmodel> detailList;

        //getting the context and product list with constructor
        public detailsAdapter(Context mCtx, List<attmodel> detailList) {
            this.mCtx = mCtx;
            this.detailList = detailList;
        }

        @Override
        public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            //inflating and returning our view holder

            LayoutInflater inflater = LayoutInflater.from(mCtx);
            View view = inflater.inflate(R.layout.details, null);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ProductViewHolder holder, int position) {
            //getting the attmodel of the specified position
            attmodel attmodel = detailList.get(position);


            //binding the data with the viewholder views
            holder.subject_name.setText(attmodel.getSubjectName());
            String a = String.valueOf(attmodel.getpAttendance());
            String b = String.valueOf(attmodel.gettAttendance());
            Float c= Float.valueOf(a)/Float.valueOf(b)*100;
            circularProgressBar.setProgressWithAnimation(c, 1000l); // =1s

            String perc = String.valueOf(c);

            holder.attendence.setText("Attendance:" + a + "/" + b);
            holder.status.setText("Percentage : "+perc);

            if(Integer.parseInt(b)!=0) {

                 m= Float.valueOf(a)/Float.valueOf(b)*100;
            }
            else{
                m=Float.valueOf(0) ;
            }


            String y= String.valueOf(m);
            Log.i("TAG",y);
            //holder.status.setText("Percentage:" + y + "%");

          //  holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(attmodel.getImage()));

        }


        @Override
        public int getItemCount() {
            return detailList.size();
        }


        class ProductViewHolder extends RecyclerView.ViewHolder {

            TextView subject_name, attendence, status;
            ImageView imageView;

            public ProductViewHolder(View itemView) {
                super(itemView);
                 circularProgressBar = itemView.findViewById(R.id.circularProgressBar);
                subject_name = itemView.findViewById(R.id.sub_name);
                attendence = itemView.findViewById(R.id.attendence);
                status = itemView.findViewById(R.id.status);
            }
        }
    }

