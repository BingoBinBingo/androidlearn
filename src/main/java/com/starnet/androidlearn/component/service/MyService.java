package com.starnet.androidlearn.component.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

public class MyService extends Service {
    /**
     * 1.第一次启动服务的时候被调用，只被调用一次
     */
    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, " MyService onCreate", Toast.LENGTH_SHORT).show();
    }


    /**
     * 2.每次被startService()的时候，都会被调用一次
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, " MyService onStartCommand startId: " + startId, Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    /**
     *3.当 Context.stopService()函数或者 Service.stopSelf()被调用的时候，onDestroy()函数被回调
     * 标志着生命周期结束
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, " MyService onDestroy", Toast.LENGTH_SHORT).show();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
