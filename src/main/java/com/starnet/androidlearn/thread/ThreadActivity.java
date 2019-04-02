package com.starnet.androidlearn.thread;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

public class ThreadActivity extends Activity {
    private static final String TAG = ThreadActivity.class.getSimpleName();
    private TextView mTv;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thread_activity_layout);
        mTv = (TextView) findViewById(R.id.threadTv);

        //1. 继承 Thread
        MyThead myThead = new MyThead();
        myThead.start();

        //2. 实现 Runnable
        Thread runnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(10000);
                    Log.i(TAG, " runnableThread run ... ");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        runnableThread.start();
    }

    public class MyThead extends Thread {
        @Override
        public void run() {
            super.run();
            try {
                Thread.sleep(5000);
                Log.i(TAG, " MyThread run ... ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
