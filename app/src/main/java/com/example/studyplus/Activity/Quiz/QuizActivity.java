package com.example.studyplus.Activity.Quiz;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.example.studyplus.R;
import android.app.AlertDialog;
import android.graphics.Color;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.ActionBar;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    TextView totalQuestions, question;
    Button ansA, ansB, ansC, ansD;
    Button summitButton;

    int score = 0;
    int totalQuestion = QuestionAnswer.question.length;
    int currentQuestionIndex = 0;
    String selectedAnswer = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        totalQuestions = findViewById(R.id.totalQuestions);
        question = findViewById(R.id.question);
        ansA = findViewById(R.id.ansA);
        ansB = findViewById(R.id.ansB);
        ansC = findViewById(R.id.ansC);
        ansD = findViewById(R.id.ansD);
        summitButton = findViewById(R.id.summitButton);

        ansA.setOnClickListener(this);
        ansB.setOnClickListener(this);
        ansC.setOnClickListener(this);
        ansD.setOnClickListener(this);
        summitButton.setOnClickListener(this);

        totalQuestions.setText("Total Questions: " + totalQuestion );

        loadNewQuestion();
    }

    @Override
    public void onClick(View v) {

        ansA.setBackgroundColor(Color.WHITE);
        ansB.setBackgroundColor(Color.WHITE);
        ansC.setBackgroundColor(Color.WHITE);
        ansD.setBackgroundColor(Color.WHITE);

        Button clickedButton = (Button) v;
        if (clickedButton.getId() == R.id.summitButton) {
            if (selectedAnswer.equals(QuestionAnswer.correctAnswer[currentQuestionIndex])) {
                score++;
            }
            currentQuestionIndex++;
            loadNewQuestion();
        } else {
            //choices button clicked
            selectedAnswer = clickedButton.getText().toString();
            clickedButton.setBackgroundColor(Color.LTGRAY);
        }
    }

    void loadNewQuestion() {

        if (currentQuestionIndex == totalQuestion) {
            finishQuiz();
            return;
        }

        question.setText(QuestionAnswer.question[currentQuestionIndex]);
        ansA.setText(QuestionAnswer.choices[currentQuestionIndex][0]);
        ansB.setText(QuestionAnswer.choices[currentQuestionIndex][1]);
        ansC.setText(QuestionAnswer.choices[currentQuestionIndex][2]);
        ansD.setText(QuestionAnswer.choices[currentQuestionIndex][3]);
    }

    void finishQuiz() {
        String passStatus = "";
        if (score > totalQuestion * 0.60) {
            passStatus = "Passed";
        } else {
            passStatus = "Failed";
        }

        new AlertDialog.Builder(this)
                .setTitle(passStatus)
                .setMessage("Score is "+score+" out of "+totalQuestion)
                .setPositiveButton("Restart", (dialogInterface, i) -> restartQuiz())
                .setCancelable(false)
                .show();
    }

    void restartQuiz() {
        score = 0;
        currentQuestionIndex = 0;
        loadNewQuestion();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}