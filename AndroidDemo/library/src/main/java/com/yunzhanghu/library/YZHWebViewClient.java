package com.yunzhanghu.library;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

/**
 * Created by max on 15/11/25.
 * 自定义WebViewClient,通过识别自定义http协议截获Ajax请求,
 * 实现WebView与JavaScript的交互.
 */
public class YZHWebViewClient extends WebViewClient {

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
        //通过识别自定义的协议来截获请求
        if (url.contains("/app")) {
            Uri uri = Uri.parse(PARSE_PARAMS_TEST_URL);
            returnAuth(view, parseParams(uri));
            return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream(RESPONSE_MSG.getBytes(StandardCharsets.UTF_8)));
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
        //通过识别自定义的协议来截获请求
        if (uri.getPath().equals("/app")) {
            Uri uri1 = Uri.parse(PARSE_PARAMS_TEST_URL);
            returnAuth(view, parseParams(uri1));
            return new WebResourceResponse("text/plain", "utf-8", new ByteArrayInputStream(RESPONSE_MSG.getBytes(StandardCharsets.UTF_8)));
        }
        return null;
    }

    /**
     * 解析请求中携带的参数
     *
     * @param uri 请求资源的Uri
     * @return Params
     */
    private RequestParams parseParams(Uri uri) {
        //TODO 待TEST_URL中data修改为JSON格式后使用JSON解析
        RequestParams requestParams = new RequestParams();
        String code = uri.getQueryParameter("code");
        String result = uri.getQueryParameter("result");
        String realName = uri.getQueryParameter("realname");
        String cardno = uri.getQueryParameter("cardno");
        HashMap<String, String> data = new HashMap<String, String>();
        data.put("realname", realName);
        data.put("cardno", cardno);
        requestParams.code = code;
        requestParams.msg = result;
        requestParams.data = data;
        return requestParams;
    }

    /**
     * 进行实名认证后截获请求的回调方法,商户App可以在该方法内实现自己的业务逻辑.
     * 注意：该方法是在非UI线程中调用的.
     *
     * @param view          请求资源的WebView.
     * @param requestParams 被截获请求中返回的参数,详见RequestParams类.
     */
    public void returnAuth(WebView view, RequestParams requestParams) {
    }

    /**
     * 绑定银行卡后截获请求的回调方法,商户App可以在该方法内实现自己的业务逻辑.
     * 注意：该方法是在非UI线程中调用的.
     *
     * @param view          请求资源的WebView.
     * @param requestParams 被截获请求中返回的参数,详见RequestParams类.
     */
    public void returnBankcard(WebView view, RequestParams requestParams) {
    }

    /**
     * 投资后截获请求的回调方法,商户App可以在该方法内实现自己的业务逻辑.
     * 注意：该方法是在非UI线程中调用的.
     *
     * @param view          请求资源的WebView.
     * @param requestParams 被截获请求中返回的参数,详见RequestParams类.
     */
    public void returnInvest(WebView view, RequestParams requestParams) {
    }

    public final static String RETURN_AUTH_ACTION = "returnAuth";

    public final static String RETURN_BANKCARD_ACTION = "returnBankcard";

    public final static String URI_SCHEME = "yzh";

    public final static String RESPONSE_MSG = "app";

    public final static String PARSE_PARAMS_TEST_URL = "http://yunzhanghu.com/app/action?code=0&realname=zhangsan&cardno=341226198902121355&result=1";


}
