package com.starnet.androidlearn.component.provider;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.Log;

public class BookProviderActivity extends Activity {
    private static final String TAG = BookProviderActivity.class.getSimpleName();
    private String newId;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_provider_activity_main);
        mTextView = (TextView) findViewById(R.id.fileStrTv);
        Button addData = (Button) findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 添加数据
                Uri uri = Uri.parse("content://com.starnet.androidlearn.provider/book");
                ContentValues values = new ContentValues();
                values.put("name", "A Clash of Kings");
                values.put("author", "George Martin");
                values.put("pages", 1040);
                values.put("price", 55.55);
                Uri newUri = getContentResolver().insert(uri, values);
                newId = newUri.getPathSegments().get(1);
            }
        });
        Button queryData = (Button) findViewById(R.id.query_data);
        queryData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 查询数据
                Uri uri = Uri.parse("content://com.starnet.androidlearn.provider/book");
                Cursor cursor = getContentResolver().query(uri, null, null, null, null);
                if (cursor != null) {
                    String str = "";
                    while (cursor.moveToNext()) {
                        String name = cursor.getString(cursor. getColumnIndex("name"));
                        String author = cursor.getString(cursor. getColumnIndex("author"));
                        int pages = cursor.getInt(cursor.getColumnIndex ("pages"));
                        double price = cursor.getDouble(cursor. getColumnIndex("price"));
                        str = str + "book name is " + name + "\n"
                                + "book author is " + author + "\n"
                                + "book pages is " + pages + "\n"
                                + "book price is " + price + "\n";
                        Log.d(TAG, "book name is " + name);
                        Log.d(TAG, "book author is " + author);
                        Log.d(TAG, "book pages is " + pages);
                        Log.d(TAG, "book price is " + price);
                    }
                    cursor.close();
                    mTextView.setText(str);
                }
            }
        });
        Button updateData = (Button) findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 更新数据
                Uri uri = Uri.parse("content://com.starnet.androidlearn.provider/book/" + newId);
                ContentValues values = new ContentValues();
                values.put("name", "A Storm of Swords");
                values.put("pages", 1216);
                values.put("price", 24.05);
                getContentResolver().update(uri, values, null, null);
            }
        });
        Button deleteData = (Button) findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 删除数据
                Uri uri = Uri.parse("content://com.starnet.androidlearn.provider/book/" + newId);
                getContentResolver().delete(uri, null, null);
            }
        });
    }

}

