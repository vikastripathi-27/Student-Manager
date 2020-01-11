package com.nextlink.studentmanager;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class cllg_website extends AppCompatActivity {
    private WebView mywebView;
    public String url = "https://";
    public String temp;
    ProgressDialog prDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cllg_website);

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid()).child("data");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userProfile userprofile = dataSnapshot.getValue(userProfile.class);
                temp = userprofile.getWebsite_register();
                url = url + temp;
               // Toast.makeText(cllg_website.this,"url = "+url,Toast.LENGTH_SHORT).show();
                mywebView = (WebView) findViewById(R.id.webview);
                mywebView.setWebViewClient(new MyWebViewClient());

                mywebView.getSettings().setJavaScriptEnabled(true);
                mywebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
                mywebView.loadUrl(url);
//                WebSettings webSettings= mywebView.getSettings();
//                webSettings.setJavaScriptEnabled(true);
//                mywebView.loadUrl(url);
                // Line of Code for opening links in app
//                mywebView.setWebViewClient(new WebViewClient());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(cllg_website.this,"Cannot connect to server",Toast.LENGTH_SHORT).show();
            }
        });

//        initWebView();

//        mywebView.loadUrl(url);
    }

    private class MyWebViewClient extends WebViewClient {

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            prDialog = new ProgressDialog(cllg_website.this);
            prDialog.setMessage("Loading your college website ...");
            prDialog.show();
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            if(prDialog!=null){
                prDialog.dismiss();
            }
        }
    }

//    private void initWebView() {
//        final ProgressDialog pd = ProgressDialog.show(cllg_website.this, "", "Loading your college website .....", true);
//
//        mywebView.setWebChromeClient(new MyWebChromeClient(this));
//        mywebView.clearCache(true);
//        mywebView.getSettings().setJavaScriptEnabled(true);
//        mywebView.setHorizontalScrollBarEnabled(false);
//
//        mywebView.setWebViewClient(new WebViewClient() {
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//
//                pd.show();
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                mywebView.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                pd.dismiss();
//            }
//
//            @Override
//            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
//                invalidateOptionsMenu();
//            }
//        });
//        mywebView.clearCache(true);
//        mywebView.clearHistory();
//        mywebView.getSettings().setJavaScriptEnabled(true);
//        mywebView.setHorizontalScrollBarEnabled(false);
//    }
//
//    private class MyWebChromeClient extends WebChromeClient {
//        Context context;
//
//        public MyWebChromeClient(Context context) {
//            super();
//            this.context = context;
//        }
//    }

}
