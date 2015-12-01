package com.yunzhanghu.androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.yunzhanghu.library.YZHWebViewClient;
import com.yunzhanghu.library.RequestParams;

public class JSActivity extends Activity {

    private final static String LOG_TAG = JSActivity.class.getSimpleName();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        findViewById(R.id.btn_closed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        WebView webView = (WebView) findViewById(R.id.js_web_view);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        webView.setWebViewClient(new YZHWebViewClient() {
            //商户App可以根据不同的入口url（即Constant.JS_TEST_URL)）选择实现不同的回调方法来实现自己的业务逻辑
            @Override
            public void returnAuth(WebView view, RequestParams requestParams) {
                Log.d(LOG_TAG, requestParams.toString());
                //TODO 完成实名认证后 商户App的业务逻辑 注意：该方法运行在非UI线程
            }

            @Override
            public void returnBankcard(WebView view, RequestParams requestParams) {
                Log.d(LOG_TAG, requestParams.toString());
                //TODO 完成绑卡后 商户App的业务逻辑 注意：该方法运行在非UI线程
            }

            @Override
            public void returnInvest(WebView view, RequestParams requestParams) {
                Log.d(LOG_TAG, requestParams.toString());
                //TODO 完成投资后 商户App的业务逻辑 注意：该方法运行在非UI线程
            }
        });
        Log.d(LOG_TAG, Constant.JS_TEST_URL);
        webView.loadUrl(Constant.JS_TEST_URL);
    }
}
