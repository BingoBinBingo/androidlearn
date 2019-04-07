package com.starnet.androidlearn.parctice;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.ui.activity.SecondActivity;

import org.w3c.dom.Text;


public class LoginActivity extends Activity {

    private Button btnLogin;
    private EditText txtUserName;
    private EditText txtPass;
    private SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //2.加载布局
        setContentView(R.layout.login_view);

        btnLogin = (Button)findViewById(R.id.btnLogin);
        txtUserName = (EditText)findViewById(R.id.txtUserName);
        txtPass = (EditText)findViewById(R.id.txtPass);
        // 获取共享属性操作的工具（文件名，操作模式）
        sp = this.getSharedPreferences("data", 0);

        txtUserName.setText(sp.getString("userName", ""));
        txtPass.setText(sp.getString("pass", ""));

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtUserName.getText().toString().trim().isEmpty()==false && txtPass.getText().toString().trim().isEmpty()==false)
                {
                    Toast.makeText(LoginActivity.this,"登录成功", Toast.LENGTH_SHORT).show();
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("userName", txtUserName.getText().toString());
                    editor.putString("pass", txtPass.getText().toString());
                    editor.commit();
                }
                else{
                    Toast.makeText(LoginActivity.this,"用户名、密码不允许为空", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



}