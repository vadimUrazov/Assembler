package com.example.quiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnswersActivity extends AppCompatActivity {
    public static final String EXTRA_ANSWER_IS_TRUE = "com.example.quiz.true_answer";

    public static final String EXTRA_ANSWER_INDEX = "com.example.quiz.index_answer";

    private boolean mAnswerIsTrue;

    private int mIndex;

    private TextView mAnswerTextView;

    private Button mShowAnswerButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_answers);

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE,

                false);

        mIndex = getIntent().getIntExtra(EXTRA_ANSWER_INDEX, 0);

        mAnswerTextView = (TextView)findViewById(R.id.textViewAnswer);

        mShowAnswerButton = (Button)findViewById(R.id.buttonShowAnswer);

        mShowAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                String[] answers = getResources().getStringArray(R.array.answers);

                if (mAnswerIsTrue) {

                    mAnswerTextView.setText("Да\n");

                    mAnswerTextView.append(answers[mIndex]);

                } else {

                    mAnswerTextView.setText("Нет\n");

                    mAnswerTextView.append(answers[mIndex]);

                }

            }

        });

    }

    public static Intent sendIntent(Context packageContext, boolean

            answerIsTrue, int index) {

        Intent intent = new Intent(packageContext, AnswersActivity.class);

        intent.putExtra(EXTRA_ANSWER_IS_TRUE, answerIsTrue);

        intent.putExtra(EXTRA_ANSWER_INDEX, index);

        return intent;

    }

}

