package com.visualstudio.gossminn.simpleapp01;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class PracticeActivity extends AppCompatActivity {

    // Declare practice iterator and current trial object
    static Iterator<Trial> practiceIterator;

    // Declare TextView, counter for displaying number of trials left
    TextView trialsLeft;
    int numberOfTrials;
    int numberTrialsLeft;

    // Declare file output objects
    static File outputFile;
    static FileOutputStream outputStream;

    // Method that returns new file
    File getOutputFile(String fileName) throws IOException {
        // Create file in directory "Downloads"
        File outputFile = new File(this.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS), fileName);
        // Check if directory exists, output error message if not
        if (!outputFile.getParentFile().mkdirs()) {
            Log.e("getOutputFile", "Directory already exists or could not be created.");
        }
        else {
            System.out.println("File created in " + outputFile);
        }
        System.out.println(outputFile.isDirectory());

        // Return the file
        return outputFile;
    }

    // Code that will be executed once activity is started
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tapping_experiment);

        // Declare trial list
        TrialList trialList;

        // Import stimuli
        try {
            InputStream file = getAssets().open("practice.csv");
            trialList = new TrialList(new StimulusList(file));
        }
        catch (IOException e) {
            trialList = null;
            e.printStackTrace();
        }

        // Create trial iterable
        assert trialList != null;
        practiceIterator = trialList.getTrials().iterator();

        // Display number of trials
        numberOfTrials = trialList.getTrials().size();
        numberTrialsLeft = numberOfTrials;
        trialsLeft = (TextView) findViewById(R.id.tapping_trials_left);
        trialsLeft.setText("Er zijn nog " + numberTrialsLeft + " rondes over.");

        // Write output file TODO: remove dummy output, replace by real output
        try {
            @SuppressLint("SimpleDateFormat")
            DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd-HH_mm_ss");
            String dateStamp = dateFormat.format(new Date());
            String fileName = dateStamp + "_practice" + ".csv";
            outputFile = getOutputFile(fileName);
            CsvWriter.writeHeader(outputFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // Code that will be executed when user taps on the screen
    public void onClickEvent(View view) {
        if (practiceIterator.hasNext()) {
            numberTrialsLeft--;
            Intent intent = new Intent(this, PracticeTrialActivity.class);
            startActivity(intent);
            if (numberTrialsLeft != 1) {
                trialsLeft.setText("Er zijn nog " + numberTrialsLeft + " rondes over.");
            }
            else {
                trialsLeft.setText("Er is nog " + numberTrialsLeft + " ronde over.");
            }
        }
        else {
            finish();
        }
    }

}
