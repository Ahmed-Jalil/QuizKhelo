package com.example.quizkhelo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    TextView tvQuestion, tvQuizNumber, tvBtnPrevious;
    Button btnNext;
    RadioButton rbOption1, rbOption2, rbOption3, rbOption4;
    RadioGroup radioGroup;

    List<QuizQuestion> questionsList = new ArrayList<>();
    int currentQuestionIndex = 0;
    int score = 0;
    List<Integer> selectedAnswers; // Store selected options
    String userName; // Store User Name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        // Retrieve User Name from Intent
        userName = getIntent().getStringExtra("USER_NAME");

        // Initialize views
        tvQuestion = findViewById(R.id.tvQuestion);
        tvQuizNumber = findViewById(R.id.tvQuizNumber);
        tvBtnPrevious = findViewById(R.id.tvBtnPrevious);
        btnNext = findViewById(R.id.btnNext);
        radioGroup = findViewById(R.id.radioGroup);
        rbOption1 = findViewById(R.id.rbOption1);
        rbOption2 = findViewById(R.id.rbOption2);
        rbOption3 = findViewById(R.id.rbOption3);
        rbOption4 = findViewById(R.id.rbOption4);

        // Load quiz data
        loadQuizData();

        // Initialize the selectedAnswers list with -1 (meaning no selection)
        selectedAnswers = new ArrayList<>();
        for (int i = 0; i < questionsList.size(); i++) {
            selectedAnswers.add(-1);
        }

        // Show first question
        displayQuestion();

        // Next Button Logic
        btnNext.setOnClickListener(v -> {
            if (radioGroup.getCheckedRadioButtonId() == -1) {
                Toast.makeText(this, "Please select an answer before proceeding", Toast.LENGTH_SHORT).show();
            } else {
                saveSelectedAnswer(); // Save answer before moving
                checkAnswer(); // Check correctness

                if (currentQuestionIndex < questionsList.size() - 1) {
                    currentQuestionIndex++;
                    displayQuestion();
                } else {
                    // If last question, navigate to ResultActivity
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    intent.putExtra("USER_NAME", userName); // Pass Name
                    intent.putExtra("score", score);
                    intent.putExtra("totalQuestions", questionsList.size());
                    startActivity(intent);
                    finish();
                }
            }
        });

        // Previous Button Logic
        tvBtnPrevious.setOnClickListener(v -> {
            if (currentQuestionIndex > 0) {
                saveSelectedAnswer(); // Save current answer
                currentQuestionIndex--;
                displayQuestion();
            } else {
                // If on first question, go back to the main screen
                Intent intent = new Intent(QuizActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void loadQuizData() {
        questionsList.add(new QuizQuestion("Which component is responsible for handling interactions between UI elements in an Android app?", "Activity", "Service", "Broadcast Receiver", "Content Provider", 1));
        questionsList.add(new QuizQuestion("What is the main purpose of an Android Manifest file?", "Defines UI layout", "Declares components", "Manages memory", "Handles network requests", 2));
        questionsList.add(new QuizQuestion("Which component runs in the background to perform long-running operations?", "Activity", "Service", "Intent", "Fragment", 2));
        questionsList.add(new QuizQuestion("Which Android component is used to share data between applications?", "Intent", "Broadcast Receiver", "Content Provider", "Service", 3));
        questionsList.add(new QuizQuestion("What is the entry point of an Android application?", "MainActivity", "AndroidManifest.xml", "Launcher", "Intent", 1));
        questionsList.add(new QuizQuestion("Which XML attribute is used to uniquely identify UI components?", "android:name", "android:tag", "android:id", "android:label", 3));
        questionsList.add(new QuizQuestion("Which Android layout allows placing UI elements relative to each other?", "LinearLayout", "RelativeLayout", "ConstraintLayout", "FrameLayout", 2));
        questionsList.add(new QuizQuestion("Which data storage option is best for storing key-value pairs in Android?", "SQLite", "SharedPreferences", "Internal Storage", "External Storage", 2));
        questionsList.add(new QuizQuestion("Which method is used to navigate between activities in Android?", "startService()", "startActivity()", "sendBroadcast()", "onCreate()", 2));
        questionsList.add(new QuizQuestion("Which Android component allows displaying dynamic lists efficiently?", "ListView", "RecyclerView", "GridLayout", "ScrollView", 2));
    }

    @SuppressLint("SetTextI18n")
    private void displayQuestion() {
        QuizQuestion q = questionsList.get(currentQuestionIndex);

        // Update question text and options
        tvQuestion.setText(q.getQuestion());
        rbOption1.setText(q.getOption1());
        rbOption2.setText(q.getOption2());
        rbOption3.setText(q.getOption3());
        rbOption4.setText(q.getOption4());

        // Update question counter
        tvQuizNumber.setText((currentQuestionIndex + 1) + "/" + questionsList.size());

        // Restore the previous selection
        radioGroup.clearCheck();
        if (selectedAnswers.get(currentQuestionIndex) != -1) {
            switch (selectedAnswers.get(currentQuestionIndex)) {
                case 1:
                    rbOption1.setChecked(true);
                    break;
                case 2:
                    rbOption2.setChecked(true);
                    break;
                case 3:
                    rbOption3.setChecked(true);
                    break;
                case 4:
                    rbOption4.setChecked(true);
                    break;
            }
        }

        // If it's the last question, change button text to "Finish"
        if (currentQuestionIndex == questionsList.size() - 1) {
            btnNext.setText("Finish");
        } else {
            btnNext.setText("Next");
        }
    }

    private void saveSelectedAnswer() {
        int selectedAnswerIndex = -1;

        if (rbOption1.isChecked()) selectedAnswerIndex = 1;
        if (rbOption2.isChecked()) selectedAnswerIndex = 2;
        if (rbOption3.isChecked()) selectedAnswerIndex = 3;
        if (rbOption4.isChecked()) selectedAnswerIndex = 4;

        selectedAnswers.set(currentQuestionIndex, selectedAnswerIndex);
    }

    private void checkAnswer() {
        int selectedAnswerIndex = selectedAnswers.get(currentQuestionIndex);

        if (selectedAnswerIndex == questionsList.get(currentQuestionIndex).getCorrectAnswer()) {
            score++; // Increase score if correct
        }
    }
}
