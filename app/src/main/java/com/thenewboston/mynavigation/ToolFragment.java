package com.thenewboston.mynavigation;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;



public class ToolFragment extends Fragment {


    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    RelativeLayout tool_layout;


    String[] country = {"Dog", "Car", "Donkey", "WTH", "Lion", "Cat", "Football", "abc", "bcd", "sdad", "srgs",
            "gkgkf", "dtfhfy", "Hyynw", "CBSE", "vtu"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tool, container, false);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_view);
        tool_layout = (RelativeLayout) rootView.findViewById(R.id.tool_layout);
        adapter = new RecyclerAdapter(country);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getActivity(), recyclerView, new ClickListener() {
            @Override
            public void onmyClick(View view, int position) {
                Snackbar.make(view, "onClick " + position, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();
                Intent intent = new Intent(getContext(), InnerActivity.class);
                startActivity(intent);


            }

            @Override
            public void onmyLongClick(View view, int position) {
                Snackbar.make(view, "onLongPress " + position, Snackbar.LENGTH_SHORT)
                        .setAction("Action", null)
                        .show();

            }
        }));

        return rootView;

    }


    class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {


        private GestureDetector gestureDetector;
        private ClickListener clickListener;    // initialise the clickListener in constructor


        public RecyclerTouchListener(Context context, final RecyclerView recyclerView,
                                     final ClickListener clickListener) {

            this.clickListener = clickListener;

            //Manually start gesture detector
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {

                    return true;    //this has to be true
                }


                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onmyLongClick(child, recyclerView.getChildLayoutPosition(child));
                    }
                }
            });

        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
            gestureDetector.onTouchEvent(e);
            //single click method goes here
            View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onmyClick(child, rv.getChildLayoutPosition(child));
            }
            return false;


        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {

        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }

    }


    public interface ClickListener {

        void onmyClick(View view, int position);

        void onmyLongClick(View view, int position);
    }


}
