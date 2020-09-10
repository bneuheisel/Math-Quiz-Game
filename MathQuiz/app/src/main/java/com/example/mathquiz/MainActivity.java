package com.example.mathquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_start, btn_answer0, btn_answer1, btn_answer2, btn_answer3;
    TextView txt_timer, txt_score, txt_questions, txt_bottomMessage;
    ProgressBar prog_timer;

    Game g = new Game();

    int secondsRemaining = 30;
    private boolean timerRunning;

    private void updateButtons(){
        if(timerRunning){
            btn_start.setVisibility(View.INVISIBLE);
        }
        else{
            btn_start.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState( outState );

        //outState.putInt("secondsLeft", secondsRemaining);
        outState.putString("currentScore", txt_score.getText().toString());
        outState.putLong("timeLeft", secondsRemaining);
        outState.putBoolean("timerStatus", timerRunning);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState( savedInstanceState );

        //secondsRemaining = savedInstanceState.getInt("secondsleft");
        txt_score.setText(savedInstanceState.getString("currentScore"));
        secondsRemaining = savedInstanceState.getInt("timeLeft");

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Lifecyclefilter","The app is started.");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Lifecyclefilter","The app is stopped.");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("Lifecyclefilter","The app is destroyed.");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Lifecyclefilter","The app is paused.");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Lifecyclefilter","The app is resumed.");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d("Lifecyclefilter","The app is restarted.");
    }

    CountDownTimer timer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            secondsRemaining--;
            txt_timer.setText(Integer.toString(secondsRemaining) + " sec");
            prog_timer.setProgress(30 - secondsRemaining);
        }

        @Override
        public void onFinish() {
            btn_answer0.setEnabled(false);
            btn_answer1.setEnabled(false);
            btn_answer2.setEnabled(false);
            btn_answer3.setEnabled(false);

            txt_bottomMessage.setText("Time is up! " + g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));

            final Handler handler = new Handler();
            handler.postDelayed( new Runnable() {
                @Override
                public void run() {
                    btn_start.setVisibility(View.VISIBLE);
                }
            } , 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        Log.d("Lifecyclefilter","The app is created.");

        btn_start = findViewById(R.id.btn_start);
        btn_answer0 = findViewById(R.id.btn_answer0);
        btn_answer1 = findViewById(R.id.btn_answer1);
        btn_answer2 = findViewById(R.id.btn_answer2);
        btn_answer3 = findViewById(R.id.btn_answer3);

        txt_timer = findViewById(R.id.txt_timer);
        txt_score = findViewById(R.id.txt_score);
        txt_questions = findViewById(R.id.txt_questions);
        txt_bottomMessage = findViewById(R.id.txt_bottomMessage);

        prog_timer = findViewById(R.id.prog_timer);

        txt_timer.setText("0sec");
        txt_questions.setText("");
        txt_bottomMessage.setText("Press Go");
        txt_score.setText("0pts");

        View.OnClickListener startButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button start_button = (Button) v;
                start_button.setVisibility(View.INVISIBLE);
                secondsRemaining = 30;
                g = new Game();
                nextTurn();
                timer.start();
                timerRunning = true;
                txt_score.setText("0pts");
            }
        };
        View.OnClickListener answerButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button buttonClicked = (Button) v;
                int answerSelected = Integer.parseInt(buttonClicked.getText().toString());

                g.checkAnswer(answerSelected);
                txt_score.setText(Integer.toString(g.getScore()));
                nextTurn();
            }
        };

        btn_start.setOnClickListener(startButtonClickListener);

        btn_answer0.setOnClickListener(answerButtonClickListener);
        btn_answer1.setOnClickListener(answerButtonClickListener);
        btn_answer2.setOnClickListener(answerButtonClickListener);
        btn_answer3.setOnClickListener(answerButtonClickListener);
    }
    private void nextTurn(){
        g.makeNewQuestion();
        int [] answers = g.getCurrentQuestion().getAnswerArray();

        btn_answer0.setText(Integer.toString(answers[0]));
        btn_answer1.setText(Integer.toString(answers[1]));
        btn_answer2.setText(Integer.toString(answers[2]));
        btn_answer3.setText(Integer.toString(answers[3]));

        btn_answer0.setEnabled(true);
        btn_answer1.setEnabled(true);
        btn_answer2.setEnabled(true);
        btn_answer3.setEnabled(true);

        txt_questions.setText(g.getCurrentQuestion().getMathQuestion());

        txt_bottomMessage.setText(g.getNumberCorrect() + "/" + (g.getTotalQuestions() - 1));
    }
}
