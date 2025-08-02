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
        int minLength = Integer.MAX_VALUE;
        String minLengthStr = null;
        int maxLength = Integer.MIN_VALUE;
        String maxLengthStr = null;

        for (String value : list) {
            int length = value.length();
            if (length < minLength) {
                minLength = length;
                minLengthStr = value;
            }
            if (length > maxLength) {
                maxLength = length;
                maxLengthStr = value;
            }
        }

        if (list.isEmpty()) {
            return "";
        } else {
            return "Min length string: " + minLengthStr + "\n"
                    + "Max length string: " + maxLengthStr;
        }
    }
}
