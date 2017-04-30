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
        loadMapwithAdr();
        filldata(data);
    }

    private void loadMapwithAdr() {

        final String url = "http://maps.google.com/maps";

        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://maps.google.com/maps?q=" + "52.1819961,21.0645194");
    }

    private void filldata(DisplayUnit data) {

    }
}
