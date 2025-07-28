package ru.naumov.contentfilter.output.buffer;

public final class StringOutputBuffer extends OutputBuffer<String> {
    private static final String FILE_NAME = "strings";

    public StringOutputBuffer() {
        super(FILE_NAME);
    }

    @Override
    public boolean tryAppendContent(String line) {
        appendContent(line);
        return true;
    }

    @Override
    public String getFullStatistic() {
        return "";
    }
}
