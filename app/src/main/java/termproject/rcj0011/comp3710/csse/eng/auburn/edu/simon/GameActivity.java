package termproject.rcj0011.comp3710.csse.eng.auburn.edu.simon;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;

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
    private ArrayList<Integer> userInput;

    public void simon() {
        int difficulty = getDifficulty();
        long delay = 3;
        if(difficulty == 2)
            delay = 2;
        else if(difficulty == 3)
            delay = 1;

        score = 0;
        ArrayList<Integer> pattern = new ArrayList<>();
        userInput = new ArrayList<>();

        int gameMode = 1;       //gameMode: 1 -> display pattern, 2 -> get user input, 3 -> game over

        while(true) {
            if(gameMode == 1) {
                int color = (int) (Math.ceil(Math.random() * 4));
                pattern.add(color);

                System.out.println("Pattern: " + pattern);

                buttonEnabler("off");
                for (int x = 0; x < pattern.size(); x++)
                    flashButton(pattern.get(x));
                buttonEnabler("on");

                gameMode = 2;
            }

            if(gameMode == 2) {

                //wait for user input with delay from difficulty

                //check userInput against pattern
                for (int x = 0; x < userInput.size(); x++) {
                    if (userInput.get(x).equals(pattern.get(x))) {
                        score++;
                        updateScore(score);
                        userInput.clear();
                    }
                    else
                        gameMode = 3;
                }

                gameMode = 1;
            }

            gameMode = 3;   //only activated to show issue with flashing all colors at once
                            //comment out to return to ANR functionality

            if(gameMode == 3)
                //finish();     //this ends the activity if the sequence is incorrect
                break;
        }
    }

    public void flashButton(int color) {
        final Button greenButton = findViewById(R.id.greenButton);
        final Button yellowButton = findViewById(R.id.yellowButton);
        final Button redButton = findViewById(R.id.redButton);
        final Button blueButton = findViewById(R.id.blueButton);

        //flash bright color
        switch(color) {
            case 1:
                greenButton.setPressed(true);
                System.out.println("Flashing green.");
                break;
            case 2:
                yellowButton.setPressed(true);
                System.out.println("Flashing yellow.");
                break;
            case 3:
                redButton.setPressed(true);
                System.out.println("Flashing red.");
                break;
            case 4:
                blueButton.setPressed(true);
                System.out.println("Flashing blue.");
                break;
        }

        //return flashed button to normal after 1 second
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

    //updates displayed score text
    public void updateScore(int score) {
        TextView currentScore = findViewById(R.id.scoreText);
        String output = "Score: " + score;
        currentScore.setText(output);

        int highScore = getHighScore();

        if(score > highScore)
            updateHighScore(score);
    }

    //disables/enables button input
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
        SharedPreferences prefs = this.getSharedPreferences("difficultyPrefs", Context.MODE_PRIVATE);
        return prefs.getInt("difficulty", 0);
    }

    public int getHighScore() {
        SharedPreferences prefs = this.getSharedPreferences("scorePrefs", Context.MODE_PRIVATE);
        return prefs.getInt("highScore", 0);
    }

    public void updateHighScore(int score) {
        SharedPreferences prefs = this.getSharedPreferences("scorePrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("highScore", score);
        editor.apply();
    }
}
