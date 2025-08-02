package ru.naumov.contentfilter.app;

import ru.naumov.contentfilter.handler.LineHandler;
import ru.naumov.contentfilter.input.FileReader;
import ru.naumov.contentfilter.output.FileWriter;
import ru.naumov.contentfilter.output.buffer.OutputBuffer;
import ru.naumov.contentfilter.statistic.OutputStatistic;
import ru.naumov.contentfilter.statistic.StatisticPrinter;

import java.io.FileNotFoundException;
import java.io.IOException;
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

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private final FileReader fileReader = new FileReader();
    private final LineHandler lineHandler = new LineHandler();
    private StatisticPrinter statisticPrinter;
    private FileWriter fileWriter;

    @Override
    public void run() {
        initialize();
        handleInputFiles();
        printStatistic();
        handleOutputFiles();
    }

    private void initialize() {
        this.statisticPrinter = new StatisticPrinter(shortStatistic, fullStatistic);
        this.fileWriter = new FileWriter(outputPath, outputPrefix, appendToExistingFiles);
        try {
            this.fileWriter.initialize();
        } catch (IllegalArgumentException e) {
            System.out.println(ANSI_RED + e.getMessage() + ANSI_RESET);
            System.exit(1);
        }
    }

    private void handleInputFiles() {
        for (String fileName : inputFiles) {
            try {
                List<String> lines = fileReader.readFile(fileName);
                lineHandler.handle(lines);
            } catch (FileNotFoundException e) {
                System.out.println(ANSI_YELLOW + "File not found: " + fileName + ANSI_RESET);
            } catch (IOException e) {
                System.out.println(ANSI_RED + "Error when reading the file: " + fileName + ANSI_RESET);
            }
        }
    }

    private void printStatistic() {
        for (OutputStatistic statistic : lineHandler.readOnlyOutputBuffers) {
            statisticPrinter.print(statistic);
        }
    }

    private void handleOutputFiles() {
        for (OutputBuffer<?> outputBuffer : lineHandler.readOnlyOutputBuffers) {
            try {
                fileWriter.writeFile(outputBuffer);
            } catch (IOException e) {
                System.out.println(ANSI_RED + "Error when writing a file: " + outputBuffer.getFileName() + ANSI_RESET);
            }
        }
    }
}
