package task1.by.chaika19.specification;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.Optional;

public class BySumSpecification implements CustomArraySpecification{
    private final int minSum;
    private final int maxSum;

    public BySumSpecification(int minSum, int maxSum) {
        this.minSum = minSum;
        this.maxSum = maxSum;
    }


    @Override
    public boolean specify(CustomArray array) {
        CustomArrayWarehouse customArrayWarehouse = CustomArrayWarehouse.getInstance();

        Optional<CustomArrayStatistics> optionalStats = customArrayWarehouse.getStatistics(array.getId());
        if (optionalStats.isPresent()) {
            int sum = optionalStats.get().getSum();
            return sum >= minSum && sum <= maxSum;
        }
        return false;
    }
}
