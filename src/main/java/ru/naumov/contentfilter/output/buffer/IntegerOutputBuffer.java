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
    protected void appendContent(Long value) {
        super.appendContent(value);

    }

    @Override
    public String getFullStatistic() {
        String result = null;
        long minValue = Long.MAX_VALUE;
        long maxValue = Long.MIN_VALUE;
        long sum = 0;
        long median = 0;

        for (long value : list) {
            if (value < minValue) {
                minValue = value;
            }
            if (value > maxValue) {
                maxValue = value;
            }

            sum += value;
        }

        if (list.isEmpty()) {
            return "";
        } else {
            median = sum / list.size();
            return "Min value: " + minValue + "\n"
                    + "Max value: " + maxValue + "\n"
                    + "Sum value: " + sum + "\n"
                    + "Median value: " + median;
        }
    }
}
