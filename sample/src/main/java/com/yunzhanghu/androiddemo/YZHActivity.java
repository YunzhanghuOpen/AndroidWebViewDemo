package com.yunzhanghu.androiddemo;

import android.Manifest;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Toast;

import com.yunzhanghu.library.RequestParams;
import com.yunzhanghu.library.YZHWebViewClient;


/**
 * Created by max on 15/11/24.
 */
public class YZHActivity extends Activity {

    final private int REQUEST_CODE_ASK_PERMISSIONS = 123;

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
        mWebView.setWebViewClient(new YZHWebViewClient(this) {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //保证使用应用内的WebView打开 而不是使用外部浏览器 返回值为true
                view.loadUrl(url);
                if (url.startsWith("tel:")){
                    ringUp(Uri.parse(url));
                    mWebView.goBackOrForward(-1);
                }
                return true;
            }

            @Override
            public void returnAuth(WebView view, RequestParams requestParams) {
                //TODO 完成实名认证后 商户App的业务逻辑 注意：该方法运行在非UI线程
                Log.d(LOG_TAG, requestParams.toString());
                String code = requestParams.code;
                String data = requestParams.jsonStr;
                // LEVEL::INFO code=0 操作成功
                if (code.equals("0")) {
                    //TODO 商户填写，自己的后续业务
                }
                // LEVEL::WARNING code=1 警告
                if (code.equals("1")) {
                    //TODO 商户填写，记录警告内容
                }
                // LEVEL::ERROR code=2 错误
                if (code.equals("2")) {
                    //TODO 商户填写，异常处理
                    //关闭*金融
                    YZHActivity.this.finish();
                }
            }

            @Override
            public void returnBankcard(WebView view, RequestParams requestParams) {

            }
        });
        //实际开发中Constant.YZH_TEST_URL由商户App服务器端生成
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

    private void ringUp(Uri uri) {
        int hasCallPhonePermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE);
        if (hasCallPhonePermission != PackageManager.PERMISSION_GRANTED) {
            if (!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CALL_PHONE)) {
                return;
            }
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CODE_ASK_PERMISSIONS);
            return;
        }
        Intent intent = new Intent(Intent.ACTION_CALL, uri);
        startActivity(intent);
    }

}
