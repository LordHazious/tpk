package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultSplashActivity extends AppCompatActivity {

    private final int SPLASH_DISPLAY_LENGTH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_splash);

        Intent myIntent = getIntent();
        String score = myIntent.getStringExtra("score");
        Log.e("SCORE", myIntent.getStringExtra("score"));

        RatingBar bar = findViewById(R.id.ratingBar1);
        TextView t = findViewById(R.id.textResult);

        bar.setRating(Integer.valueOf(score));

        switch (Integer.valueOf(score))
        {
            case 1:
            case 2: t.setText("Oopsie! Better Luck Next Time!");
                break;
            case 3:
            case 4:t.setText("Hmmmm.. Someone's been reading a lot of trivia");
                break;
            case 5:t.setText("Who are you? A trivia wizard???");
                break;
        }

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(ResultSplashActivity.this, QuestionActivity.class);
                ResultSplashActivity.this.startActivity(mainIntent);
                ResultSplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
