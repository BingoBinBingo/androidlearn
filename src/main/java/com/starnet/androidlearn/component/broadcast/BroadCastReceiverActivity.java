package com.starnet.androidlearn.component.broadcast;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.starnet.androidlearn.R;

public class BroadCastReceiverActivity extends Activity implements View.OnClickListener {

    public static final String ACTION = "com.starnet.androidlearn.testbroadcast_ACTION";
    private MyBroadcast mMyBroadcast = new MyBroadcast();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.broadcast_reciver_activity_layout);

        Button openBtn = (Button)findViewById(R.id.openBtn);
        openBtn.setOnClickListener(this);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_TIME_TICK);
        registerReceiver(mMyBroadcast, filter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openBtn:
                Intent intent = new Intent(ACTION);
                intent.putExtra("param", "Android Broadcast");
                sendBroadcast(intent);
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBroadcast);
    }
}
