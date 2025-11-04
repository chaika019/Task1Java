package by.chaika19.task1.specification.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

public class BySumSpecification implements CustomArraySpecification {
    private final int minSum;
    private final int maxSum;

    public BySumSpecification(int minSum, int maxSum) {
        this.minSum = minSum;
        this.maxSum = maxSum;
    }


    @Override
    public boolean specify(CustomArray array) {
        return CustomArrayWarehouse.getInstance()
                .getStatistics(array.getId())
                .map(stats -> {
                    int sum = stats.getSum();
                    return sum >= minSum && sum <= maxSum;
                })
                .orElse(false);
    }
}
