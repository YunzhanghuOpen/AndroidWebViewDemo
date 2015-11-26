package com.yunzhanghu.androiddemo;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

public class JSActivity extends Activity {

    private WebView mWebView;

    @SuppressLint({"SetJavaScriptEnabled", "JavascriptInterface", "AddJavascriptInterface"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_js);
        mWebView = (WebView) findViewById(R.id.js_web_view);
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setSupportZoom(false);
        //方法一 通过设置WebViewClient实现与JS交互
        mWebView.setWebViewClient(new CustomWebViewClient(this) {
            @Override
            public void returnAuth(WebView view, String url) {
                Log.d("JSActivity", "return Auth" + url);
                //TODO 商户业务逻辑
//                Toast.makeText(JSActivity.this, "button is clicked", Toast.LENGTH_SHORT).show();
            }
        });
//        mWebView.loadUrl("file:///android_asset/demo.html");
        mWebView.loadUrl("http://10.10.1.116:8000/");
        //方法二 通过添加JS接口实现与JS交互
        mWebView.addJavascriptInterface(new AndroidBridge(this), "AndroidBridge");
    }

    private String parseQueryString(String url) {
        return Uri.parse(url).getQuery();
    }

    //方法一
    public class AndroidBridge {

        private Activity activity;

        public AndroidBridge(Activity activity) {
            this.activity = activity;
        }

        @JavascriptInterface//NOTE :必须添加该注解
        public void showMessage() {
            Toast.makeText(activity, "android showMessage is invoked", Toast.LENGTH_SHORT).show();
        }
    }

}
