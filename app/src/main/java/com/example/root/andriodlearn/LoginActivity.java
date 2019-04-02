package com.example.root.andriodlearn;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {


    // UI references.
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // Set up the login form.
        mUsernameView = findViewById(R.id.email);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                showDialog("Login Exception","Fail");
                return false;
            }
        });

        SharedPreferences sp=getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
        String json = sp.getString(String.valueOf(1), null);
//        showDialog("json",json);
        mUsernameView.setText(getValue(json,"username"));
        mPasswordView.setText(getValue(json,"password"));

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });


    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    }


    private void attemptLogin() {

        // Reset errors.
        mUsernameView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        String username = mUsernameView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }


        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError(getString(R.string.error_invalid_email));
            focusView = mUsernameView;
            cancel = true;
        }


        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            UserBean newUser=new UserBean(username,password);
            SharedPreferences sp=getApplicationContext().getSharedPreferences("config", MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();

            String json = newUser.getJson();
            editor.putString(String.valueOf(1), json);
            editor.apply();

            showDialog("Login Result","success");
        }
    }

    private void showDialog(String tag,String txt) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(tag);
        builder.setMessage(txt);
        builder.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private String getValue(String json, String attr)
    {

        try {
            int index1 = json.indexOf("\""+attr+"\"");
            String newData = json.substring(index1 + attr.length()+2);
            int index2 = newData.indexOf("\"");
            int index3 = newData.substring(index2+1).indexOf("\"");
            return newData.substring(index2+1, index2+index3+1);
        }
        catch (Exception e)
        {
            return null;
        }
    }
}

