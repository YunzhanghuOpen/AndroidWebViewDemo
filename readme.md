![yun](https://www.yunzhanghu.com/img/logo.png)

# Android WebView 接入云账户

1. library 中 YZHWebViewClient 会拦截 HTTP 请求找到我们的自定义URL。
1. sample 中演示了接入云账户的几种不同场景、WebView的相关设置、以及如何使用YZHWebViewClient中的回调方法。

## 使用

接入方App实名认证后回调方法的使用:

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

## 引用library到你的项目


library是一个Android library 项目，你可以将它作为一个库引入到你的Eclipse或者Android Studio。

Android Studio中还需要进行如下配置

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










