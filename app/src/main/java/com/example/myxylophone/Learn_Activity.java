package com.example.myxylophone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class Learn_Activity extends AppCompatActivity {

    WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_);

        if (isNetworkAvailable(Learn_Activity.this)) {

            String query = "how to play the xylophone";
            String url = "https://www.google.com/search?q="+query;
            mWebView=findViewById(R.id.web);
           mWebView.setWebViewClient(new WebViewClient());
            mWebView.getSettings().setLoadsImagesAutomatically(true);
            mWebView.getSettings().setJavaScriptEnabled(true);
            mWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
            mWebView.loadUrl(url);
        }
        else
            Toast.makeText(this, "Please Check Your Internet Connection", Toast.LENGTH_SHORT).show();

    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }


    public static boolean isNetworkAvailable(Context context){

        if (context==null) return false;

        ConnectivityManager connectivityManager=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager!=null){

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){

                NetworkCapabilities capabilities=connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                if (capabilities!=null){
                    if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)){
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                        return true;
                    } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)){
                        return true;
                    }
                }
            }

            else {

                try {
                    NetworkInfo activeNetworkInfo=connectivityManager.getActiveNetworkInfo();
                    if (activeNetworkInfo!=null && activeNetworkInfo.isConnected()){
                        Log.i("update_status","Network is available : true");
                        return true;
                    }
                } catch (Exception e){
                    Log.i("update_status",""+e.getMessage());
                }
            }
        }
        Log.i("update_status","Network is available : FALSE");
        return false;
    }
}
