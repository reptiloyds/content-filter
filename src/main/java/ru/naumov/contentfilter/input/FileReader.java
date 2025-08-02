package ru.naumov.contentfilter.input;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileReader {
    public List<String> readFile(String fileName) throws FileNotFoundException, IOException {
        String path = "." + File.separator + fileName;
        FileInputStream inputStream;
        inputStream = new FileInputStream(path);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        List<String> inputLines = new ArrayList<>();
        while (bufferedReader.ready()) {
            inputLines.add(bufferedReader.readLine());
        }

        return inputLines;
    }
}
