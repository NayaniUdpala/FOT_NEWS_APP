package com.example.fot_news_hub;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class NewsActivity extends AppCompatActivity {

    ImageView userIcon, infoIcon;
    TextView welcomeText, readMore1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        String username = getIntent().getStringExtra("username");
        if (username == null || username.isEmpty()) {
            username = "User";
        }

        userIcon = findViewById(R.id.userIcon);
        infoIcon = findViewById(R.id.infoIcon);
        welcomeText = findViewById(R.id.welcomeText);
        readMore1 = findViewById(R.id.readMore1);

        welcomeText.setText("Hi, " + username);

        userIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, UserInfoActivity.class);
            startActivity(intent);
        });

        infoIcon.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        readMore1.setOnClickListener(v -> {
            Intent intent = new Intent(this, ClickedNewsActivity.class);
            startActivity(intent);
        });
    }
}
