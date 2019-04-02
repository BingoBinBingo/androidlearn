package com.starnet.androidlearn.component.broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyBroadcast extends BroadcastReceiver {

    /**
     * 1.当接收到广播的时候被回调
     * @param context
     * @param intent
     */
    @Override
    public void onReceive(Context context, Intent intent) {

        switch (intent.getAction()) {
            case BroadCastReceiverActivity.ACTION:
                String data = intent.getStringExtra("param");
                Toast.makeText(context,
                        " MyBroadcast onReceive data:" + data + ", thread id: " + Thread.currentThread().getName(), Toast.LENGTH_LONG).show();
                break;
            case Intent.ACTION_TIME_TICK:
                //每分钟更新
                Toast.makeText(context,
                        " MyBroadcast onReceive time change", Toast.LENGTH_LONG).show();
                break;
        }

    }


}
