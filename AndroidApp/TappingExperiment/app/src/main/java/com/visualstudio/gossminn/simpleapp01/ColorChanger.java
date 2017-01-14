package com.visualstudio.gossminn.simpleapp01;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.widget.LinearLayout;

// Runnable to change the activity background color (when user taps on screen)
class ColorChanger implements Runnable {
    private Activity trialActivity;

    public ColorChanger(Activity trialActivity) {
        this.trialActivity = trialActivity;
    }

    @Override
    public void run() {
        LinearLayout layout = (LinearLayout) trialActivity.findViewById(R.id.trial_layout);
        layout.setBackgroundColor(ContextCompat.getColor
                (trialActivity.getBaseContext(), R.color.colorPrimary));
    }
}
