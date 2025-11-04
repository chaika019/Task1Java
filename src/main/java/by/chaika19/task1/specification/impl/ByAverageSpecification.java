package by.chaika19.task1.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

public class ByAverageSpecification implements CustomArraySpecification {
    private final int minAvg;
    private final int maxAvg;

    public ByAverageSpecification(int minAvg, int maxAvg) {
        this.minAvg = minAvg;
        this.maxAvg = maxAvg;
    }

    @Override
    public boolean specify(CustomArray array) {
        return CustomArrayWarehouse.getInstance()
                .getStatistics(array.getId())
                .map(stats -> {
                    double avg = stats.getAverage();
                    return avg >= minAvg && avg <= maxAvg;
                })
                .orElse(false);
    }
}
