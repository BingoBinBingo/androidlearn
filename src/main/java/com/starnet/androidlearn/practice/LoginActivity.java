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
 * Created by zhangliansheng on 19-4-7.
 */

public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private Button loginButton;
    private EditText username;
    private EditText pwd;
    private SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);
        loginButton = (Button) findViewById(R.id.login_button);
        username = (EditText) findViewById(R.id.login_username);
        pwd = (EditText) findViewById(R.id.login_password);
        loginButton.setOnClickListener(this);
        mSharedPreferences = getPreferences(Context.MODE_PRIVATE);
//        initData();
        if(mSharedPreferences.contains("username")&&mSharedPreferences.contains("password")){
            String usernameString = mSharedPreferences.getString("username",null);
            String passwordString=mSharedPreferences.getString("password",null);
            username.setText(usernameString);
            pwd.setText(passwordString);
        }
    }

    @Override
    public void onClick(View v) {
       if (username.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("警告");
            builder.setMessage("用户名不能为空");
            builder.show();
        } else if (pwd.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
            builder.setTitle("警告");
            builder.setMessage("密码不能为空");
            builder.show();
        } else {
           SharedPreferences.Editor editor = mSharedPreferences.edit();
           editor.putString("username",username.getText().toString());
           editor.putString("password",pwd.getText().toString());
           editor.apply();
        }
    }
}
