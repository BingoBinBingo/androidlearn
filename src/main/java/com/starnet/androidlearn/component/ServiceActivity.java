package com.starnet.androidlearn.component;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.component.service.MyService;

public class ServiceActivity extends Activity implements View.OnClickListener {

    private Intent mIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.service_activity_layout);

        Button openBtn = (Button)findViewById(R.id.openBtn);
        Button closeBnt = (Button)findViewById(R.id.closeBtn);
        openBtn.setOnClickListener(this);
        closeBnt.setOnClickListener(this);
        mIntent = new Intent(this, MyService.class);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.openBtn:
                startService(mIntent);
                break;

            case R.id.closeBtn:
                stopService(mIntent);
                break;
        }
    }
}
