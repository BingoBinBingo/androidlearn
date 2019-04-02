package com.starnet.androidlearn.thread;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.starnet.androidlearn.R;

import java.util.Date;

public class AsyncTaskActivity extends Activity {
    private static final String TAG = AsyncTaskActivity.class.getSimpleName();
    private TextView mTv;
    private static Handler mHandler;
    private static final int UPGRADE_UI = 1;

    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.async_task_activity_layout);
        mTv = (TextView) findViewById(R.id.threadTv);

        //1. 创建异步线程
        UITask task = new UITask();
        task.execute();
    }

    private class UITask extends AsyncTask<Void, Integer, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //2.开始【子线程】之前，提供回调函数在【UI线程】进行一些初始化
            mTv.setText("开始执行任务!");
        }

        @Override
        protected String doInBackground(Void... voids) {

            //3.子线程开始执行
            for(int index = 5; index >0; index--) {
                //4.通知【子线程】执行过程中的情况给【UI线程】
                publishProgress(index);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return new Date().toString();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //5.收到【子线程】发送给【UI线程】的信息
            mTv.setText("开始执行任务:\n UI更新倒计时【" + values[0] + "】秒");
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            //6.【子线程】执行完毕，【UI线程】接收前者的数据进行显示
            mTv.setText("完成更新任务:\n" + s);
        }
    }


}
