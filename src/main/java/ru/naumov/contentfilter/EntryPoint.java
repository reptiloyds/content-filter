package ru.naumov.contentfilter;

import picocli.CommandLine;
import static picocli.CommandLine.*;

@Command(name="content-filter", mixinStandardHelpOptions = true)
public class EntryPoint implements Runnable {

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

    public static void main(String[] args) {
        int exitCode = new CommandLine(new EntryPoint()).execute(args);
        System.exit(exitCode);
    }

    @Override
    public void run() {
        System.out.println(outputPath);
        System.out.println(outputPrefix);
        for (String inputFile : inputFiles) {
            System.out.println(inputFile);
        }
    }
}