package com.example.cbe_teclwsp026.tab;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    Toolbar toolbar;
    DatabaseHelper mydb;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar= (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tabLayout= (TabLayout) findViewById(R.id.tabLayout);

        viewPager= (ViewPager) findViewById(R.id.viewPager);
        tabLayout.setupWithViewPager(viewPager);
        setViewPager(viewPager);

        mydb= new DatabaseHelper(this);



    }



    private void setViewPager(ViewPager viewPager) {

        ViewPagerAdapter viewPagerAdapter= new ViewPagerAdapter(getSupportFragmentManager());

        viewPagerAdapter.addFragment(new first_fragment(), "First");
        viewPagerAdapter.addFragment(new second_fragment(), "Second");
        viewPagerAdapter.addFragment(new third_fragment(), "Third");

        viewPager.setAdapter(viewPagerAdapter);


    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater= getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        MenuItem item= menu.findItem(R.id.app_bar_search);

        SearchManager searchManager= (SearchManager) getSystemService(Context.SEARCH_SERVICE);

        SearchView searchView= (SearchView) item.getActionView();

        assert searchView != null;
        searchView.setQueryHint("Search........");
        assert searchManager != null;
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setIconified(true);
        searchView.setSubmitButtonEnabled(true);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {

                Log.d("Search", s);

                boolean result= mydb.insert(s);

                if (result)
                {
                    Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();
//                    FragmentHistory.getFragmentHistory().notify_list();
//                    FragmentHistory fragmentHistory= new FragmentHistory();
//                    ((FragmentHistory)fragmentHistory).notify_list();


            }
                else
                {
                    Toast.makeText(MainActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

                return result;
            }

            @Override
            public boolean onQueryTextChange(String s) {

//                interfaceSearch.search(s);
                return false;
            }
        });

        return true;


    }
}
