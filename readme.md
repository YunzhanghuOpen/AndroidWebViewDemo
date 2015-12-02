![yun](https://www.yunzhanghu.com/img/logo.png)

# AndroidWebViewDemo

1. YZHLibrary 中 YZHWebViewClient 会收到来自*金融（云账户H5应用）的通知，并根据通知内容做不同的处理。
1. sample 中演示了接入方法，其中包含：
    * WebView 的设置
    * 模拟接收到云账户通知
    * 处理通知

## 使用

商户方根据自己的业务需要处理通知

```java
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
            String code = requestParams.code;

            // LEVEL::INFO code=0 操作成功
            if (code.equals("0")) {

                //TODO 商户填写，自己的后续业务
                HashMap data = requestParams.data;

                //关闭*金融
                YZHActivity.this.finish();
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
```

## 引用 library 到你的项目

引用 YZHLibrary 到 Android Studio

In your `build.gradle`:

```gradle
dependencies {
    compile project(':library')
}
```

In your `settings.gradle`:

```gradle
include ':library'
```










