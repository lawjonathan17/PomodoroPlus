package com.lawjonathan.pomodoroplus;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {
    int seconds = 59;
    int minutes = 24;
    boolean isPaused = false;
    boolean isResetted = false;
    long millisecondsLeft = 1500000;

    public void start(View view){
        isPaused = false;
        isResetted = false;
        timerStart(millisecondsLeft);
//        timerStart();
    }
    public void pauseAndResume(View view){
        Button pauseButton = (Button)findViewById(R.id.pauseButton);
        if (isPaused == false){
            isPaused = true;
            pauseButton.setText("Resume");
        }
        else { //Resume
            isPaused = false;
            pauseButton.setText("Pause");
            timerStart(millisecondsLeft);
        }
    }

    public void reset(View view){
        seconds = 59;
        minutes = 24;
        millisecondsLeft = 1500000;
        TextView timerText = (TextView)findViewById(R.id.time);
        if (isResetted == false){
            isResetted = true;
            timerText.setText("25:00");
        }
    }
    //    public void reset(View view){
//        seconds = 59;
//        minutes = 24;
//    }
//    public void timerStart(){
    public void timerStart(long timeInMilliseconds){
        new CountDownTimer(timeInMilliseconds, 200){
            //        new CountDownTimer(1500000, 200){
            TextView timerText = (TextView)findViewById(R.id.time); //time in text
            //            int seconds = 59;
//            int minutes = 24;
            String minutesStr;
            String secondsStr;
            public void onTick(long millisecondsUntilDone){
                //update text
                //int minutes = 1500000/60000;
                if (isPaused == true || isResetted == true) {
                    cancel();
                }
                else{
                    millisecondsLeft = millisecondsUntilDone;
                    minutesStr = Integer.toString(minutes); //24
                    secondsStr = Integer.toString(seconds); //59

                    if (seconds == 0) {
                        timerText.setText(minutesStr + ":0" + secondsStr);
                        seconds = 59;
                        minutes--;
                    } else if (seconds > 0 && seconds <= 9) {
                        timerText.setText(minutesStr + ":0" + secondsStr);
                        seconds--;
                    } else if (seconds >= 10) {
                        timerText.setText(minutesStr + ":" + secondsStr);
                        seconds--;
                    }
                }
            }
            public void onFinish(){
                //set text to 0

            }

        }.start();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
