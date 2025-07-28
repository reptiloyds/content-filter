package ru.naumov.contentfilter.output.buffer;

public final class FloatOutputBuffer extends OutputBuffer<Double> {
    private static final String FILE_NAME = "floats";

    public FloatOutputBuffer() {
        super(FILE_NAME);
    }

    @Override
    public boolean tryAppendContent(String line) {
        try {
            //TODO CHECK WITH FLOAT
            Double value = Double.parseDouble(line);
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
