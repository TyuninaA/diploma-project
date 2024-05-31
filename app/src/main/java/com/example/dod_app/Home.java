package com.example.dod_app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Home extends Fragment {

    private RecyclerView recyclerView;
    private List<News> newsList;
    private NewsAdapter newsAdapter;
    private Button addNewsButton;
    private ProgressBar progressBar;
    private String userType;
    private SwipeRefreshLayout swipeRefreshLayout;
    private static final int ADD_NEWS_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsList = new ArrayList<>();
        newsAdapter = new NewsAdapter(getContext(), newsList);
        recyclerView.setAdapter(newsAdapter);

        addNewsButton = view.findViewById(R.id.addNewsButton);
        progressBar = view.findViewById(R.id.progress);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        userType = getActivity().getIntent().getStringExtra("userType");

        if ("admin".equals(userType)) {
            addNewsButton.setVisibility(View.VISIBLE);
            addNewsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAddNewsDialog();
                }
            });
        } else {
            addNewsButton.setVisibility(View.GONE);
        }

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Clear the existing news list
                newsList.clear();
                // Fetch the news from the server again
                fetchNews();
                // Stop the refreshing animation
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        fetchNews();
        return view;
    }

    private void showAddNewsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Add News");

        // Inflate the layout for the dialog
        View dialogView = getLayoutInflater().inflate(R.layout.dialog_add_news, null);
        final EditText titleInput = dialogView.findViewById(R.id.titleEditText);
        final EditText textInput = dialogView.findViewById(R.id.textEditText);
        final EditText imageUrlInput = dialogView.findViewById(R.id.imageUrlEditText);

        builder.setView(dialogView);

        // Set up the buttons
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String title = titleInput.getText().toString();
                String text = textInput.getText().toString();
                String imageUrl = imageUrlInput.getText().toString();
                sendNewsToServer(title, text, imageUrl);
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        builder.show();
    }

    private void fetchNews() {
        String url = "http://192.168.6.69//LoginRegister/get_news.php";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        newsList.clear();
                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject jsonObject = response.getJSONObject(i);
                                String title = jsonObject.getString("title");
                                String text = jsonObject.getString("text");
                                String imageUrl = jsonObject.getString("image_url");
                                // Вставляем новую новость в начало списка
                                News news = new News(title, text, imageUrl);
                                newsList.add(0, news);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        newsAdapter.notifyDataSetChanged();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Error fetching news", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Volley.newRequestQueue(getContext()).add(jsonArrayRequest);
    }


    private void sendNewsToServer(final String title, final String text, final String imageUrl) {
        String url = "http://192.168.6.69//LoginRegister/add_news.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Создаем новый объект новости
                        News news = new News(title, text, imageUrl);
                        // Добавляем новую новость в начало списка
                        newsList.add(0, news);
                        // Уведомляем адаптер о вставке новой новости в список
                        newsAdapter.notifyItemInserted(0);
                        Toast.makeText(getContext(), "News added successfully", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(), "Failed to add news", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("title", title);
                params.put("text", text);
                params.put("image_url", imageUrl);
                return params;
            }
        };

        Volley.newRequestQueue(getContext()).add(stringRequest);
    }

}
