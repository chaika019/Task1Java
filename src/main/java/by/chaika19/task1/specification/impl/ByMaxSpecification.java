package by.chaika19.task1.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

public class ByMaxSpecification implements CustomArraySpecification {
    private final int minMax;
    private final int maxMax;

    public ByMaxSpecification(int minMax, int maxMax) {
        this.minMax = minMax;
        this.maxMax = maxMax;
    }

    @Override
    public boolean specify(CustomArray array) {
        return CustomArrayWarehouse.getInstance()
                .getStatistics(array.getId())
                .map(stats -> {
                    int max = stats.getMax();
                    return max >= minMax && max <= maxMax;
                })
                .orElse(false);
    }
}
