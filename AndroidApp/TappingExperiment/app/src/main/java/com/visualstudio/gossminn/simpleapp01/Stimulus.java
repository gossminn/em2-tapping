package com.visualstudio.gossminn.simpleapp01;

// Class for representing stimulus objects
class Stimulus {

    // Fields for representing essential information about stimulus
    String fileName;
    String[] stimTypes = {"golden", "easy", "lsys", "training"};
    String stimType; // type of stimulus (= condition)
    String stimStr; // stimulus string provided by input csv
    char[] stimArr; // stimulus string as array

    // Take info from csv and process it
    Stimulus(String fileName, String stimStr) {
        // Put constructor parameters into the relevant fields
        this.fileName = fileName;
        this.stimStr = stimStr;

        // Convert stimulus string to an array of an array of chars
        stimArr = stimStr.toCharArray();

        // Select appropriate type from stimTypes using filename
        stimType = "";
        for (String type : stimTypes) {
            if (this.fileName.startsWith(type)) {
                stimType = type;
            }
        }
    }
}
