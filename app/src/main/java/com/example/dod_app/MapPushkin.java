package com.example.dod_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapPushkin extends AppCompatActivity {

    private ImageView imageView;
    private Button undergroundButton, firstButton, secondButton, thirdButton;
    private TextView floorNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mappushkin);

        imageView = findViewById(R.id.imageView3);

        undergroundButton = findViewById(R.id.undergroundPushkin);
        firstButton = findViewById(R.id.firstPushkin);
        secondButton = findViewById(R.id.secondPushkin);
        thirdButton = findViewById(R.id.thirdPushkin);

        floorNameTextView = findViewById(R.id.floorNameTextView);

        undergroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.canteen, "Underground Floor");
            }
        });

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.first_floor, "First Floor");
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.second_floor, "Second Floor");
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.third_floor, "Third Floor");
            }
        });


        changeImage(R.drawable.canteen, "Underground Floor");
    }

    private void changeImage(int imageResourceId, String floorName) {

        imageView.setImageResource(imageResourceId);

        floorNameTextView.setText(floorName);
    }
}
