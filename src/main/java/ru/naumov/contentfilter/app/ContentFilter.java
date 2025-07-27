package ru.naumov.contentfilter.app;

import ru.naumov.contentfilter.input.FileReader;

import java.io.File;
import java.util.List;

import static picocli.CommandLine.*;

@Command(name = "content-filter", mixinStandardHelpOptions = true)
public class ContentFilter implements Runnable {

    @Option(names = "-o", description = "Output path")
    private String outputPath;
    @Option(names = "-p", description = "Output file prefix")
    private String outputPrefix;
    @Option(names = "-s", description = "Short statistic")
    private boolean shortStatistic;
    @Option(names = "-f", description = "Full statistic")
    private boolean fullStatistic;
    @Option(names = "-a", description = "Append to existing files")
    private boolean appendToExistingFiles;
    @Parameters(paramLabel = "INPUT_FILES", description = "One or more files to handle")
    private String[] inputFiles;

    private final FileReader fileReader = new FileReader();

    @Override
    public void run() {
        for (String fileName : inputFiles) {
            HandleFile(fileName);
        }
    }

    public void HandleFile(String fileName) {
        List<String> lines = fileReader.ReadFile(fileName);
        for (String line : lines) {
            System.out.println(line);
        }
    }

    public void HandleLine(){

    }
}
