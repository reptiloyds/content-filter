package ru.naumov.contentfilter.output.buffer;

import ru.naumov.contentfilter.statistic.OutputStatistic;

import java.util.ArrayList;
import java.util.List;

public abstract class OutputBuffer<T> implements OutputStatistic {
    protected final List<T> list = new ArrayList<>();
    private final String fileName;

    public OutputBuffer(String outputFileName) {
        this.fileName = outputFileName;
    }

    public abstract boolean tryAppendContent(String line);

    protected void appendContent(T value) {
        list.add(value);
    }

    @Override
    public String getShortStatistic() {
        return "";
    }

    @Override
    public abstract String getFullStatistic();
}
