package com.visualstudio.gossminn.simpleapp01;

import android.app.Activity;
import android.os.Handler;
import android.widget.TextView;

import java.io.File;

class Trial {
    // Set duration of each stimulus
    private final static int STIM_INTERVAL = 400;

    // Create counter (for trial IDs)
    private static int counter = 1;

    // Declare stimulus handler
    private static Handler stimHandler = new Handler();

    // Declare TextView
    TextView stimText;

    // Trial properties
    private long startTime;
    private int postTime;
    private int trialID;
    private Stimulus stim;

    // Getter for startTime
    long getStartTime() {
        return startTime;
    }

    // Getter for trialID
    int getTrialID() {
        return trialID;
    }

    // Getter for Stimulus
    Stimulus getStimulus() {
        return stim;
    }

    // Setter for counter
    static void resetCounter() {
        counter = 1;
    }

    // Constructor takes Stimulus as input
    Trial(Stimulus stim) {
        this.stim = stim;
        postTime = STIM_INTERVAL;
        trialID = counter++;
    }

    // Method for actually running the trial
    void runTrial(Activity trialActivity, File file) {
        // Log trial start event to CsvWriter
        CsvWriter.writeTrialStart(file, this);

        // Log trial id
        System.out.println("Trial id: " + trialID);

        // Set start time
        startTime = System.currentTimeMillis();

        // Set trialActivity and TextView
        stimText = (TextView) trialActivity.findViewById(R.id.tapping_stim_text);

        // Iterate over the characters in each Stimulus's stimArr
        for (char subStim : stim.stimArr) {
            // Show current character at postTime
            CharShower currentCharShower = new CharShower(this, subStim, file);
            stimHandler.postDelayed(currentCharShower, postTime);

            // Increase postTime value
            postTime += STIM_INTERVAL;
        }
        ActivityStopper activityStopper = new ActivityStopper(trialActivity);
        stimHandler.postDelayed(activityStopper, postTime);
    }

    static void recordResponse(Activity trialActivity, File outputFile) {
        // Write screenTap event to CSV
        CsvWriter.writeScreenTap(outputFile);

        // New ColorChanger and ColorUndoer objects
        ColorChanger currentColorChanger = new ColorChanger(trialActivity);
        ColorUndoer currentColorUndoer = new ColorUndoer(trialActivity);

        // Change color to black
        stimHandler.post(currentColorChanger);

        // Revert after a fixed amount of time
        int BACKGR_REVERT = 100;
        stimHandler.postDelayed(currentColorUndoer, BACKGR_REVERT);
    }

}
