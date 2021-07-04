package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

import org.jetbrains.annotations.NotNull;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StopWatch#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StopWatch extends Fragment {

    Button start,stop, reset;
    // Animation rotating;
    ObjectAnimator rotating;
    ImageView img;
    Chronometer chronometer;
    long timePaused = 0;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StopWatch() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StopWatch.
     */
    // TODO: Rename and change types and number of parameters
    public static StopWatch newInstance(String param1, String param2) {
        StopWatch fragment = new StopWatch();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        start = view.findViewById(R.id.startbtn);
        reset = view.findViewById(R.id.resetbtn);
        img = view.findViewById(R.id.anchorimg);
        chronometer = view.findViewById(R.id.chronometer);
        stop=view.findViewById(R.id.pausebtn);


        rotating = ObjectAnimator.ofFloat(img, "rotation", 0, 360);
        rotating.setDuration(4000);
        rotating.setRepeatCount(ObjectAnimator.INFINITE);
        rotating.setRepeatMode(ObjectAnimator.RESTART);


        stop.setVisibility(View.INVISIBLE);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // img.startAnimation(rotating);
                if(rotating.isStarted())
                {
                    rotating.resume();
                } else rotating.start();

                chronometer.setBase(SystemClock.elapsedRealtime() + timePaused);
                chronometer.start();
                stop.setVisibility(View.VISIBLE);
                start.animate().alpha(0).setDuration(1000).start();
                stop.animate().alpha(1).translationY(-100).setDuration(1000).start();
            }
        });
        stop.setOnClickListener(view1 -> {
            img.clearAnimation();
            rotating.pause();
            timePaused = chronometer.getBase() - SystemClock.elapsedRealtime();

            chronometer.stop();
            start.animate().alpha(1).setDuration(1000).start();
            stop.animate().alpha(0).translationY(100).setDuration(1000).start();
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rotating.isStarted() || rotating.isRunning())
                {
                    start.animate().alpha(1).setDuration(1000).start();
                    stop.animate().alpha(0).translationY(100).setDuration(1000).start();
                }
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.stop();
                rotating.end();
                timePaused = 0;

            }
        });


    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        return inflater.inflate(R.layout.fragment_stop_watch, container, false);
    }
}
