package com.example.dod_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Robot extends Fragment implements View.OnTouchListener {

    private ImageView joystickHandle;
    private ImageView joystickBase;
    private TextView coordinatesTextView;
    private float centerX, centerY;
    private float lastX, lastY;
    private float initialX, initialY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_robot, container, false);

        joystickHandle = view.findViewById(R.id.joystick_handle);
        joystickBase = view.findViewById(R.id.joystick_base);
        coordinatesTextView = view.findViewById(R.id.coordinates_textview); // Находим TextView для отображения координат
        joystickBase.setOnTouchListener(this);

        // После того как представление отображено на экране, можно получить его размеры
        joystickBase.post(new Runnable() {
            @Override
            public void run() {
                // Вычисляем центр устройства
                centerX = view.getWidth() / 2;
                centerY = view.getHeight() / 2;

                // Сохраняем начальные координаты джойстика
                initialX = joystickHandle.getX();
                initialY = joystickHandle.getY();

                // Устанавливаем начальные координаты в TextView
                updateCoordinatesText(initialX, initialY);
            }
        });

        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                float dy = event.getRawY() - lastY;

                float newX = joystickHandle.getX() + dx;
                float newY = joystickHandle.getY() + dy;

                // Проверяем, чтобы джойстик не выходил за пределы центра устройства
                float radius = 0.3f * Math.min(centerX, centerY);
                float distance = (float) Math.sqrt(Math.pow(newX + joystickHandle.getWidth() / 2 - centerX, 2) +
                        Math.pow(newY + joystickHandle.getHeight() / 2 - centerY, 2));
                if (distance <= radius) {
                    joystickHandle.setX(newX);
                    joystickHandle.setY(newY);
                } else {
                    float angle = (float) Math.atan2(newY + joystickHandle.getHeight() / 2 - centerY,
                            newX + joystickHandle.getWidth() / 2 - centerX);
                    joystickHandle.setX((float) (centerX + radius * Math.cos(angle)) - joystickHandle.getWidth() / 2);
                    joystickHandle.setY((float) (centerY + radius * Math.sin(angle)) - joystickHandle.getHeight() / 2);
                }

                // Обновляем TextView с текущими координатами
                updateCoordinatesText(newX, newY);

                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                // Возвращаем джойстик в начальную позицию
                joystickHandle.setX(initialX);
                joystickHandle.setY(initialY);

                // Обновляем TextView с координатами 0, 0
                updateCoordinatesText(initialX, initialY);
                break;
        }
        return true;
    }

    private void updateCoordinatesText(float x, float y) {
        // Вычисляем координаты относительно центра
        float relativeX = x + joystickHandle.getWidth() / 2 - centerX;
        float relativeY = y + joystickHandle.getHeight() / 2 - centerY;

        // Определяем значение по вертикали
        String verticalValue;
        if (Math.abs(relativeY) > Math.abs(relativeX)) {
            verticalValue = relativeY < 0 ? "1" : "-1";
        } else {
            verticalValue = "0";
        }

        // Определяем значение по горизонтали
        String horizontalValue;
        if (Math.abs(relativeX) > Math.abs(relativeY)) {
            horizontalValue = relativeX < 0 ? "-1" : "1";
        } else {
            horizontalValue = "0";
        }

        // Обновляем TextView с координатами
        coordinatesTextView.setText("Vertical: " + verticalValue + ", Horizontal: " + horizontalValue);
    }
}


/*
package com.example.dod_app;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Robot extends Fragment implements View.OnTouchListener {

    private ImageView joystickHandle;
    private ImageView joystickBase;
    private TextView coordinatesTextView;
    private float centerX, centerY;
    private float lastX, lastY;
    private float initialX, initialY;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_robot, container, false);

        joystickHandle = view.findViewById(R.id.joystick_handle);
        joystickBase = view.findViewById(R.id.joystick_base);
        coordinatesTextView = view.findViewById(R.id.coordinates_textview); // Находим TextView для отображения координат
        joystickBase.setOnTouchListener(this);

        // После того как представление отображено на экране, можно получить его размеры
        joystickBase.post(new Runnable() {
            @Override
            public void run() {
                // Вычисляем центр устройства
                centerX = view.getWidth() / 2;
                centerY = view.getHeight() / 2;

                // Сохраняем начальные координаты джойстика
                initialX = joystickHandle.getX();
                initialY = joystickHandle.getY();
            }
        });

        return view;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                float dx = event.getRawX() - lastX;
                float dy = event.getRawY() - lastY;

                float newX = joystickHandle.getX() + dx;
                float newY = joystickHandle.getY() + dy;

                // Проверяем, чтобы джойстик не выходил за пределы центра устройства
                float radius = 0.3f * Math.min(centerX, centerY);
                float distance = (float) Math.sqrt(Math.pow(newX + joystickHandle.getWidth() / 2 - centerX, 2) +
                        Math.pow(newY + joystickHandle.getHeight() / 2 - centerY, 2));
                if (distance <= radius) {
                    joystickHandle.setX(newX);
                    joystickHandle.setY(newY);
                } else {
                    float angle = (float) Math.atan2(newY + joystickHandle.getHeight() / 2 - centerY,
                            newX + joystickHandle.getWidth() / 2 - centerX);
                    joystickHandle.setX((float) (centerX + radius * Math.cos(angle)) - joystickHandle.getWidth() / 2);
                    joystickHandle.setY((float) (centerY + radius * Math.sin(angle)) - joystickHandle.getHeight() / 2);
                }

                // Обновляем TextView с текущими координатами
                updateCoordinatesText(newX, newY);

                lastX = event.getRawX();
                lastY = event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                // Возвращаем джойстик в начальную позицию
                joystickHandle.setX(initialX);
                joystickHandle.setY(initialY);
                break;
        }
        return true;
    }

    private void updateCoordinatesText(float x, float y) {
        // Вычисляем координаты относительно центра
        float relativeX = x + joystickHandle.getWidth() / 2 - centerX;
        float relativeY = y + joystickHandle.getHeight() / 2 - centerY;

        // Определяем значение по вертикали
        String verticalValue;
        if (Math.abs(relativeY) > Math.abs(relativeX)) {
            verticalValue = relativeY < 0 ? "1" : "-1";
        } else {
            verticalValue = "0";
        }

        // Определяем значение по горизонтали
        String horizontalValue;
        if (Math.abs(relativeX) > Math.abs(relativeY)) {
            horizontalValue = relativeX < 0 ? "-1" : "1";
        } else {
            horizontalValue = "0";
        }

        // Обновляем TextView с координатами
        coordinatesTextView.setText("Vertical: " + verticalValue + ", Horizontal: " + horizontalValue);
    }
}

 */