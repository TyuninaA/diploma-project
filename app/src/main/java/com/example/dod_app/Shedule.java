package com.example.dod_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

public class Shedule extends Fragment {

    private WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_shedule, container, false);

        // Найдем WebView в макете
        webView = view.findViewById(R.id.webView);

        // Включим JavaScript (если это необходимо)
        webView.getSettings().setJavaScriptEnabled(true);

        // Загрузим страницу в WebView
        webView.loadUrl("https://timetable.dku.kz");

        // Укажем WebViewClient, чтобы страницы загружались внутри WebView, а не в стандартном браузере
        webView.setWebViewClient(new WebViewClient());

        return view;
    }
}
