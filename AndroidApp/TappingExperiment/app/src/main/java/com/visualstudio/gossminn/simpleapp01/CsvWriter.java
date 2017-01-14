package com.visualstudio.gossminn.simpleapp01;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class CsvWriter {

    // Set separators
    private static final String LINE_SEP = "\n";
    private static final String COLUMN_SEP = ",";

    // Set header
    private static final String HEADER = "subjectID,trialID,currentTime,eventType,stimType,stimFile,stimChar";

    // Write header
    static void writeHeader(File outputFile) {
        FileOutputStream outputStream = null;

        // Open outputStream
        try {
            outputStream = new FileOutputStream(outputFile, false); // don't append
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Write header
        try {
            assert outputStream != null;
            outputStream.write(HEADER.getBytes());
            outputStream.write(LINE_SEP.getBytes());
            System.out.println("Written to csv: " + HEADER);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close outputStream
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write trial start event
    static void writeTrialStart(File outputFile, Trial trial) {
        FileOutputStream outputStream = null;

        // Open outputStream
        try {
            outputStream = new FileOutputStream(outputFile, true); // do append
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create empty event string
        String eventString = "";

        // Add SubjectID
        eventString += MainActivity.participantID;
        eventString += COLUMN_SEP;

        // Add trialID
        eventString += trial.getTrialID();
        eventString += COLUMN_SEP;

        // Add currentTime
        eventString += System.currentTimeMillis();
        eventString += COLUMN_SEP;

        // Add eventType
        eventString += "trialStart";
        eventString += COLUMN_SEP;

        // Add stimType
        eventString += trial.getStimulus().stimType;
        eventString += COLUMN_SEP;

        // Add stimFile
        eventString += trial.getStimulus().fileName;
        eventString += COLUMN_SEP;

        // Add stimChar (leave empty)
        eventString += "";

        // Write to CSV
        try {
            assert outputStream != null;
            outputStream.write(eventString.getBytes());
            outputStream.write(LINE_SEP.getBytes());
            System.out.println("Written to csv: " + eventString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close outputStream
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write character show event
    static void writeCharShow(File outputFile, String currentChar) {
        FileOutputStream outputStream = null;

        // Open outputStream
        try {
            outputStream = new FileOutputStream(outputFile, true); // do append
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create empty event string
        String eventString = "";

        // Add SubjectID
        eventString += MainActivity.participantID;
        eventString += COLUMN_SEP;

        // Add trialID (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add currentTime
        eventString += System.currentTimeMillis();
        eventString += COLUMN_SEP;

        // Add eventType
        eventString += "charShow";
        eventString += COLUMN_SEP;

        // Add stimType (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add stimFile (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add stimChar
        eventString += currentChar;

        // Write to CSV
        try {
            assert outputStream != null;
            outputStream.write(eventString.getBytes());
            outputStream.write(LINE_SEP.getBytes());
            System.out.println("Written to csv: " + eventString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close outputStream
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Write screen tap event
    static void writeScreenTap(File outputFile) {
        FileOutputStream outputStream = null;

        // Open outputStream
        try {
            outputStream = new FileOutputStream(outputFile, true); // do append
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // Create empty event string
        String eventString = "";

        // Add SubjectID
        eventString += MainActivity.participantID;
        eventString += COLUMN_SEP;

        // Add trialID (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add currentTime
        eventString += System.currentTimeMillis();
        eventString += COLUMN_SEP;

        // Add eventType
        eventString += "screenTap";
        eventString += COLUMN_SEP;

        // Add stimType (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add stimFile (leave empty)
        eventString += "";
        eventString += COLUMN_SEP;

        // Add stimChar (leave empty)
        eventString += "";

        // Write to CSV
        try {
            assert outputStream != null;
            outputStream.write(eventString.getBytes());
            outputStream.write(LINE_SEP.getBytes());
            System.out.println("Written to csv: " + eventString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Close outputStream
        try {
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Declare CSV printer
    //CSVPrinter csvPrinter;

}
