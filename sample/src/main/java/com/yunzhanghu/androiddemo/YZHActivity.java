package com.yunzhanghu.androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.yunzhanghu.library.RequestParams;
import com.yunzhanghu.library.YZHWebViewClient;


/**
 * Created by max on 15/11/24.
 */
public class YZHActivity extends Activity {

    private WebView mWebView;

    private final static String LOG_TAG = YZHActivity.class.getSimpleName();

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yzh);
        findViewById(R.id.btn_closed).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        mWebView = (WebView) findViewById(R.id.yzh_web_view);
        //设置支持Dom存储
        mWebView.getSettings().setDomStorageEnabled(true);
        //设置支持JavaScript
        mWebView.getSettings().setJavaScriptEnabled(true);
        //设置WebView缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        mWebView.setWebViewClient(new YZHWebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //保证使用应用内的WebView打开 而不是使用外部浏览器 返回值为true
                view.loadUrl(url);
                return true;
            }

            @Override
            public void returnAuth(WebView view, RequestParams requestParams) {
                //TODO 完成实名认证后 商户App的业务逻辑 注意：该方法运行在非UI线程
                Log.d(LOG_TAG, requestParams.toString());
            }
        });
        mWebView.loadUrl(Constant.YZH_TEST_URL);
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