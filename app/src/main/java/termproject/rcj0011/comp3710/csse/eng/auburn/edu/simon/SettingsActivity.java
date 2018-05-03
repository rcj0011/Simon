package termproject.rcj0011.comp3710.csse.eng.auburn.edu.simon;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void setEasy(View view) {
        SharedPreferences prefs = this.getSharedPreferences("difficultyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("difficulty", 1);
        editor.commit();

        System.out.println("Difficulty set to 1.");
    }

    public void setMedium(View view) {
        SharedPreferences prefs = this.getSharedPreferences("difficultyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("difficulty", 2);
        editor.commit();

        System.out.println("Difficulty set to 2.");
    }

    public void setHard(View view) {
        SharedPreferences prefs = this.getSharedPreferences("difficultyPrefs", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("difficulty", 3);
        editor.commit();

        System.out.println("Difficulty set to 3.");
    }
}
