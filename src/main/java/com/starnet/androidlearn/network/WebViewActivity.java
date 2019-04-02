package com.starnet.androidlearn.network;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.starnet.androidlearn.R;
import com.starnet.androidlearn.util.MainThread;

public class WebViewActivity extends Activity {
    private static final String TAG = WebViewActivity.class.getSimpleName();
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview_activity_layout);
        mWebView = (WebView) findViewById(R.id.webView);

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url); // 根据传入的参数再去加载新的网页
                return true; // 表示当前WebView可以处理打开新网页的请求，不用借助
            }
        });


        MainThread.getInstance().postDelayed(new Runnable() {
            @Override
            public void run() {
                mWebView.loadUrl("http://www.baidu.com"); //React Native Flutter
            }
        }, 0);

    }

}
