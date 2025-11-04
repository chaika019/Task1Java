package by.chaika19.task1.repository.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.repository.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

public class ByAverageSpecification implements CustomArraySpecification {
    private final double minAvg;
    private final double maxAvg;

    public ByAverageSpecification(double minAvg, double maxAvg) {
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
