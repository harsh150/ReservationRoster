//====================================================================
//
// Application: <Reservation Roster>
// Activity:    <life>
// Course:      CSC 4992
// Homework:    <3>
// Author:      <Harsh Patel>
// Date:        <3/20/2019>
// Description:
//   <App to keep track of resturant reservations>
//
//====================================================================

package com.example.reservationroster;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import java.util.Locale;




public class ActMain extends AppCompatActivity implements View.OnClickListener {
    private Handler mHandler = new Handler();
    private Handler mHandler2 = new Handler();

    public Button start, stop, reserve, clear;
    public EditText partyName, partyNum;
    public ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.laymain);
        init();
    }

    public void init() {
        Shared.Data.counterTimer = 0;
        // makes array panel all 30 for new image order
        start = (Button)findViewById(R.id.start);
        start.setOnClickListener(this);
        stop = (Button)findViewById(R.id.stop);
        stop.setOnClickListener(this);
        reserve = (Button)findViewById(R.id.reserve);
        reserve.setOnClickListener(this);
        clear = (Button)findViewById(R.id.clear);
        clear.setOnClickListener(this);
        //
        partyName = (EditText)findViewById(R.id.partyName);
        partyNum =(EditText)findViewById(R.id.partyNum);
        mHandler2.postDelayed(mToastRunnable2, 15000);
        //
        listview = (ListView)findViewById(R.id.listview);
        //
        Shared.Data.lastWaitTime = 0;
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "lisa", "2"));
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "bob", "3"));
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "timmy", "7"));
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "drake", "1"));
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "tony", "8"));
        Shared.Data.arrayList.add(String.format(Locale.US,"%-30s %-25s", "tracy", "5"));
        //
    }

    public void showReservations() {
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,Shared.Data.show);
        listview.setAdapter(arrayAdapter);
    }

    public void startTimerTask() {
        String temp;
        if ((Shared.Data.arrayList.size() >= 1) && (Shared.Data.counterTimer <= 5)){
            temp = Shared.Data.arrayList.get(Shared.Data.counterTimer);
            Shared.Data.nandn = temp;
            Reservation res = new Reservation();
            res.startWaitTime();
            Shared.Data.counterTimer++;
            mHandler.postDelayed(mToastRunnable, 3000);
            showReservations();
        }
        else {
            //init();
            Shared.Data.task = 1;
            Shared.Data.counterTimer = 0;
        }

    }


    private Runnable mToastRunnable = new Runnable() {
        @Override
        public void run() {
            if (Shared.Data.task == 0){
                startTimerTask();
            }
        }
    };

    private Runnable mToastRunnable2 = new Runnable() {
        @Override
        public void run() {
            AutoTask auto = new AutoTask();
            auto.updateWaitTimes();
            updateReservations();
            mHandler2.postDelayed(this, 15000);
        }
    };

    public void makeReservation() {
        String tout;
        String pname = partyName.getText().toString();
        String n = partyNum.getText().toString();
        Shared.Data.name = pname;
        Shared.Data.number = n;
        if((n.trim().length() != 0) && (pname.trim().length() != 0)){
            Toast.makeText(getApplicationContext(), "Reserved", Toast.LENGTH_LONG).show();
            int c = 0;
            tout = String.format(Locale.US,"%-30s %-25s", pname, n);
            Shared.Data.nandn = tout;
            Reservation res2 = new Reservation();
            res2.startWaitTime();
            showReservations();
        }
        else {
            Toast.makeText(getApplicationContext(), "Invalid Input", Toast.LENGTH_LONG).show();
        }
        Shared.Data.task = 0;
    }

    public void clearReservation() {
        ((EditText) findViewById(R.id.partyName)).setText("");
        ((EditText) findViewById(R.id.partyNum)).setText("");
    }

    public void stopTimerTask() {
        Shared.Data.task = 1;
        Toast.makeText(getApplicationContext(), "Ending Task", Toast.LENGTH_LONG).show();
    }

    public void updateReservations() {
        int tempc;
        tempc = 0;
        Shared.Data.show.clear();
        while (Shared.Data.store.size() > tempc) {
            if (Shared.Data.timeList.get(tempc) > 0){
                Shared.Data.show.add(String.format(Locale.US,"%-1s %-25s", Shared.Data.store.get(tempc), Shared.Data.timeList.get(tempc)));
            }
            tempc++;
        }
        showReservations();
    }

    public void onClick(View v) {
        //
        switch (v.getId()) {
            case R.id.start:
                Toast.makeText(getApplicationContext(), "Starting Task" , Toast.LENGTH_LONG).show();
                Shared.Data.task = 0;
                mHandler.postDelayed(mToastRunnable, 3000);
                break;
            case R.id.stop:
                stopTimerTask();
                break;
            case R.id.reserve:
                makeReservation();
                break;
            case R.id.clear:
                clearReservation();
                break;
        }
    }
    //
}
