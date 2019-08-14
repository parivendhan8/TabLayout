package com.example.cbe_teclwsp026.tab;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

class CustomAdapter extends BaseAdapter{


    ArrayList<Modal_History> list= new ArrayList<>();
    Context context;
    Viewholder v;
    private int selectedPosition = -1;




    public CustomAdapter(@NonNull Context context ,ArrayList<Modal_History> data) {

        this.list= data;
        this.context = context;

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {

        v = new Viewholder();
        final int p= position;



        if (convertView == null) {

            convertView = LayoutInflater.from(context).inflate(R.layout.row_item,null,false);
            convertView.setTag(v);

        }

        v = (Viewholder) convertView.getTag();


        v.search= (TextView) convertView.findViewById(R.id.search);
        v.time= (TextView) convertView.findViewById(R.id.time);
//        v.relative_layout = (LinearLayout) convertView.findViewById(R.id.relative_layout);
//        v.history_image= (ImageView) convertView.findViewById(R.id.history_image);

//
        v.checkBox = (CheckBox) convertView.findViewById(R.id.custom_ChechBox);
        v.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                Integer pos = (Integer) buttonView.getTag();

                list.get(pos).setChecked(isChecked);
            }
        });

//        v.checkBox.setTag(position);




//        v.relative_layout.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
////
////                Toast.makeText(context, "Long click"+ v.id, Toast.LENGTH_SHORT).show();
////
////
////                if(v.checkBox.isChecked())
////                {
////
////                    Toast.makeText(context, "Checked", Toast.LENGTH_SHORT).show();
////
////                }
////
////                v.checkBox.setVisibility(View.VISIBLE);
//
//
//                return true;
//            }
//        });


//        v.history_image.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                v.setBackgroundColor(Color.LTGRAY);
//
////                v.setBackgroundDrawable(getdr);
//
//            }
//        });


        Modal_History model= list.get(position);

        if(model != null){

            v.search.setText(model.getSearch());
            v.time.setText(model.getTime());
//            v.checkBox.setTag(model.getId());

        }

        return convertView;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    class Viewholder {

        int id;
        TextView search, time;
        RelativeLayout relative_layout;
        CheckBox checkBox;
        ImageView history_image;

    }
}
