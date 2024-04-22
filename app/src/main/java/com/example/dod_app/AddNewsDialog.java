package com.example.dod_app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class AddNewsDialog extends Dialog {

    private EditText titleEditText, contentEditText, imageUrlEditText;
    private OnAddNewsListener listener;

    public interface OnAddNewsListener {
        void onAddNews(String title, String content, String imageUrl);
    }

    public AddNewsDialog(@NonNull Context context) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_news);
        setTitle("Добавить новость");
        setCancelable(true);

        // Находим элементы в макете диалогового окна
        titleEditText = findViewById(R.id.titleEditText);
        contentEditText = findViewById(R.id.contentEditText);
        imageUrlEditText = findViewById(R.id.imageUrlEditText);
        Button addButton = findViewById(R.id.addNewsButton);

        // Обработчик нажатия кнопки "Добавить"
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Получаем данные из полей ввода
                String title = titleEditText.getText().toString().trim();
                String content = contentEditText.getText().toString().trim();
                String imageUrl = imageUrlEditText.getText().toString().trim();

                // Проверяем, чтобы все поля были заполнены
                if (!title.isEmpty() && !content.isEmpty() && !imageUrl.isEmpty()) {
                    // Передаем данные в активити через слушателя
                    if (listener != null) {
                        listener.onAddNews(title, content, imageUrl);
                    }
                    // Закрываем диалоговое окно
                    dismiss();
                } else {
                    // Если не все поля заполнены, выдаем сообщение
                    // (можно также добавить визуальное выделение незаполненных полей)
                    // Например:
                    // titleEditText.setError("Введите заголовок");
                    // contentEditText.setError("Введите содержание");
                    // imageUrlEditText.setError("Введите URL изображения");
                }
            }
        });
    }
}
