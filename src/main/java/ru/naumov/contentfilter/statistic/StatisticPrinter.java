package ru.naumov.contentfilter.statistic;

public class StatisticPrinter {
    private final boolean shortStatistic;
    private final boolean fullStatistic;
    private final static String STATISTIC_PREFIX = "Statistic ";

    public StatisticPrinter(boolean shortStatistic, boolean fullStatistic) {
        this.shortStatistic = shortStatistic;
        this.fullStatistic = fullStatistic;
    }

    public void print(OutputStatistic statistic) {
        if (!shortStatistic && !fullStatistic) return;

        System.out.println(STATISTIC_PREFIX + statistic.getStatisticLabel());
        System.out.println(statistic.getShortStatistic());
        if (fullStatistic)
            System.out.println(statistic.getFullStatistic());
        System.out.println();
    }
}
