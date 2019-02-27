package com.safeshop.safeshopindia.safeshop;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.vlonjatg.progressactivity.ProgressActivity;

public class AllWebView extends AppCompatActivity {
    WebView myWebView;
    ProgressActivity progressActivity;
    protected static String url4load;


    public static void LoadWebView(Context mContext, String loadURL) {
        url4load = loadURL;
        Intent cv = new Intent(mContext, AllWebView.class);
        mContext.startActivity(cv);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_web_view);
        myWebView = (WebView) findViewById(R.id.allwebview);
        progressActivity = (ProgressActivity) findViewById(R.id.activity_follow_web_view);
        progressActivity.showLoading();

        myWebView.setWebViewClient(new WebViewClient() {

            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

                myWebView.loadUrl("file:///android_asset/error.html");

            }

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                myWebView.loadUrl("javascript:(function() { " +
                        "document.getElementsByClassName('header')[0].style.display='none';document.getElementsByClassName('footer')[0].style.display='none'; })()");
                super.onPageFinished(view, url);
                progressActivity.showContent();
            }
        });
        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        myWebView.loadUrl(url4load);

    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && myWebView.canGoBack()) {
            myWebView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

