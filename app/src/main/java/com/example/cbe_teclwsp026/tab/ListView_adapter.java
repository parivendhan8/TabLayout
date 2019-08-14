package com.example.cbe_teclwsp026.tab;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ListView_adapter extends RecyclerView.Adapter<ListView_adapter.MyViewHolder> {

    ArrayList<Modal_History> list = new ArrayList<Modal_History>();
    Context context;
    SetOnclicklistner setOnclicklistner;

    boolean isVisible= false;


    public ListView_adapter(Context context, ArrayList<Modal_History> list, SetOnclicklistner setOnclicklistner) {
        this.list = list;
        this.context = context;
        this.setOnclicklistner = setOnclicklistner;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.row_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        Modal_History modal_history = list.get(position);

        holder.time.setText(modal_history.getTime());
        holder.search.setText(modal_history.getSearch());
        holder.id_history.setText(modal_history.getId());


        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
            }
        });

//        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//
////                list.get(position).setChecked(isChecked);
//                setOnclicklistner.getposition(position);
//
//            }
//        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                setOnclicklistner.OnItemLongClick(v, position);
                return true;
            }
        }) ;

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//
//                setOnclicklistner.OnItemLongClick(v, position);
//
//                if (holder.imageView.getVisibility() == View.VISIBLE)
//                {
//                    holder.imageView.setVisibility(View.INVISIBLE);
//                    holder.history_image.setVisibility(View.VISIBLE);
//                }
//
//                else{
//                    holder.imageView.setVisibility(View.VISIBLE);
//                    holder.history_image.setVisibility(View.INVISIBLE);
//                }
//
//                return true;
//            }
//        });

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                setOnclicklistner.getposition(position);
                list.remove(position);
                notifyItemChanged(position);
                notifyDataSetChanged();

                DatabaseHelper mydb = new DatabaseHelper(context);
                mydb.Delete_item(Integer.valueOf(holder.id_history.getText().toString()));


            }
        });


        if (isVisible)
        {
            holder.imageView.setVisibility(View.VISIBLE);
        }
        else
        {
            holder.imageView.setVisibility(View.INVISIBLE);
        }




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        String id;
        TextView search, time, id_history;
        CheckBox checkBox;
        ImageView imageView, history_image;
        LinearLayout listlayout;
        SetOnclicklistner setOnclicklistner;

        public MyViewHolder(View itemView) {
            super(itemView);

            search = (TextView) itemView.findViewById(R.id.search);
            time = (TextView) itemView.findViewById(R.id.time);
            checkBox = (CheckBox) itemView.findViewById(R.id.custom_ChechBox);
            imageView = (ImageView) itemView.findViewById(R.id.delete_image);
            id_history = (TextView) itemView.findViewById(R.id.id_history);
            history_image = (ImageView) itemView.findViewById(R.id.history_image);


        }


    }

    public interface SetOnclicklistner {
        void getposition(int position);

        void OnItemClick(View view, int position);

        void OnItemLongClick(View view, int position);



    }


    public void remove(String pos) {

        list.remove(pos);
//        notifyItemRemoved(Integer.parseInt(pos));
        notifyDataSetChanged();
    }

    public void setVisibility(boolean isVisibile)
    {
        this.isVisible= isVisibile;
    }
}

