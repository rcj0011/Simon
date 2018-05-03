package termproject.rcj0011.comp3710.csse.eng.auburn.edu.simon;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ScoresActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        TextView highScore = findViewById(R.id.highScoreText);
        int score = getHighScore();
        String output = "" + score;
        highScore.setText(output);
    }

    public int getHighScore() {
        SharedPreferences prefs = this.getSharedPreferences("scorePrefs", Context.MODE_PRIVATE);
        return prefs.getInt("highScore", 0);
    }
}
