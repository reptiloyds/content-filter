package ru.naumov.contentfilter.app;

import picocli.CommandLine;

import static picocli.CommandLine.*;

public class EntryPoint {

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ContentFilter()).execute(args);
        System.exit(exitCode);
    }
}