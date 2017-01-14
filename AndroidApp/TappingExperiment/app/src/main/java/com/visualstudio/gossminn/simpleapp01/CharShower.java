package com.visualstudio.gossminn.simpleapp01;

import java.io.File;

// Class for showing the current character in the TextView object
class CharShower implements Runnable {

    private Trial currentTrial;
    private String currentChar;
    private String currentCharAB;
    private File outputFile;


    // Constructor takes current character and color as parameters
    CharShower(Trial currentTrial, char currentChar, File outputFile) {
        this.currentTrial = currentTrial;
        this.outputFile = outputFile;
        this.currentCharAB = Character.toString(currentChar);
        switch (currentCharAB) {
            case "a":
                this.currentChar = "\uD83D\uDE00"; // grinning face
                break;
            case "b":
                this.currentChar = "\uD83D\uDE38"; // grinning cat
                break;
            default:
                this.currentChar = Character.toString(currentChar);
                break;
        }
    }

    // Create runnable that sets stimulus text, saves current time
    @Override
    public void run() {
        // Set TextView to current character
        currentTrial.stimText.setText(currentChar);

        // Write charShow event to csv
        CsvWriter.writeCharShow(outputFile, currentCharAB);

        // Log time
        long timeSinceStart = System.currentTimeMillis() - currentTrial.getStartTime();
        System.out.println("Time since start of trial: " + timeSinceStart);
    }
}
