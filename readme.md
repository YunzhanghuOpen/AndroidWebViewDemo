![yun](https://www.yunzhanghu.com/img/logo.png)

# Android WebView 接入云账户

1. YZHLibrary 中 YZHWebViewClient 会收到来自*金融（云账户H5应用）的通知，并根据通知内容做不同的处理。
1. sample 中演示了接入方法，其中L包含：
    * WebView 的设置
    * 模拟接收到云账户通知
    * 处理通知

## 使用

商户方根据自己的需要填写业务代码

```java
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
});
webView.loadUrl(Constant.JS_TEST_URL);
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










