package com.example.dod_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private NewsAdapter newsAdapter;
    private List<news> newsList;
    private Button addNewsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        // Получение списка новостей с сервера
        getNewsFromServer();

        // Находим кнопку "Добавить новость" в макете
        addNewsButton = view.findViewById(R.id.addNewsButton);

        // Получаем тип пользователя из MainActivity
        String userType = getActivity().getIntent().getStringExtra("userType");

        // Проверяем, является ли пользователь администратором, и отображаем кнопку "Добавить новость" при необходимости
        if (userType != null && userType.equals("admin")) {
            // Отображаем кнопку "Добавить новость"
            addNewsButton.setVisibility(View.VISIBLE);
        } else {
            // Скрываем кнопку "Добавить новость"
            addNewsButton.setVisibility(View.GONE);
        }

        // Обработчик нажатия кнопки "Добавить новость"
        addNewsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Открыть диалоговое окно добавления новости
                AddNewsDialog addNewsDialog = new AddNewsDialog(getActivity());
                addNewsDialog.show();
            }
        });
    }

    private void getNewsFromServer() {
        newsList = new ArrayList<>();

        // URL для запроса новостей с сервера
        String url = "http://192.168.0.123//LoginRegister/get_news.php";

        // Создание запроса с помощью Volley
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            // Обработка ответа сервера
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String content = jsonObject.getString("content");
                                String imageUrl = jsonObject.getString("image_url");

                                // Создание объекта новости и добавление его в список
                                newsList.add(new news(title, content, imageUrl));
                            }

                            // Установка адаптера для RecyclerView
                            newsAdapter = new NewsAdapter(getActivity(), newsList);
                            recyclerView.setAdapter(newsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Обработка ошибки при получении новостей с сервера
                        Toast.makeText(getActivity(), "Error fetching news: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // Добавление запроса в очередь Volley
        Volley.newRequestQueue(getActivity()).add(jsonArrayRequest);
    }
}