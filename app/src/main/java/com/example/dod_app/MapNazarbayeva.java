package com.example.dod_app;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MapNazarbayeva extends AppCompatActivity {

    private ImageView imageView;
    private Button undergroundButton, firstButton, secondButton, thirdButton, fourthButton, fifthButton;
    private TextView floorNameTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapnazarbayev);

        imageView = findViewById(R.id.imageView3);
        undergroundButton = findViewById(R.id.undergroundNazarbayev);
        firstButton = findViewById(R.id.firstNazarbayev);
        secondButton = findViewById(R.id.secondNazarbayev);
        thirdButton = findViewById(R.id.thirdNazarbayev);
        fourthButton = findViewById(R.id.fourthNazarbayev);
        fifthButton = findViewById(R.id.fifthNazarbayev);


        floorNameTextView = findViewById(R.id.floorNameTextView);

        undergroundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.canteen_nazarbayev, "Underground Floor");
            }
        });

        firstButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.first_floor_nazarbayev, "First Floor");
            }
        });

        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.second_floor_nazarbayev, "Second Floor");
            }
        });

        thirdButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.third_floor_nazarbayev, "Third Floor");
            }
        });

        fourthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.fourth_floor_nazarbayev, "Fourth Floor");
            }
        });
        fifthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeImage(R.drawable.fifth_floor_nazarbayev, "Fifth Floor");
            }
        });

        changeImage(R.drawable.canteen_nazarbayev, "Underground Floor");
    }

    private void changeImage(int imageResourceId, String floorName) {
        imageView.setImageResource(imageResourceId);
        floorNameTextView.setText(floorName);
    }
}
