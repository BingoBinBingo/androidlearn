package com.starnet.androidlearn.datastorage.sp;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.starnet.androidlearn.R;

public class SharedPreferenceStorageActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "FileStorageActivity";
    private EditText editText;
    private TextView mFileStrTv;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shared_preference_storage_activity_layout);

        editText = (EditText) findViewById(R.id.fileStrEt);
        mFileStrTv = (TextView) findViewById(R.id.fileStrTv);

        Button button1 = (Button) findViewById(R.id.readBtn);
        Button button2 = (Button) findViewById(R.id.writeBtn);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        createFile();
    }

    private void createFile() {
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readBtn:
                int number = mSharedPreferences.getInt("number", 1);
                mFileStrTv.setText(String.valueOf(number));
                break;

            case R.id.writeBtn:
                int prevNumber = mSharedPreferences.getInt("number", 1);
                mSharedPreferences.edit().putInt("number",++prevNumber).commit();
                break;
            default:
                break;
        }
    }


}
