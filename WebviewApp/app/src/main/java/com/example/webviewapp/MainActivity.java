package com.example.webviewapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.window.OnBackInvokedDispatcher;

public class MainActivity extends AppCompatActivity {

    WebView clkWebview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        clkWebview = findViewById(R.id.webViewClock);

        WebSettings netSettings = clkWebview.getSettings();
        netSettings.setBuiltInZoomControls(true);
        netSettings.setJavaScriptEnabled(true);
        clkWebview.setWebViewClient(new Callback());
        clkWebview.loadUrl("file:////android_asset/index.html");

    }

    private class Callback extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return false; // super.shouldOverrideUrlLoading(view, request);
        }
    }

    @Override
    public void onBackPressed() {
        if (clkWebview != null && clkWebview.canGoBack()) {
            clkWebview.goBack();
        } else {
            super.onBackPressed();
        }
    }
}