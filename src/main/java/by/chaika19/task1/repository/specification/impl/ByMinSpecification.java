package by.chaika19.task1.repository.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.repository.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

public class ByMinSpecification implements CustomArraySpecification {
    private final int minMin;
    private final int maxMin;

    public ByMinSpecification(int minMax, int maxMax) {
        this.minMin = minMax;
        this.maxMin = maxMax;
    }

    @Override
    public boolean specify(CustomArray array) {
        return CustomArrayWarehouse.getInstance()
                .getStatistics(array.getId())
                .map(stats -> {
                    int min = stats.getMin();
                    return min >= minMin && min <= maxMin;
                })
                .orElse(false);
    }
}
