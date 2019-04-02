package com.starnet.androidlearn;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.starnet.androidlearn.component.ServiceActivity;
import com.starnet.androidlearn.component.broadcast.BroadCastReceiverActivity;
import com.starnet.androidlearn.component.provider.BookProviderActivity;
import com.starnet.androidlearn.component.provider.ContactsProviderActivity;
import com.starnet.androidlearn.datastorage.file.FileStorageActivity;
import com.starnet.androidlearn.datastorage.sp.SharedPreferenceStorageActivity;
import com.starnet.androidlearn.datastorage.sqlite.SqliteStorageActivity;
import com.starnet.androidlearn.media.PlayAudioActivity;
import com.starnet.androidlearn.media.PlayVideoActivity;
import com.starnet.androidlearn.network.HttpActivity;
import com.starnet.androidlearn.network.WebViewActivity;
import com.starnet.androidlearn.thread.AsyncTaskActivity;
import com.starnet.androidlearn.thread.HandlerActivity;
import com.starnet.androidlearn.thread.ThreadActivity;
import com.starnet.androidlearn.ui.activity.FirstActivity;
import com.starnet.androidlearn.ui.widget.LayoutActivity;
import com.starnet.androidlearn.ui.widget.WidgetActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private static final String TITLE_UI_ACTIVITY = "2.1：四大组件之-Activity";
    private static final String TITLE_UI_WIDGET = "2.2：常见的控件的使用方式";
    private static final String TITLE_UI_LAYOUT = "2.3：四大布局";

    private static final String TITLE_STORAGE_FILE = "3.1：数据存储-文件";
    private static final String TITLE_STORAGE_SP = "3.2：数据存储-SharedPreference";
    private static final String TITLE_STORAGE_SQLITE = "3.3：数据存储_SQLITE";

    private static final String TITLE_NETWORK_WEBVIEW = "4.1：网络请求_WebView";
    private static final String TITLE_NETWORK_HTTP = "4.1：网络请求_HTTP请求";

    private static final String TITLE_THREAD_THREAD = "5.1：多线程_Thread";
    private static final String TITLE_THREAD_HANDLER = "5.2：多线程_Handler";
    private static final String TITLE_THREAD_ASYNCTASK = "5.3：多线程_AsyncTask";

    private static final String TITLE_MULTI_MEDIA_AUDIO = "6.1：手机多媒体_播放音频";
    private static final String TITLE_MULTI_MEDIA_VIDEO = "6.2：手机多媒体_播放视频";

    private static final String TITLE_4_COMPONENT_SERVICE = "7.1：四大组件_Service";
    private static final String TITLE_4_COMPONENT_BROADCASTRECEIVER= "7.2：四大组件_BroadcastReceiver";
    private static final String TITLE_4_COMPONENT_CONTENTPROVIDER_CONTACT = "7.3.1：四大组件_ContentProvider_系统数据";
    private static final String TITLE_4_COMPONENT_CONTENTPROVIDER_BOOK = "7.3.2：四大组件_ContentProvider_自定义数据";

    private List<Class> mActivityClassList = new ArrayList<>();

    {
        mActivityClassList.add(FirstActivity.class);
        mActivityClassList.add(WidgetActivity.class);
        mActivityClassList.add(LayoutActivity.class);

        mActivityClassList.add(FileStorageActivity.class);
        mActivityClassList.add(SharedPreferenceStorageActivity.class);
        mActivityClassList.add(SqliteStorageActivity.class);

        mActivityClassList.add(WebViewActivity.class);
        mActivityClassList.add(HttpActivity.class);

        mActivityClassList.add(ThreadActivity.class);
        mActivityClassList.add(HandlerActivity.class);
        mActivityClassList.add(AsyncTaskActivity.class);

        mActivityClassList.add(PlayAudioActivity.class);
        mActivityClassList.add(PlayVideoActivity.class);

        mActivityClassList.add(ServiceActivity.class);
        mActivityClassList.add(BroadCastReceiverActivity.class);
        mActivityClassList.add(ContactsProviderActivity.class);
        mActivityClassList.add(BookProviderActivity.class);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        ListView lv = (ListView)findViewById(R.id.activityLv);
        List<String> titles = new ArrayList<>();


        titles.add(TITLE_UI_ACTIVITY);
        titles.add(TITLE_UI_WIDGET);
        titles.add(TITLE_UI_LAYOUT);

        titles.add(TITLE_STORAGE_FILE);
        titles.add(TITLE_STORAGE_SP);
        titles.add(TITLE_STORAGE_SQLITE);

        titles.add(TITLE_NETWORK_WEBVIEW);
        titles.add(TITLE_NETWORK_HTTP);

        titles.add(TITLE_THREAD_THREAD);
        titles.add(TITLE_THREAD_HANDLER);
        titles.add(TITLE_THREAD_ASYNCTASK);

        titles.add(TITLE_MULTI_MEDIA_AUDIO);
        titles.add(TITLE_MULTI_MEDIA_VIDEO);

        titles.add(TITLE_4_COMPONENT_SERVICE);
        titles.add(TITLE_4_COMPONENT_BROADCASTRECEIVER);
        titles.add(TITLE_4_COMPONENT_CONTENTPROVIDER_CONTACT);
        titles.add(TITLE_4_COMPONENT_CONTENTPROVIDER_BOOK);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(arrayAdapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, mActivityClassList.get(position));
                startActivity(intent);
            }
        });

    }
}
