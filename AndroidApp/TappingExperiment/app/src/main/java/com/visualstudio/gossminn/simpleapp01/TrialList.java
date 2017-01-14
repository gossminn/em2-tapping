package com.visualstudio.gossminn.simpleapp01;

import java.util.ArrayList;
import java.util.Collections;

class TrialList {
    // Declare trial list
    private ArrayList<Trial> trials;

    // Getter for trials
    ArrayList<Trial> getTrials() {
        return trials;
    }

    // Constructor takes StimulusList as input
    TrialList(StimulusList stimList) {
        // Create trial and stimulus lists
        trials = new ArrayList<>();
        ArrayList<Stimulus> stimuli = stimList.stimuli;

        // Shuffle the stimulus list
        Collections.shuffle(stimuli);

        // Create trial for every stimulus
        for (Stimulus stimulus : stimuli) {
            trials.add(new Trial(stimulus));
        }
    }
}
