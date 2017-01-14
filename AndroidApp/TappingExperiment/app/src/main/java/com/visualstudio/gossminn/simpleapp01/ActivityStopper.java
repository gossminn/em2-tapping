package com.visualstudio.gossminn.simpleapp01;

import android.app.Activity;

public class ActivityStopper implements Runnable {
    private Activity trialActivity;

    ActivityStopper(Activity trialActivity) {
        this.trialActivity = trialActivity;
    }

    @Override
    public void run() {
        trialActivity.finish();
    }
}
