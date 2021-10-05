package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueButton;

    private Button mFalseButton;

    private Button mNextButton;

    private Button mAnswerButton;

    private TextView mQuestionTextView;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        if(savedInstanceState !=null)

        {

            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);

        }
        mTrueButton = (Button) findViewById(R.id.buttonTrue);

        mTrueButton.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {
                checkAnswer(true);
                Toast.makeText(MainActivity.this,

                        R.string.correct_toast,

                        Toast.LENGTH_SHORT).show();

            }

        });

        mFalseButton = (Button) findViewById(R.id.buttonFalse);

        mFalseButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {
                checkAnswer(false);
                Toast.makeText(MainActivity.this,

                        R.string.incorrect_toast,

                        Toast.LENGTH_SHORT).show();

            }

        });

        mQuestionTextView = (TextView) findViewById(R.id.textViewQuestion);

        getNextQuestion();
        mNextButton = (Button) findViewById(R.id.buttonNext);

        mNextButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {

                mCurrentIndex = (mCurrentIndex + 1) % mQuestions.length;

                getNextQuestion();

            }

        });
        mAnswerButton = (Button) findViewById(R.id.buttonPeep);

        mAnswerButton.setOnClickListener(new View.OnClickListener() {

            @Override

            public void onClick(View v) {


                boolean answerIsTrue = mQuestions[mCurrentIndex].isRightAnswer();

                Intent intent = AnswersActivity.sendIntent(MainActivity.this,

                        answerIsTrue, mCurrentIndex);

                startActivity(intent);

            }

        });    }

    private void getNextQuestion() {
        int question = mQuestions[mCurrentIndex].getTextResId();

        mQuestionTextView.setText(question);
    }

    private Question[] mQuestions = new Question[]{

            new Question(R.string.question_1, true),

            new Question(R.string.question_2, true),

            new Question(R.string.question_3, true),

            new Question(R.string.question_4, false),

            new Question(R.string.question_5, false),

    };

    private int mCurrentIndex = 0;

    private void checkAnswer(boolean rightAnswer) {

        boolean answerIsTrue = mQuestions[mCurrentIndex].isRightAnswer();

        int messageResId;

        if (rightAnswer == answerIsTrue) {

            messageResId = R.string.correct_toast;

        } else {

            messageResId = R.string.incorrect_toast;

        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)

                .show();

    }

    private static final String KEY_INDEX = "INDEX";

    @Override

    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);

        outState.putInt(KEY_INDEX, mCurrentIndex);

    }


}