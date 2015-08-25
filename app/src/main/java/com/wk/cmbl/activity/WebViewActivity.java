package com.wk.cmbl.activity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.wk.cmbl.R;
import com.wk.cmbl.base.BaseActivity;

import org.kymjs.kjframe.ui.BindView;

/**
 * Created by Administrator on 2015/8/25 0025.
 */
public class WebViewActivity extends BaseActivity {
    @BindView(id = R.id.webview)
    private WebView mWebView;

    private ProgressDialog progressDialog;

    private String title = "订单更新提醒";
    private String weburl = "http://www.baidu.com";

    @Override
    public void setRootView() {
        super.setRootView();
        setMyContextView(R.layout.view_webview);
    }

    @Override
    public void initWidget() {
        super.initWidget();

        initHeader();
        initBodyer();
    }

    @Override
    protected void initHeader() {
        setHeaderTitle(title);
    }

    @Override
    protected void initBodyer() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(weburl);
        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        public MyWebViewClient() {

        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {// 网页页面开始加载的时候
            if (progressDialog == null) {
                progressDialog = new ProgressDialog(WebViewActivity.this);
                progressDialog.setMessage("数据加载中，请稍后。。。");
                progressDialog.show();
                mWebView.setEnabled(false);// 当加载网页的时候将网页进行隐藏
            }
            super.onPageStarted(view, url, favicon);
        }

        @Override
        public void onPageFinished(WebView view, String url) {// 网页加载结束的时候
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
                progressDialog = null;
                mWebView.setEnabled(true);
            }
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) { // 网页加载时的连接的网址
            view.loadUrl(url);
            return true;
        }
    }
}
