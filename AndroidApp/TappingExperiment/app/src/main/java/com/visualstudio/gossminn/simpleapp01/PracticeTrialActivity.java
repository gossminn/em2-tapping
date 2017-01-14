package com.visualstudio.gossminn.simpleapp01;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class PracticeTrialActivity extends AppCompatActivity implements Trials {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_trial);
        Trial currentTrial = PracticeActivity.practiceIterator.next();
        System.out.println("Trial no.: " + currentTrial.getTrialID());
        System.out.println(currentTrial.getStimulus().fileName);
        currentTrial.runTrial(this, PracticeActivity.outputFile);
    }

    @Override
    public void onClickEvent(View view) {
        System.out.println("Click!");
        Trial.recordResponse(this, PracticeActivity.outputFile);
    }

}
