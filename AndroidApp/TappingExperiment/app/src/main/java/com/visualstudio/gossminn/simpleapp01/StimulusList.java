package com.visualstudio.gossminn.simpleapp01;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

class StimulusList {
    // Declare stimuli
    ArrayList<Stimulus> stimuli = new ArrayList<>();

    // Constructor imports stimuli from file
    StimulusList(InputStream inputFile) {

        // Create scanner object
        Scanner scanner = new Scanner(inputFile);

        // Loop through the lines in the csv file
        while(scanner.hasNextLine()) {
            // Split current line into columns
            String[] thisLine = scanner.nextLine().split(",");

            // Extract filename and stimulus string from current line
            String fileName = thisLine[0];
            String stimString = thisLine[1];

            // Create new stimulus object and add it to stimuli
            stimuli.add(new Stimulus(fileName, stimString));
        }

        // Close the scanner
        scanner.close();
    }
}
