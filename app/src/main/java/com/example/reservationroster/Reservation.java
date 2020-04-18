package com.example.reservationroster;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.Locale;
import java.util.Random;

public class Reservation {


    public void startWaitTime()
    {
        Random r = new Random();
        int min = 4;
        int max = 10;
        int ranNum = r.nextInt((max-min)+1)+min;
        Shared.Data.lastWaitTime = Shared.Data.lastWaitTime + ranNum;
        Shared.Data.show.add(String.format(Locale.US,"%-1s %-25s", Shared.Data.nandn, Shared.Data.lastWaitTime));
        Shared.Data.store.add(Shared.Data.nandn);
        Shared.Data.timeList.add(Shared.Data.lastWaitTime);
    }


}
