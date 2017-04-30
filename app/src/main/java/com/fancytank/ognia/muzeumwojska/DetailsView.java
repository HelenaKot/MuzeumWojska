package com.fancytank.ognia.muzeumwojska;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

import com.fancytank.ognia.muzeumwojska.api.model.DisplayParagraph;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.details.ParagraphView;
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

        data = (DisplayUnit) getIntent().getExtras().getSerializable(DisplayListAdapter.TAG);
        filldata(data);
    }

    private void filldata(DisplayUnit data) {
        for (DisplayParagraph dataItem : data.getDesc()) {
            ParagraphView pv = new ParagraphView(this);
            pv.setData(this, dataItem);
            container.addView(pv);
        }
    }

    public void showLocalization(View view) {
        loadMapwithAdr("52.182242,21.065728");
    }

    private void loadMapwithAdr(String addrs) {
        webView.setVisibility(View.VISIBLE);
        final String url = "http://maps.google.com/maps";
        webView.setWebViewClient(new WebViewClient());
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("http://maps.google.com/maps?q=" + addrs);
    }
}
