package ru.naumov.contentfilter.input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<String> ReadFile(String fileName){
        String path = "." + File.separator + fileName;
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(path);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> output = new ArrayList<>();
        try {
            while(bufferedReader.ready()){
                output.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return output;
    }
}
