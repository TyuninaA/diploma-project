package com.example.dod_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewsDetailDialogFragment extends DialogFragment {

    private static final String ARG_TITLE = "arg_title";
    private static final String ARG_CONTENT = "arg_content";
    private static final String ARG_IMAGE_URL = "arg_image_url";

    public static NewsDetailDialogFragment newInstance(String title, String content, String imageUrl) {
        Bundle args = new Bundle();
        args.putString(ARG_TITLE, title);
        args.putString(ARG_CONTENT, content);
        args.putString(ARG_IMAGE_URL, imageUrl);
        NewsDetailDialogFragment fragment = new NewsDetailDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Получаем данные о новости из аргументов
        Bundle args = getArguments();
        if (args != null) {
            String title = args.getString(ARG_TITLE);
            String content = args.getString(ARG_CONTENT);
            String imageUrl = args.getString(ARG_IMAGE_URL);

            // Отображаем информацию о новости в диалоговом окне
            TextView titleTextView = view.findViewById(R.id.titleTextView);
            TextView contentTextView = view.findViewById(R.id.contentTextView);
            ImageView imageView = view.findViewById(R.id.imageView);

            titleTextView.setText(title);
            contentTextView.setText(content);
            // Установите изображение новости в imageView, используя библиотеку Picasso или Glide
        }
    }
}

