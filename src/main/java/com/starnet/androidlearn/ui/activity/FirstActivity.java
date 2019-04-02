package com.starnet.androidlearn.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.starnet.androidlearn.R;


public class FirstActivity extends Activity {

    private Button button1;
    private TextView mTipTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2.加载布局
        setContentView(R.layout.first_layout);
        mTipTv = (TextView) findViewById(R.id.tipTv);
        button1 = (Button) findViewById(R.id.button_1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FirstActivity.this, SecondActivity.class);
                intent.putExtra("param1", "data1");
                intent.putExtra("param2", "data2");
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String returnedData = data.getStringExtra("data_return");
                    mTipTv.setText(" Data from SecondActivity data_return: " + returnedData);
                }
                break;
            default:
        }
    }

}