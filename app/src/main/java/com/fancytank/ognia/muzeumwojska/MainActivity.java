package com.fancytank.ognia.muzeumwojska;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.Toast;

import com.fancytank.ognia.muzeumwojska.api.MyFilterService;
import com.fancytank.ognia.muzeumwojska.api.model.Category;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.list.DisplayListAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    RecyclerView mainList;
    DisplayListAdapter adapter;
    ScrollView scrollView;
    private boolean inited;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        final Activity context = this;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new IntentIntegrator(context).initiateScan();
            }
        });
        scrollView = (ScrollView) findViewById(R.id.scroll_view);
        initAdapter();
    }

    private void initAdapter() {
        mainList = (RecyclerView) findViewById(R.id.display_items);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayListAdapter(this);
        mainList.setAdapter(adapter);
    }

    private void fillWithData() {
        DisplayUnit[] array = {new DisplayUnit("zz_123", "Lorem Ipsum", Category.TANK),
                new DisplayUnit("zz_124", "Lorem Ipsum", Category.BTR),
                new DisplayUnit("zz_124", "Lorem Ipsum", Category.BTR),
                new DisplayUnit("zz_127", "Lorem Ipsum Dolor", Category.PLANE),
                new DisplayUnit("zz_127", "Lorem Ipsum", Category.PLANE),
                new DisplayUnit("zz_125", "Lorem Ipsum Sit Amet", Category.SATELLITE),
                new DisplayUnit("zz_126", "Lorem Ipsum", Category.LAUNCHER),
                new DisplayUnit("zz_127", "Lorem Ipsum", Category.PISTOL)
        };


        adapter.getData().addAll(Arrays.asList(array));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            MyFilterService.main();
            if (!inited)
                loadAfter(300);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadAfter(int amount) {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                fillWithData();
                scrollView.fullScroll(ScrollView.FOCUS_UP);
            }
        }, amount);
        inited = true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d("MainActivity", "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d("MainActivity", "Scanned");
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            // This is important, otherwise the result will not be passed to the fragment
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
