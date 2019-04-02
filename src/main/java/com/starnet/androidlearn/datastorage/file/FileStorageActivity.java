package com.starnet.androidlearn.datastorage.file;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileStorageActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "FileStorageActivity";
    private EditText editText;
    private TextView mFileStrTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.file_storage_activity_layout);

        editText = (EditText) findViewById(R.id.fileStrEt);
        mFileStrTv = (TextView) findViewById(R.id.fileStrTv);

        Button button1 = (Button) findViewById(R.id.readBtn);
        Button button2 = (Button) findViewById(R.id.writeBtn);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.readBtn:
                String date = load();
                mFileStrTv.setText(date);
                break;

            case R.id.writeBtn:
                save(editText.getText().toString(), Context.MODE_APPEND);
                break;
            default:
                break;
        }
    }

    public void save(String data, int mode) {
        FileOutputStream out = null;
        BufferedWriter writer = null;
        try {
            out = openFileOutput("file_storage.txt", mode);
            writer = new BufferedWriter(new OutputStreamWriter(out));
            writer.write(data);
        } catch (IOException e) {
            Log.v(TAG, " save data: " + data + ", error: "+ e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                Log.v(TAG, " save close error: "+ e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public String load() {
        FileInputStream in = null;
        BufferedReader reader = null;
        StringBuilder content = new StringBuilder();
        try {
            in = openFileInput("file_storage.txt");
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } }
        return content.toString();
    }


}
