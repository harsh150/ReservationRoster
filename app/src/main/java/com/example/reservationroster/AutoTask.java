package com.example.reservationroster;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class AutoTask  {


    public void updateWaitTimes()
    {
        int tempc;
        float t;
        tempc = 0;
        while (Shared.Data.store.size() > tempc) {
            t = Shared.Data.timeList.get(tempc) - random();
            Shared.Data.timeList.set(tempc, t);
            Shared.Data.lastWaitTime = t;
            tempc++;
        }
    }

    public float random()
    {
        Random r = new Random();
        int min = 2;
        int max = 4;
        float ranNum = r.nextInt((max-min)+1)+min;
        return ranNum;
    }
}


