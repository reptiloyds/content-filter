package ru.naumov.contentfilter.output.buffer;

import ru.naumov.contentfilter.statistic.OutputStatistic;

import java.util.ArrayList;
import java.util.List;

public abstract class OutputBuffer<T> implements OutputStatistic {
    protected final List<T> list = new ArrayList<>();
    private final String fileName;

    public String getFileName() {
        return fileName;
    }

    public OutputBuffer(String outputFileName) {
        this.fileName = outputFileName;
    }

    public abstract boolean tryAppendContent(String line);

    public List<String> asLines() {
        ArrayList<String> lines = new ArrayList<>();
        for (T item : list) {
            lines.add(item.toString());
        }
        return lines;
    }

    @Override
    public String getStatisticLabel() {
        return fileName;
    }

    @Override
    public String getShortStatistic() {
        return "Lines: " + list.size();
    }

    @Override
    public abstract String getFullStatistic();

    protected void appendContent(T value) {
        list.add(value);
    }
}
