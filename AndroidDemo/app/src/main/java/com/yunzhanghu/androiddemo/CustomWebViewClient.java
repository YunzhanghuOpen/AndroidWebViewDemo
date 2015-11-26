package com.yunzhanghu.androiddemo;

import android.content.Context;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by max on 15/11/25.
 * 自定义WebViewClient
 */
public class CustomWebViewClient extends WebViewClient {


    public CustomWebViewClient() {
    }

    public CustomWebViewClient(Context context) {
        mContext = context;
    }

    public CustomWebViewClient(Context context, boolean shouldOverrideUrlLoading) {
        mContext = context;
        mShouldOverrideUrlLoading = shouldOverrideUrlLoading;
    }
    /**
     * Give the host application a chance to take over the control when a new
     * url is about to be loaded in the current WebView. If WebViewClient is not
     * provided, by default WebView will ask Activity Manager to choose the
     * proper handler for the url. If WebViewClient is provided, return true
     * means the host application handles the url, while return false means the
     * current WebView handles the url.
     * This method is not called for requests using the POST "method".
     *
     * @param view The WebView that is initiating the callback.
     * @param url The url to be loaded.
     * @return True if the host application wants to leave the current WebView
     *         and handle the url itself, otherwise return false.
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        overrideUrlLoading(view, url);
        String scheme = Uri.parse(url).getScheme();
        if (scheme == null) {
            throw new NullPointerException("scheme");
        }
        if (scheme.equals(URI_SCHEME)) {
            String host = Uri.parse(url).getHost();
            if (host == null) {
                throw new NullPointerException("host");
            }
            if (host.equals(RETURN_AUTH_ACTION)) {
                returnAuth(view, url);
            }
            if (host.equals(RETURN_BANKCARD_ACTION)) {
                returnBankcard(view, url);
            }
        }
        return mShouldOverrideUrlLoading;
    }

    /**
     * 通知应用程序WebView将要返回授权页面
     *
     * @param view 触发回调的WebView.
     * @param url  WebView将要加载的url.
     */
    public void returnAuth(WebView view, String url) {
    }

    /**
     * 通知应用程序WebView将要返回绑定银行卡页面
     *
     * @param view 触发回调的WebView.
     * @param url  WebView将要加载的url.
     */
    public void returnBankcard(WebView view, String url) {
    }

    /**
     * 该方法用于在shouldOverrideUrlLoading方法中增加其他业务逻辑
     *
     * @param view 触发回调的WebView.
     * @param url  WebView将要加载的url.
     */
    public void overrideUrlLoading(WebView view, String url) {
    }

    private Context mContext;

    private boolean mShouldOverrideUrlLoading = true;

    public final static String RETURN_AUTH_ACTION = "returnAuth";

    public final static String RETURN_BANKCARD_ACTION = "returnBankcard";

    public final static String URI_SCHEME = "yzh";


}
