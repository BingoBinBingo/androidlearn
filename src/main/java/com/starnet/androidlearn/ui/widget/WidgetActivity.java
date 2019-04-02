package com.starnet.androidlearn.ui.widget;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.starnet.androidlearn.R;

public class WidgetActivity extends Activity implements View.OnClickListener {

    private EditText editText;
    private ImageView imageView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.widget_activity_main);
        editText = (EditText) findViewById(R.id.edit_text);
        final EditText editText2 = (EditText) findViewById(R.id.edit_text2);
        imageView  = (ImageView) findViewById(R.id.image_view);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        Button button1 = (Button) findViewById(R.id.buttonAlertDialog);
        Button button2 = (Button) findViewById(R.id.buttonProgressDialog);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button2.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(WidgetActivity.this, R.string.button_long_click_title,
                        Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(WidgetActivity.this,
                        " 文本内容变化了：" + editText.getText().toString(),
                        Toast.LENGTH_SHORT).show();
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    editText2.setText("I am Focused");
                } else {
                    editText2.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonProgressDialog:
                ProgressDialog progressDialog = new ProgressDialog(WidgetActivity.this);
                progressDialog.setTitle("This is ProgressDialog");
                progressDialog.setMessage("Android UI界面编程 Loading...");
                progressDialog.setCancelable(true);
                progressDialog.show();
                break;

            case R.id.buttonAlertDialog:
                AlertDialog.Builder builder = new AlertDialog.Builder(WidgetActivity.this);
                builder.setTitle("我是提示对话框").setMessage("Android UI界面编程").show();
                break;
            default:
                break;
        }
    }

}
