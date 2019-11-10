package com.example.alarmclock;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class QuizActivity extends AppCompatActivity {

    //Purpose : Activity to Display Quiz to switch off the alarm
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_activity);

        Button check = (Button) findViewById(R.id.check);
        final TextView ans = (TextView) findViewById(R.id.ans);

        final RadioGroup rg = (RadioGroup) findViewById(R.id.radioGroup);

        check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Get the checked Radio Button ID from Radio Group
                int selectedRadioButtonID = rg.getCheckedRadioButtonId();

                // If nothing is selected from Radio Group, then return -1
                if (selectedRadioButtonID != -1) {

                    RadioButton selectedRadioButton = (RadioButton) findViewById(selectedRadioButtonID);
                    String selectedRadioButtonText = selectedRadioButton.getText().toString();

                    if(selectedRadioButtonText.equals("H2O"))
                    {
                        ans.setText("Alarm Switched Off");

                        Intent intent = new Intent();
                        setResult(RESULT_OK, intent);
                        finish();
                    }

                    else {
                        ans.setText("Select correct answer to switch off alarm");
                    }
                }
                else{

                    ans.setText("Nothing selected from Radio Group.");
                }
            }
        });


    }
}
