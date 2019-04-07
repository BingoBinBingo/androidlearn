package com.starnet.androidlearn.practice;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

/**
 * Created by ywb on 19-4-7.
 */

public class LoginActivity extends Activity{
    private EditText text_user;
    private EditText text_password;
    private Button login_btn;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity_layout);

        text_user = (EditText) findViewById(R.id.username);
        text_password = (EditText) findViewById(R.id.password);
        login_btn = (Button) findViewById(R.id.btn_login);
        createFile();
        login();
        init();
    }

    private void createFile() {
        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    }


    public void init()
    {
        if (sharedPreferences.contains("username")&&sharedPreferences.contains("password")){

            String username = sharedPreferences.getString("username","");
            String password = sharedPreferences.getString("password","");
            text_user.setText(username);
            text_password.setText(password);
        }

    }

    public void login()
    {
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Login","onClick");
                if (TextUtils.isEmpty(text_user.getText().toString())){
                    Toast.makeText(LoginActivity.this,"请输入用户名",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(text_password.getText().toString())){
                    Toast.makeText(LoginActivity.this,"请输入密码",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();


                    sharedPreferences.edit().putString("username",text_user.getText().toString()).commit();
                    sharedPreferences.edit().putString("password",text_password.getText().toString()).commit();

                }
            }
        });

    }

}
