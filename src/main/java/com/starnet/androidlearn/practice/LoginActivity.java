package com.starnet.androidlearn.practice;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.starnet.androidlearn.R;

/**
 * Created by dbz on 19-4-7.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private Button loginButton;
    private EditText username;
    private EditText password;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        loginButton = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.login_username);
        password = (EditText) findViewById(R.id.login_password);
        loginButton.setOnClickListener(this);
        createFile();
        initData();

    }

    private void createFile() {
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
    }

    private void initData() {
        if(mSharedPreferences.contains("username")&&mSharedPreferences.contains("password")){
            String usernameString = mSharedPreferences.getString("username",null);
            String passwordString=mSharedPreferences.getString("password",null);
            username.setText(usernameString);
            password.setText(passwordString);
        }


    }



    @Override
    public void onClick(View v) {
        Log.d(TAG, "loginButton");

       if (username.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("提示");
            builder.setMessage("用户名不能为空");
            builder.show();
        } else if (password.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("提示");
            builder.setMessage("密码不能为空");
            builder.show();
        } else {
           SharedPreferences.Editor editor = mSharedPreferences.edit();
           editor.putString("username",username.getText().toString());
           editor.putString("password",password.getText().toString());
           editor.commit();
        }
    }
}
