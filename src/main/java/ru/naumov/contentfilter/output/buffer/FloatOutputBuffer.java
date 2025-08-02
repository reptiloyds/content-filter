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
        double minValue = Double.MAX_VALUE;
        double maxValue = Double.MIN_VALUE;
        double sum = 0;
        double median = 0;

        for (double value : list) {
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
