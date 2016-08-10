package com.thenewboston.mynavigation;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;




public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String SELECTED_ITEM_ID = "selected_item_id";
    FragmentManager fragmentManager = getSupportFragmentManager();
    Fragment fragment = null;
    private int mSelectedId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setCheckedItem(R.id.nav_camera);
        navigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null){
            Fragment importFragment = new ImportFragment();
            FragmentManager fragmentTransaction = getSupportFragmentManager();
            fragmentTransaction.beginTransaction().replace(R.id.containerView,importFragment,null).commit();
            setTitle(R.string.import_title);
        }else{
            mSelectedId = savedInstanceState.getInt(SELECTED_ITEM_ID);
            navigate(mSelectedId);
        }


    }




    @Override
    public void onBackPressed() {
        boolean var =false;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else {
           super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        mSelectedId =item.getItemId();
        navigate(mSelectedId);

        fragmentManager.beginTransaction().replace(R.id.containerView, fragment).commit();
        setTitle(item.getTitle());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        return true;
    }


    private void navigate(int mSelectedId) {

        Class fragmentClass;
        if (mSelectedId == R.id.nav_camera) {
            // Handle the camera action
            fragmentClass = ImportFragment.class;
        } else if (mSelectedId == R.id.nav_gallery) {
            fragmentClass = GallaryFragment.class;
        }else if (mSelectedId == R.id.nav_slideshow) {
            fragmentClass = SlideshowFragment.class;
        } else if (mSelectedId == R.id.nav_manage) {
            fragmentClass = ToolFragment.class;
        } else if (mSelectedId == R.id.nav_share) {
            fragmentClass = ShareFragment.class;
        } else if (mSelectedId == R.id.nav_send) {
            fragmentClass = SendFragment.class;
        }else
            fragmentClass = null;
        try {
            if(fragmentClass!=null)
                fragment = (Fragment) fragmentClass.newInstance();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //for remembering
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SELECTED_ITEM_ID,mSelectedId);
    }
}
