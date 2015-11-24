package com.yunzhanghu.androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by max on 15/11/24.
 */
public class WebViewActivity extends Activity {

    private WebView mWebView;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        findViewById(R.id.btn_closed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebView = (WebView) findViewById(R.id.web_view);
        //设置支持Dom存储
        mWebView.getSettings().setDomStorageEnabled(true);
        //设置支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //清除WebView缓存
        mWebView.clearCache(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //保证使用应用内的WebView打开 而不是使用外部浏览器 返回值为true
                view.loadUrl(url);
                return true;
            }
        });
        mWebView.loadUrl("https://test.yunzhanghu.com/#/app/logout");
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            finish();
        }
    }
}
