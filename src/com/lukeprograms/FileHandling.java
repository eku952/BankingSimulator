package com.lukeprograms;


import java.io.*;

public class FileHandling {
    public static String fileName = "Save.sba";

    public static void writeFile(StringBuilder stagedText) {
        try {
            FileWriter fileWriter = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(String.valueOf(stagedText));
            bufferedWriter.close();

            } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(int lineToRead) {
        StringBuilder stagedText = new StringBuilder();
        String line = null;
        int counter = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                counter++;

                if(counter == lineToRead) {
                    stagedText.append(line);
                }
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stagedText.toString();
    }

    public static String readFullFile() {
        StringBuilder stagedText = new StringBuilder();
        String line = null;
        //int counter = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                stagedText.append(line);
            }

            bufferedReader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stagedText.toString();
    }

    public static void overwriteFile(String change) {
        StringBuilder mainOverwrite = new StringBuilder();
        mainOverwrite.append(FileHandling.readFile(1));
        mainOverwrite.append(System.lineSeparator());
        mainOverwrite.append(FileHandling.readFile(2));
        mainOverwrite.append(System.lineSeparator());
        mainOverwrite.append(FileHandling.readFile(3));
        mainOverwrite.append(System.lineSeparator());
        mainOverwrite.append(change);
        FileHandling.writeFile(mainOverwrite);
        System.out.println("Writing to File Success.");
    }
}
