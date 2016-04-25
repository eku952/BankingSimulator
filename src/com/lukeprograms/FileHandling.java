package com.lukeprograms;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileHandling implements AutoCloseable {
    private static final String fileName = "Save.txt";
    private static final String JSONFile = "JSONSave.json";
    Map<String, Account> account = new HashMap<>();

    //public static String currentFile;

    /*public static void setSaveFile(int saveFile) {
        switch (saveFile) {
            case 1: currentFile = fileName1;
                break;
            case 2: currentFile = fileName2;
                break;
            case 3: currentFile = fileName3;
                break;
            case 4: currentFile = fileName4;
                break;
            default: currentFile = fileName1;
                System.out.println("Not a proper file number");
                break;
        }

    }*/


    public static void writeFile(StringBuilder stagedText) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fileName))) {
            bufferedWriter.write(String.valueOf(stagedText));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String readFile(int lineToRead) {
        StringBuilder stagedText = new StringBuilder();
        String line = null;
        int counter = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            while((line = bufferedReader.readLine()) != null) {
                counter++;

                if(counter == lineToRead) {
                    stagedText.append(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stagedText.toString();
    }

    public static String readFullFile() {
        StringBuilder stagedText = new StringBuilder();
        String line = null;
        //int counter = 0;
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(JSONFile))) {
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
        mainOverwrite.append(System.lineSeparator());
        mainOverwrite.append(FileHandling.readFile(5));
        FileHandling.writeFile(mainOverwrite);
        System.out.println("Writing to File Success.");
    }

    public static JSONObject readAsJSON() throws ParseException {
        String transfer = FileHandling.readFullFile();

        JSONParser parser = new JSONParser();
        Object obj = parser.parse(transfer);
        JSONObject accountJSON = (JSONObject)obj;

        return accountJSON;
    }

    @Override
    public void close() throws Exception {
        System.out.println("Closing File.");
    }
}
