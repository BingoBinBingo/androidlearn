package com.starnet.androidlearn.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.starnet.androidlearn.R;

public class SecondActivity extends Activity {

    private TextView mTipTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_layout);
        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello FirstActivity");
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
        mTipTv = (TextView) findViewById(R.id.tipTv);
        String data1 = getIntent().getStringExtra("param1");
        String data2 = getIntent().getStringExtra("param2");

        mTipTv.setText(" Data from FirstActivity\n param1: " + data1 + "\n param2: " + data2);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstActivity");
        setResult(RESULT_OK, intent);
        finish();
    }

}