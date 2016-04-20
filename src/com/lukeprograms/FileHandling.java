package com.lukeprograms;


import java.io.*;

public class FileHandling implements AutoCloseable {
    public static String fileName = "Save.txt";

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
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
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

    @Override
    public void close() throws Exception {
        System.out.println("Closing File.");
    }
}
