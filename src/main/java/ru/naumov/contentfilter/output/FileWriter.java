package ru.naumov.contentfilter.output;

import ru.naumov.contentfilter.output.buffer.OutputBuffer;
import ru.naumov.contentfilter.pathvalidation.PathValidator;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class FileWriter {
    private final String outputPathString;
    private final String fileNamePrefix;
    private final boolean appendToFile;
    private Path outputPath;

    private static final String FILE_TYPE = ".txt";
    private static final String OUTPUT_DIRECTORY = "Output directory";
    private static final String FILE_NAME_PART = "File name part";

    public FileWriter(String outputPathString, String fileNamePrefix, boolean appendToFile) {
        this.outputPathString = Objects.requireNonNullElse(outputPathString, "");
        this.fileNamePrefix = Objects.requireNonNullElse(fileNamePrefix, "");
        this.appendToFile = appendToFile;
    }

    public void initialize() throws IllegalArgumentException{
        String[] dirs = outputPathString.trim().split("[/\\\\]+");
        for (String dir : dirs) {
            PathValidator.validateDirectionPart(dir, OUTPUT_DIRECTORY);
        }
        outputPath = Path.of(".", dirs);
        PathValidator.validateFileNamePart(fileNamePrefix, FILE_NAME_PART);
    }

    public void writeFile(OutputBuffer<?> outputBuffer) throws IOException {
        List<String> outputBufferLines = outputBuffer.asLines();
        if (outputBufferLines.isEmpty()) return;

        String path = getPath(outputBuffer.getFileName());
        Path fullPath = Path.of(path);
        Path parentDir = fullPath.getParent();

        if (parentDir != null && !Files.exists(parentDir)) {
            Files.createDirectories(parentDir);
        }
        FileOutputStream outputStream = new FileOutputStream(path, appendToFile);

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream));

        for (String line : outputBufferLines) {
            writer.write(line);
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }

    private String getPath(String file) {
        String fileName = fileNamePrefix + file + FILE_TYPE;
        return outputPath.resolve(fileName).toString();
    }
}
