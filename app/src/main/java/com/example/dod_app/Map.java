package com.example.dod_app;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Map extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Надуваем макет фрагмента
        View rootView = inflater.inflate(R.layout.fragment_map, container, false);

        // Находим кнопку в макете
        Button button = rootView.findViewById(R.id.btnPushkin);
        Button button2 = rootView.findViewById(R.id.btnNazarbayev);
        // Устанавливаем слушатель нажатия на кнопку
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем Intent для перехода на другую активность
                Intent intent = new Intent(getActivity(), MapPushkin.class);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Создаем Intent для перехода на другую активность
                Intent intent2 = new Intent(getActivity(), MapNazarbayeva.class);
                startActivity(intent2);
            }
        });

        return rootView;
    }
}
