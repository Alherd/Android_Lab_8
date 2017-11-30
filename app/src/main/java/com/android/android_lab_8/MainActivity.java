package com.android.android_lab_8;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public WebView mWebView;
    EditText mEnterEditText;
    Button mEnterButton;
    ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWebView = (WebView) findViewById(R.id.webView);
        mEnterEditText = (EditText) findViewById(R.id.editText);
        mEnterButton = (Button) findViewById(R.id.button);
        mListView = (ListView) findViewById(R.id.listView);
        final ArrayList<String> webSites = new ArrayList<>();

        final ArrayAdapter<String> adapter;
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, webSites);
        mListView.setAdapter(adapter);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mEnterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a = mEnterEditText.getText().toString();
                mWebView.loadUrl("http://" + a);
                webSites.add(0, mEnterEditText.getText().toString());
                adapter.notifyDataSetChanged();
                mEnterEditText.setText("");
            }
        });

        mWebView.setWebViewClient(new MyWebViewClient());
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
