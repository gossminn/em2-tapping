package com.visualstudio.gossminn.simpleapp01;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Declare buttons
    Button instructionsButton;
    Button practiceButton;
    Button tappingExpButton;

    // Declare participant id
    static String participantID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set content view
        setContentView(R.layout.activity_main);

        // Set buttons
        instructionsButton = (Button) findViewById(R.id.instructions_button);
        practiceButton = (Button) findViewById(R.id.practice_button);
        tappingExpButton = (Button) findViewById(R.id.tapping_exp_button);

        // Disable all buttons
        instructionsButton.setEnabled(false);
        practiceButton.setEnabled(false);
        tappingExpButton.setEnabled(false);
    }

    // Set participant ID
    public void setID(View view) {
        EditText enterID = (EditText) findViewById(R.id.participant_id);
        participantID = enterID.getText().toString();

        // Enable instructions button
        instructionsButton.setEnabled(true);
    }

    // Finish current intent, make new MainActivity for next participant
    public void nextParticipant(View view) {
        Trial.resetCounter();
        Intent intent = new Intent (this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void launchInstructions(View view) {
        Intent intent = new Intent(this, InstructionsActivity.class);
        startActivity(intent);
        practiceButton.setEnabled(true);
    }

    public void launchPractice(View view) {
        Intent intent = new Intent(this, PracticeActivity.class);
        startActivity(intent);
        tappingExpButton.setEnabled(true);
    }

    public void launchTappingExperiment(View view) {
        // Go to new intent
        Intent intent = new Intent(this, TappingActivity.class);
        startActivity(intent);
    }
}
