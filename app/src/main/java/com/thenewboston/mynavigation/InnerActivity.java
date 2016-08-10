package com.thenewboston.mynavigation;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class InnerActivity extends AppCompatActivity {

    private Toolbar toolbar;
    TabLayout mTabLayout;
    ViewPager mPager;
    MyPagerAdapter myPagerAdapter;
    Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inner);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        mPager = (ViewPager) findViewById(R.id.pager);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myPagerAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mPager.setAdapter(myPagerAdapter);
        //use getTitle from adapter in TabLayout
        mTabLayout.setTabsFromPagerAdapter(myPagerAdapter);
        //connecting tabs with Pager not with adapter
        mTabLayout.setupWithViewPager(mPager);
        mPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inner, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        }

        if(id == android.R.id.home){
            Intent parentIntent =  NavUtils.getParentActivityIntent(this);
            if (parentIntent == null){
                finish();
                return true;
            }else{
                parentIntent.setFlags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT | Intent.FLAG_ACTIVITY_SINGLE_TOP |
                Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(parentIntent);
                finish();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }



    class MyPagerAdapter extends FragmentStatePagerAdapter{

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Class fragmentClass;

            if (position == 0) {
                // Handle the camera action
                fragmentClass = ImportFragment.class;
            } else if (position == 1) {
                fragmentClass = GallaryFragment.class;
            }else if (position == 2) {
                fragmentClass = SlideshowFragment.class;
            }  else if (position == 3) {
                fragmentClass = ShareFragment.class;
            } else if (position == 4) {
                fragmentClass = SendFragment.class;
            }else
                fragmentClass = null;
            try {
                if(fragmentClass!=null)
                    fragment = (Fragment) fragmentClass.newInstance();

            }catch (Exception e){
                e.printStackTrace();
            }
            return fragment;
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return "Tab " + (position + 1);
        }
    }


}
