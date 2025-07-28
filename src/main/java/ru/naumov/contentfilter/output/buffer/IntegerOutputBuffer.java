package ru.naumov.contentfilter.output.buffer;

public final class IntegerOutputBuffer extends OutputBuffer<Long> {
    private static final String FILE_NAME = "integers";

    public IntegerOutputBuffer() {
        super(FILE_NAME);
    }

    @Override
    public boolean tryAppendContent(String line) {
        try {
            Long value = Long.parseLong(line);
            appendContent(value);
            return true;
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    @Override
    public String getFullStatistic() {
        return "";
    }
}
