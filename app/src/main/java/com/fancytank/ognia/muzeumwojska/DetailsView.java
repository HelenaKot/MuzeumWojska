package com.fancytank.ognia.muzeumwojska;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.list.DisplayListAdapter;

public class DetailsView extends AppCompatActivity {
    LinearLayout container;
    DisplayUnit data;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);
        container = (LinearLayout) findViewById(R.id.container);
        webView = (WebView) findViewById(R.id.web_view);

        getIntent().getExtras().getSerializable(DisplayListAdapter.TAG);
//        loadMiniMapWithAdr("52.1819961,21.0645194");
        loadMapwithAdr("52.182242,21.065728");
        filldata(data);
    }

    private void loadMapwithAdr(String addrs) {

        final String url = "http://maps.google.com/maps";

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://maps.google.com/maps?q=" + addrs);
    }

    private void loadMiniMapWithAdr(String addrs) {
        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageFinished(final WebView view, final String url) {
                super.onPageFinished(view, url);
                resizeWebView();
                webView.invalidate();
            }
        });

        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("https://maps.googlemaps.com/maps/api/staticmap?maptype=satellite&center=" + addrs + "&zoom=18" + "&size=100x100");
        webView.requestLayout();
        webView.invalidate();
    }

    private void resizeWebView() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                int size = webView.getLayoutParams().height;
                webView.setLayoutParams(new LinearLayout.LayoutParams(size, size));
            }
        });
    }

    private void filldata(DisplayUnit data) {

    }
}
