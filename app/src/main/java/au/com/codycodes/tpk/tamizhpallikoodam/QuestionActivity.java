package au.com.codycodes.tpk.tamizhpallikoodam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class QuestionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        DatabaseHelper db = new DatabaseHelper(this);

        final Quiz quiz = db.getQuiz();

        TextView category = findViewById(R.id.category);
        category.setText(quiz.getCategory());

        TextView question = findViewById(R.id.question);
        question.setText(quiz.getTamilTranslation());

        TextView option1 = findViewById(R.id.option1);
        option1.setText(quiz.getOption1());
        TextView option2 = findViewById(R.id.option2);
        option2.setText(quiz.getOption2());
        TextView option3 = findViewById(R.id.option3);
        option3.setText(quiz.getOption3());
        TextView option4 = findViewById(R.id.option4);
        option4.setText(quiz.getOption4());

        final Button button = findViewById(R.id.submit);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup rg = findViewById(R.id.options);
                String selectedOption = ((RadioButton)findViewById(rg.getCheckedRadioButtonId())).getText().toString();
                if(selectedOption == quiz.getAnswer()){
                    Toast.makeText(getApplicationContext(), "Correct!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Incorrect!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
