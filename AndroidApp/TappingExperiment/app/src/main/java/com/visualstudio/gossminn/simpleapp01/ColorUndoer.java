package com.visualstudio.gossminn.simpleapp01;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

// Runnable to change the activity background color back to normal (after some time)
class ColorUndoer implements  Runnable {
    private Activity trialActivity;

    ColorUndoer(Activity tappingActivity) {
        this.trialActivity = tappingActivity;
    }

    @Override
    public void run() {
        LinearLayout layout = (LinearLayout) trialActivity.findViewById(R.id.trial_layout);
        layout.setBackgroundColor(ContextCompat.getColor
                (trialActivity.getBaseContext(), android.R.color.background_light));
    }
}
