package au.com.codycodes.tpk.tamizhpallikoodam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.media.MediaPlayer;
import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private int score = 0;
    private int qid = 0;
    private ArrayList<Quiz> questions;
    private Quiz quiz;
    private String selectedOption;
    TextView category,question,option1,option2,option3,option4;
    MediaPlayer mp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DatabaseHelper db = new DatabaseHelper(this);
        questions = new ArrayList<>();

        int x = 1;

        do {
            try {

                quiz = db.getQuiz();
                questions.add(new Quiz(quiz.getTamilTranslation(), quiz.getAnswer(), quiz.getCategory(), quiz.getOption1(), quiz.getOption2(), quiz.getOption3(), quiz.getOption4(), quiz.getImageResourceId(), quiz.getAudioResourceId()));
                Log.e("Test", "Add Object "+x);
            } catch (Exception e) {
                Log.e("ERROR", "Getting DB ");
            }
            x++;
        }while(x <=5);

        setContentView(R.layout.activity_question);
        category = findViewById(R.id.category);
        question = findViewById(R.id.question);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        setQuestionView();

        final Button submit = findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.options);
                if(rg.getCheckedRadioButtonId() != -1) {
                    selectedOption = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();

                    if (selectedOption.equals(questions.get(qid).getAnswer())) {
                        Toast.makeText(getApplicationContext(), "CORRECT", Toast.LENGTH_LONG).show();
                        score += 1;
                    } else {
                        Toast.makeText(getApplicationContext(), "INCORRECT", Toast.LENGTH_LONG).show();
                    }

                    if(qid == 4){
                        // Create a new intent to open the {@link LearnActivity}
                        Intent resultIntent = new Intent(QuestionActivity.this, ResultSplashActivity.class);
                        resultIntent.putExtra("score", String.valueOf(score));

                        Log.e("SCORE", String.valueOf(score));

                        // Start the new activity
                        startActivity(resultIntent);
                    } else {
                        qid++;
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Please make a selection", Toast.LENGTH_LONG).show();
                }
                setQuestionView();
            }
        });

        final ImageView play =  findViewById(R.id.play);

        play.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                mp.start();
            }
        });
    }

    private void setQuestionView()
    {
        category.setText(questions.get(qid).getCategory());
        question.setText(questions.get(qid).getTamilTranslation());
        option1.setText(questions.get(qid).getOption1());
        option2.setText(questions.get(qid).getOption2());
        option3.setText(questions.get(qid).getOption3());
        option4.setText(questions.get(qid).getOption4());
        mp = MediaPlayer.create(this, questions.get(qid).getAudioResourceId());
    }
}
