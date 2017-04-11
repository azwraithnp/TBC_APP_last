package com.example.b50i7d.tbcapp;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by B50i7D on 11/29/2016.
 */
public class HomeActivity extends Fragment {

    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v;
        String url = "https://ebookzweb.wordpress.com/";
        if (!isConnected(getContext())){
            //you can alert the user if there is no internet connection
            //buildDialog(getActivity()).show();
            v= inflater.inflate(R.layout.nointernet_activity, container, false);
        }
        else {
            //Toast.makeText(getContext(), "connected", Toast.LENGTH_SHORT).show();
            v = inflater.inflate(R.layout.fragment_settings, container, false);
            WebView view = (WebView) v.findViewById(R.id.webview1);
            view.getSettings().setJavaScriptEnabled(true);
            view.loadUrl(url);
            view.setWebViewClient(new WebViewClient());
            view.getSettings().setJavaScriptEnabled(true);

        }

        return v;
    }


    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
            else return false;
        } else
            return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        return builder;
    }
    @Override
    public void onPause() {
        super.onPause();
        if (getActivity().getSupportFragmentManager().findFragmentByTag("MyFragment") != null)
            getActivity().getSupportFragmentManager().findFragmentByTag("MyFragment").setRetainInstance(true);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getActivity().getSupportFragmentManager().findFragmentByTag("MyFragment") != null)
            getActivity().getSupportFragmentManager().findFragmentByTag("MyFragment").getRetainInstance();
    }

}

