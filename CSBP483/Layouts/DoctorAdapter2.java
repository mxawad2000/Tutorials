package com.example.mycsbp483app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class DoctorAdapter2 extends BaseAdapter {
    private List<Doctor> items;
    private Context context;
    public DoctorAdapter2(Context context, List<Doctor> items){
        this.context = context; this.items = items;
    }
    @Override public int getCount(){ return items.size();}
    @Override public long getItemId(int position){ return position;}
    @Override public Doctor getItem(int position){ return items.get(position);}
    @Override public View getView(int position, View view, ViewGroup parent){
        if(view == null){
            LayoutInflater inflater = (LayoutInflater.from(context));
            view = inflater.inflate(R.layout.doctor_list_item, null);
        }
        Doctor doctor = items.get(position);
        //set up doctor fields in the view.
        TextView nameTV = view.findViewById(R.id.doc_name_tv);
        nameTV.setText(doctor.getName());
        //set other views
        return view;
    }
}
