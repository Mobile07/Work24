package com.pyropy.work24.views.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.pyropy.work24.model.CoursesModel;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CourseRecyclerAdapter extends RecyclerView.Adapter<CourseRecyclerAdapter.MyViewHolder> {

    public List<CoursesModel> mCourseList;
    Context mContext;

    public CourseRecyclerAdapter(Context context, List<CoursesModel> courseList){
        mContext = context;
        mCourseList = courseList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mCourseList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
