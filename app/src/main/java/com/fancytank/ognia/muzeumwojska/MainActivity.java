package com.fancytank.ognia.muzeumwojska;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.fancytank.ognia.muzeumwojska.api.MyFilterService;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;
import com.fancytank.ognia.muzeumwojska.list.DisplayListAdapter;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    RecyclerView mainList;
    DisplayListAdapter adapter;

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
        initAdapter();
    }

    private void initAdapter() {
        mainList = (RecyclerView) findViewById(R.id.display_items);
        mainList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new DisplayListAdapter(this);
        fillWithData();
        mainList.setAdapter(adapter);
    }

    private void fillWithData() {
        adapter.getData().add(new DisplayUnit("zz_123", "eee"));
        adapter.getData().add(new DisplayUnit("zz_124", "eee"));
        adapter.getData().add(new DisplayUnit("zz_125", "eee"));
        adapter.getData().add(new DisplayUnit("zz_126", "eee"));
        adapter.getData().add(new DisplayUnit("zz_127", "eee"));

        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        try {
            MyFilterService.main();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
