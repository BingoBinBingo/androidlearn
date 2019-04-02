package com.starnet.androidlearn.thread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.starnet.androidlearn.R;

import java.util.Date;

public class HandlerActivity extends Activity {
    private static final String TAG = HandlerActivity.class.getSimpleName();
    private TextView mTv;
    private static Handler mHandler;
    private static final int UPGRADE_UI = 1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.handler_activity_layout);
        mTv = (TextView) findViewById(R.id.threadTv);

        //1. 创建Handler
        mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                //4.处理 消息
                switch (msg.what) {
                    case UPGRADE_UI:
                        mTv.append(String.valueOf(msg.obj) + "\n");
                        break;

                }
            }
        };

        //2. 创建线程在 5秒之后更新UI
        Thread runnableThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //3.1 发送消息给主线程
                    Thread.sleep(3000);
                    Message msg = mHandler.obtainMessage();
                    msg.what = UPGRADE_UI;
                    msg.obj = new Date();
                    mHandler.sendMessage(msg);

                    //3.2 发送消息给主线程
                    Thread.sleep(3000);
                    Message msg2 = mHandler.obtainMessage();
                    msg2.what = UPGRADE_UI;
                    msg2.obj = new Date();
                    mHandler.sendMessage(msg2);


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        runnableThread.start();
    }


}
