package com.example.myapplication;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.Toast;

import org.jetbrains.annotations.NotNull;


public class Counter extends Fragment {
    EditText editText;
    Chronometer chronometer;
    Button start,stop, reset;
    String ed_text;
    long countingNumber;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Counter() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Counter.
     */
    // TODO: Rename and change types and number of parameters
    public static Counter newInstance(String param1, String param2) {
        Counter fragment = new Counter();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//pauseCountingBtn
        editText = view.findViewById(R.id.edttxt);
        start = view.findViewById(R.id.countingbtn);
        reset = view.findViewById(R.id.resetbtnc);
        stop = view.findViewById(R.id.pauseCountingBtn);
        chronometer = view.findViewById(R.id.counter);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ed_text = editText.getText().toString().trim();

                if (ed_text.isEmpty() || Integer.valueOf(ed_text)< 0)
                {
                    Toast.makeText(getContext(),"Please enter a positive number",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Timer(ed_text);
                }

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_counter, container, false);
    }

    public void Timer(String number)
    {
        countingNumber = Long.parseLong(number) * 1000;
        new CountDownTimer(countingNumber, 1000) {
            public void onTick(long millisUntilFinished) {

            editText.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                editText.setText("Done");
            }
        }.start();
    }
}
