package com.example.cbe_teclwsp026.tab;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class first_fragment extends Fragment implements ListView_adapter.SetOnclicklistner {

    View view;
    RecyclerView recyclerView;
    DatabaseHelper mydb;
    ListView_adapter adapter;
    Modal_History modal_history;
    ArrayList<Modal_History> stringList;
    ImageView history_image;
    CheckBox custom_ChechBox;
    boolean selection = false;
    FloatingActionButton fab;
    ArrayList<String> history_pos=new ArrayList<String>();
    LinearLayoutManager linearLayoutManager;

    Button button;

    ArrayList<Integer> temp_check = new ArrayList<Integer>();


    public first_fragment() {
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_one, container, false);
        return view;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

//        recyclerView = (ListView) view.findViewById(R.id.list);
//        mydb = new DatabaseHelper(getActivity());
//
//        stringList = new ArrayList();
//        stringList = mydb.getAlllatlng();
//
//        adapter = new Adapter(getActivity(), R.layout.row_item, stringList);
//        recyclerView.setAdapter(adapter);
//
//
//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Toast.makeText(getActivity(), "Checked", Toast.LENGTH_SHORT).show();
//            }
//        });
//
    }


    @Override
    public void onResume() {
        super.onResume();


        recyclerView = (RecyclerView) getActivity().findViewById(R.id.list);

        linearLayoutManager= new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);

        fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);

//        history_image = (ImageView) getActivity().findViewById(R.id.history_image);
        mydb = new DatabaseHelper(getActivity());

        stringList = new ArrayList();
        stringList = mydb.getAlllatlng();

        adapter = new ListView_adapter(getActivity().getBaseContext(), stringList,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        custom_ChechBox = (CheckBox) getActivity().findViewById(R.id.custom_ChechBox);


//        recyclerView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
//            @Override
//            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


//                history_image.setBackgroundResource(R.drawable.checked);

//                recyclerView.getChildAt(position).setSelected(true);

//                selection = true;
//                if (selection)
//                {
//                int count = adapter.getCount();

//                for (int i = 0; i < count; i++) {
//                    View v = recyclerView.getChildAt(i);
//                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.custom_ChechBox);
//


//                    if (checkBox.getVisibility() == View.GONE) {
//                            selection = true;
//                        checkBox.setVisibility(View.VISIBLE);
//                    } else if (checkBox.getVisibility() == View.VISIBLE) {
//
//                        checkBox.setVisibility(View.GONE);
//                            selection = true;
//                    }


//                }

//                }


//                    view.setBackgroundColor(Color.LTGRAY);


//                CheckBox checkBox = (CheckBox) view.findViewById(R.id.custom_ChechBox);

//                if (checkBox.isChecked())
//                    Toast.makeText(getActivity(), "Checked", Toast.LENGTH_SHORT).show();
//                else
//                    Toast.makeText(getActivity(), "not Checked", Toast.LENGTH_SHORT).show();

//                history_image.setVisibility(View.INVISIBLE);


//                return false;

//            }
//        });


//        recyclerView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

//                selection = false;
//                Toast.makeText(getActivity(), "checked" + temp_check.toString(), Toast.LENGTH_SHORT).show();

//                Log.d("items", temp_check.toString());
//      //                for (int i = 0; i < count; i++) {
////                    View v = recyclerView.getChildAt(i);
////                    CheckBox checkBox = (CheckBox) v.findViewById(R.id.custom_ChechBox);
////
//
//
////                    if (checkBox.getVisibility() == View.GONE) {
////                            selection = true;
////                        checkBox.setVisibility(View.VISIBLE);
////                    } else if (checkBox.getVisibility() == View.VISIBLE) {
////
////                        checkBox.setVisibility(View.GONE);
////                            selection = true;
////                    }
//
//
////                }
//
////                }          view.setBackgroundColor(Color.parseColor("#ffffffff"));
//            }
//        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                check_Method();
                int count= history_pos.size();

                for (int i=0; i< count; i++)
                {

                    Log.d("value", history_pos.get(i).toString());
                    Toast.makeText(getContext(),  "value"+ history_pos.get(i), Toast.LENGTH_SHORT).show();

//                    stringList.remove(history_pos.get(i));
                    adapter.remove(history_pos.get(i));
//                    adapter.notifyItemRemoved(Integer.parseInt(history_pos.get(i)));
//                    adapter.notifyDataSetChanged();
                }


//                mydb.deleteAll();
//                adapter.notifyDataSetChanged();
//                adapter.notify();



            }
        });




//            if (checkBox.getVisibility() == View.GONE) {
//                selection = true;
//                checkBox.setVisibility(View.VISIBLE);
//            } else if (checkBox.getVisibility() == View.VISIBLE) {
//
//                checkBox.setVisibility(View.GONE);
//                selection = true;
//            }


//        }



//
//        button= (Button) view.findViewById(R.id.button);
//        button.setOnClickListener(new View.OnClickListener() {
//            @RequiresApi(api = Build.VERSION_CODES.O)
//            @Override
//            public void onClick(View v) {
//
//
//                for (int i=0; i<recyclerView.getAdapter().getCount(); i++ )
//                {
//                    View view = recyclerView.getChildAt(i);
//                    CheckBox checkBox = (CheckBox)view.findViewById(R.id.custom_ChechBox);
//
//                    if (checkBox.isChecked())
//                    {
//                        temp_check.add(i);
//                        mydb.Delete_item(i);
//
//                        Toast.makeText(getActivity(), "Checked"+ checkBox.getId(), Toast.LENGTH_SHORT).show();
//
//
//
//                    }
//
//                }
//            }
//        });

//        check_Method();


}

    private void check_Method() {

//        int count = recyclerView.getAdapter().getItemCount() - 1;
//
//        for (int i = 0; i < count ; i++) {
////            View v = recyclerView.getChildAt(i).findViewById(R.i);
//
//            RecyclerView.ViewHolder v= recyclerView.findViewHolderForAdapterPosition(i);
//
//
//            CheckBox checkBox = (CheckBox) v.itemView.findViewById(R.id.custom_ChechBox);
//
//
//            if (checkBox.isChecked())
//            {
//                Toast.makeText(getContext(),  "true", Toast.LENGTH_SHORT).show();
//            }else if (!checkBox.isChecked()){
//                Toast.makeText(getContext(),  "not  true", Toast.LENGTH_SHORT).show();
//            }
//        }
//

//        int count= recyclerView.getChildCount();
//
//        for (int i=0; i< count; i++)
//        {
//            RecyclerView.ViewHolder holder= (RecyclerView.ViewHolder) recyclerView.getChildViewHolder(recyclerView.getChildAt(i));
//
//            ImageView  delete_image= (ImageView) holder.itemView.findViewById(R.id.delete_image);
//
//            if (delete_image.getVisibility() == View.INVISIBLE)
//            {
//                delete_image.setVisibility(View.VISIBLE);
//
//            }else {
//                delete_image.setVisibility(View.INVISIBLE);
//            }
//
//            adapter.notifyItemChanged(i);
//        }
        adapter.setVisibility(true);
        adapter.notifyDataSetChanged();


        }

    @Override
    public void getposition(int position) {
//        history_pos.add(String.valueOf(position));
        Toast.makeText(getActivity(), position, Toast.LENGTH_SHORT).show();
//        adapter.remove(String.valueOf(position));

    }

    @Override
    public void OnItemClick(View view, int position) {

    }

    @Override
    public void OnItemLongClick(View view, int position) {

        check_Method();
    }




    }



