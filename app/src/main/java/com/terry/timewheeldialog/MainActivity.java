package com.terry.timewheeldialog;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.terry.wheelview.TimeListener;
import com.terry.wheelview.TimeWheel;

public class MainActivity extends AppCompatActivity {

    TextView timetv;

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
                        .setAction("Action", null).show();
            }
        });

        timetv = (TextView) findViewById(R.id.timetv);
        Button timebtn = (Button) findViewById(R.id.timebtn);
        timebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimeWheel(MainActivity.this, new TimeListener() {
                    @Override
                    public void getTime(int[] time) {
                        //time[0]  start hour
                        //time[1]  start minute
                        //time[2]  endhour
                        //time[3]  end minute
                        timetv.setText(getTimeStr(time));
                    }
                });
            }
        });
    }

    private String getTimeStr(int[] st) {
        return new StringBuilder()
                .append((st[0] < 10) ? ("0" + st[0]) : st[0]).append(":")
                .append((st[1] < 10) ? ("0" + st[1]) : st[1])
                .append(" ～ ")
                .append((st[2] < 10) ? ("0" + st[2]) : st[2]).append(":")
                .append((st[3] < 10) ? ("0" + st[3]) : st[3])
                .toString();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
