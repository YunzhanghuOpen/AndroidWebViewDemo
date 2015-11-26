package com.yunzhanghu.androiddemo;

import android.annotation.TargetApi;
import android.app.Activity;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

/**
 * Created by max on 15/11/25.
 * 自定义WebViewClient
 */
public class CustomWebViewClient extends WebViewClient {
    /**
     * 构造方法
     *
     * @param activity WebView所在的Activity实例
     */
    public CustomWebViewClient(Activity activity) {
        mActivity = activity;
    }


    /**
     * Notify the host application of a resource request and allow the
     * application to return the data.  If the return value is null, the WebView
     * will continue to load the resource as usual.  Otherwise, the return
     * response and data will be used.
     * <p/>
     * NOTE: This method is called on a thread other than the UI thread
     * so clients should exercise caution
     * when accessing private data or the view system.
     *
     * @param view The {@link android.webkit.WebView} that is requesting the
     *             resource.
     * @param url  The raw url of the resource.
     * @return A {@link android.webkit.WebResourceResponse} containing the
     * response information or null if the WebView should load the
     * resource itself.
     * @deprecated Use {@link #shouldInterceptRequest(WebView, WebResourceRequest)
     * shouldInterceptRequest(WebView, WebResourceRequest)} instead.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT) //API 19及以下会调用这个方法
    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        String url1 = "http://yunzhanghu.com/app/action?code=2&realname=zhangsan&cardno=341226198902121355&result=1";
        Uri uri1 = Uri.parse(url1);
        String code = uri1.getQueryParameter("code");

        Log.d("hello", "url :" + uri1.toString() + "\n" + uri1.getPath() + "\n" + uri1.getQuery());
        if (url.contains("app")) {
            if (!TextUtils.isEmpty(code) && code.equals("2")) {
                //强制关闭WebView所在页面
                if (mActivity != null) {
                    mActivity.finish();
                    return null;
                }
            }
            String exampleString = "true";
            returnAuth(view, url);
            return new WebResourceResponse("text/json", "utf-8", new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8)));
        }
        return null;
    }

    /**
     * Notify the host application of a resource request and allow the
     * application to return the data.  If the return value is null, the WebView
     * will continue to load the resource as usual.  Otherwise, the return
     * response and data will be used.
     * <p/>
     * NOTE: This method is called on a thread other than the UI thread
     * so clients should exercise caution
     * when accessing private data or the view system.
     *
     * @param view    The {@link android.webkit.WebView} that is requesting the
     *                resource.
     * @param request Object containing the details of the request.
     * @return A {@link android.webkit.WebResourceResponse} containing the
     * response information or null if the WebView should load the
     * resource itself.
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP) //API 21及以上会调用该方法

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {
        Uri uri = request.getUrl();
        Log.d("hello", "uri :" + uri.toString() + "\n" + uri.getPath() + "\n" + uri.getQuery());
        String url = "http://yunzhanghu.com/app/action?code=0&realname=zhangsan&cardno=341226198902121355&result=1";
        Uri uri1 = Uri.parse(url);
        Log.d("hello", "url :" + uri1.toString() + "\n" + uri1.getPath() + "\n" + uri1.getAuthority() + "\n" + uri1.getHost() + "\n" + uri1.getQuery());
        if (uri.getPath().equals("/app")) {
            String exampleString = "ok";
            returnAuth(view, request.getUrl().toString());
            return new WebResourceResponse("text/json", "utf-8", new ByteArrayInputStream(exampleString.getBytes(StandardCharsets.UTF_8)));
        }
        return null;
    }

    /**
     * 通知应用程序接收实名认证信息,可以通过解析url获取参数.
     * <p/>
     * 例如：String url = "http://yunzhanghu.com/app/action?code=0&realname=zhangsan&cardno=341226198902121355&result=1".
     * Uri.parse(url).getQuery()将返回code=0&realname=zhangsan&cardno=341226198902121355&result=1.
     * <p/>
     * 注意：该方法是在子线程中调用的.
     *
     * @param view 请求资源的WebView.
     * @param url  请求资源的原始url.
     */
    public void returnAuth(WebView view, String url) {
    }

    /**
     * 通知应用程序WebView将要返回绑定银行卡页面.
     *
     * @param view 请求资源的WebView.
     * @param url  请求资源的原始url.
     */
    public void returnBankcard(WebView view, String url) {
    }

    /**
     * 通知应用程序WebView将要返回绑定银行卡页面.
     *
     * @param view 请求资源的WebView.
     * @param url  请求资源的原始url.
     */
    public void returnInvest(WebView view, String url) {
    }

    private Activity mActivity;


    public final static String RETURN_AUTH_ACTION = "returnAuth";

    public final static String RETURN_BANKCARD_ACTION = "returnBankcard";

    public final static String URI_SCHEME = "yzh";

}
