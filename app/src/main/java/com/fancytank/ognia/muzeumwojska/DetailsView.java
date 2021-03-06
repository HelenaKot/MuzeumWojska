package com.fancytank.ognia.muzeumwojska;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.fancytank.ognia.muzeumwojska.api.model.DisplayParagraph;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.details.ParagraphView;
import com.fancytank.ognia.muzeumwojska.list.DisplayListAdapter;

public class DetailsView extends AppCompatActivity {
    LinearLayout container;
    DisplayUnit data;
    WebView webView;
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_view);
        container = (LinearLayout) findViewById(R.id.container);
        webView = (WebView) findViewById(R.id.web_view);
        scrollView = (ScrollView) findViewById(R.id.scroll_view);

        data = (DisplayUnit) getIntent().getExtras().getSerializable(DisplayListAdapter.TAG);
        if (data != null) {
            setTitle(data.getTitle());
            filldata(data);
        }
        webView.setVisibility(View.GONE);
    }

    private void filldata(DisplayUnit data) {
        for (DisplayParagraph dataItem : data.getDesc()) {
            ParagraphView pv = new ParagraphView(this);
            pv.setData(this, dataItem);
            container.addView(pv);
        }
        if (data.coordinates != null)
            loadMapwithAdr(data.coordinates);
    }

    public void showLocalization(View view) {
        webView.setVisibility(View.VISIBLE);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void loadMapwithAdr(String addrs) {
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://maps.google.com/maps?q=" + addrs);
    }
}
