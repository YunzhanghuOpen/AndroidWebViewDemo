package com.yunzhanghu.androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class JSActivity extends Activity {

    private WebView mWebView;

    public static final String JSDEMO_URL = "https://test.yunzhanghu.com/js-native-connect-test.html";

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        mWebView = (WebView) findViewById(R.id.js_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        mWebView.setWebViewClient(new CustomWebViewClient(this) {
            @Override
            public void returnAuth(WebView view, String url) {
                Log.d("JSActivity", "return Auth" + url);
                //TODO 商户业务逻辑
            }
        });
        mWebView.loadUrl(JSDEMO_URL);
    }
}
