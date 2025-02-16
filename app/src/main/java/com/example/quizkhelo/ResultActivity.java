package com.example.quizkhelo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {
    TextView tvUserName, tvUserScore;
    Button shareButton;
    ImageView backButton;

    @SuppressLint({"SetTextI18n", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // Initialize Views
        tvUserName = findViewById(R.id.tvUserName);
        tvUserScore = findViewById(R.id.tvUserScore);
        shareButton = findViewById(R.id.btnShareScore);
        backButton = findViewById(R.id.backButton);

        // Get data from Intent
        Intent intent = getIntent();
        String userName = intent.getStringExtra("USER_NAME"); // Retrieve username
        int score = intent.getIntExtra("score", 0);
        int totalQuestions = intent.getIntExtra("totalQuestions", 10);

        // Display Name and Score
        tvUserName.setText(userName);
        tvUserScore.setText(score + "/" + totalQuestions);

        // Share Score Button Click Listener
        shareButton.setOnClickListener(v -> shareScore(userName, score, totalQuestions));

        // Back Button Click Listener - Navigate to MainActivity
        backButton.setOnClickListener(v -> {
            Intent mainIntent = new Intent(ResultActivity.this, MainActivity.class);
            startActivity(mainIntent);
            finish();
        });


    }

    private void shareScore(String userName, int score, int totalQuestions) {
        String shareMessage = userName + " scored (" + score + " out of " + totalQuestions + ") in QuizKhelo! 🎉";

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);

        // Show share options
        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}
