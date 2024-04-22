package com.example.dod_app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class NewsDetailDialog extends DialogFragment {

    private String title;
    private String content;
    private String imageUrl;

    public NewsDetailDialog(String title, String content, String imageUrl) {
        this.title = title;
        this.content = content;
        this.imageUrl = imageUrl;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(R.layout.news_detail_dialog);

        Dialog dialog = builder.create();
        dialog.show();

        TextView textViewTitle = dialog.findViewById(R.id.textViewTitle);
        TextView textViewContent = dialog.findViewById(R.id.textViewContent);
        ImageView imageViewNews = dialog.findViewById(R.id.imageViewNews);

        textViewTitle.setText(title);
        textViewContent.setText(content);
        // Установите изображение в imageViewNews используя библиотеку Picasso или Glide

        return dialog;
    }
}

