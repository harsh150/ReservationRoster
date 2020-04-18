package com.example.reservationroster;

import android.support.v7.app.AppCompatActivity;

import java.util.ArrayList;

public enum Shared
{

    // Define enum value
    Data;

    // Declare enum fields
    //public final int WORKER_THREAD_PAUSE = 1;  // seconds
    public String name, number, nandn;
    public float task, lastWaitTime;
    public int counterTimer;
    ArrayList<String> store=new ArrayList<>();
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> show=new ArrayList<>();
    ArrayList<Float> timeList=new ArrayList<>();
}
