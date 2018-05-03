package termproject.rcj0011.comp3710.csse.eng.auburn.edu.simon;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        TextView score = findViewById(R.id.scoreText);
        score.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);

        simon();
    }

    private int score;
    private ArrayList<Integer> pattern, userInput;

    public void simon() {
        int difficulty = getDifficulty();
        long delay = 3;
        if(difficulty == 2)
            delay = 2;
        else if(difficulty == 3)
            delay = 1;

        score = 0;
        pattern = new ArrayList<>();
        userInput = new ArrayList<>();
        boolean gameOn = true;

        while(gameOn) {
            int color = (int)(Math.ceil(Math.random() * 4));
            pattern.add(color);

            System.out.println("Pattern: " + pattern);

            //buttonEnabler("off");

            for(int x = 0; x < pattern.size(); x++) {
                flashButton(pattern.get(x));
            }

            //buttonEnabler("on");

            for(int x = 0; x < pattern.size(); x++) {

            }

            for(int x = 0; x < userInput.size(); x++) {
                if(!(userInput.get(x) == pattern.get(x)))
                    gameOn = false;
                else {
                    score++;
                    updateScore(score);
                    userInput.clear();
                }
            }

            gameOn = false;
        }
    }

    public void flashButton(int color) {
        final Button greenButton = findViewById(R.id.greenButton);
        final Button yellowButton = findViewById(R.id.yellowButton);
        final Button redButton = findViewById(R.id.redButton);
        final Button blueButton = findViewById(R.id.blueButton);

        switch(color) {
            case 1:
                greenButton.setPressed(true);
                break;
            case 2:
                yellowButton.setPressed(true);
                break;
            case 3:
                redButton.setPressed(true);
                break;
            case 4:
                blueButton.setPressed(true);
                break;
        }

        Handler handler = new Handler();
        final Runnable r = new Runnable() {
            public void run() {
                greenButton.setPressed(false);
                yellowButton.setPressed(false);
                redButton.setPressed(false);
                blueButton.setPressed(false);
            }
        };
        handler.postDelayed(r, 1000);
    }

    public void greenButtonPress(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.green);
        mp.start();

        userInput.add(1);
        updateScore(score);

        System.out.println("UserInput: " + userInput);
    }

    public void yellowButtonPress(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.yellow);
        mp.start();

        userInput.add(2);
        updateScore(score);

        System.out.println("UserInput: " + userInput);
    }

    public void redButtonPress(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.red);
        mp.start();

        userInput.add(3);
        updateScore(score);

        System.out.println("UserInput: " + userInput);
    }

    public void blueButtonPress(View view) {
        MediaPlayer mp = MediaPlayer.create(this, R.raw.blue);
        mp.start();

        userInput.add(4);
        updateScore(score);

        System.out.println("UserInput: " + userInput);
    }

    public void updateScore(int score) {
        TextView currentScore = findViewById(R.id.scoreText);
        currentScore.setText("Score: " + score);
    }

    public void buttonEnabler(String status) {
        Button greenButton = findViewById(R.id.greenButton);
        Button yellowButton = findViewById(R.id.yellowButton);
        Button redButton = findViewById(R.id.redButton);
        Button blueButton = findViewById(R.id.blueButton);

        if(status.equals("on")) {
            greenButton.setEnabled(true);
            yellowButton.setEnabled(true);
            redButton.setEnabled(true);
            blueButton.setEnabled(true);
        }
        if(status.equals("off")) {
            greenButton.setEnabled(false);
            yellowButton.setEnabled(false);
            redButton.setEnabled(false);
            blueButton.setEnabled(false);
        }
    }

    public int getDifficulty() {
        return R.integer.difficulty;
    }
}
