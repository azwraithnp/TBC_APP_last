package com.example.b50i7d.tbcapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class CollegeActivity extends Activity {

    private ProgressDialog progress;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.college_activity);

        WebView webView = (WebView) findViewById(R.id.webview2);
        webView.setInitialScale(0);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setLoadWithOverviewMode(true);
        webView.getSettings().setUseWideViewPort(true);
        //webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
       // webView.setScrollbarFadingEnabled(false);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("http://www.thebritishcollege.edu.np/pages/about-tbc/0/1");

        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                if (newProgress > 0) {
                    showProgressDialog("Please Wait");
                }
                if (newProgress >= 30) {
                    hideProgressDialog();
                }
            }
        });

    }

    public void showProgressDialog(final String msg) {

        runOnUiThread(new Runnable() {
            public void run() {
                if (progress == null || !progress.isShowing()) {
                    try{}catch(Exception e){
                        progress = ProgressDialog.show(CollegeActivity.this, "", msg);
                    }

                }
            }
        });
    }

    public void hideProgressDialog() {
        runOnUiThread(new Runnable() {

            @Override
            public void run() {
                try {
                    if (progress.isShowing())
                        progress.dismiss();
                } catch (Throwable e) {

                }
            }
        });
    }

}