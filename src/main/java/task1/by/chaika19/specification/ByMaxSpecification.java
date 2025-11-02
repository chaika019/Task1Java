package task1.by.chaika19.specification;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.Optional;

public class ByMaxSpecification implements CustomArraySpecification {
    private final int minMax;
    private final int maxMax;

    public ByMaxSpecification(int minMax, int maxMax) {
        this.minMax = minMax;
        this.maxMax = maxMax;
    }

    @Override
    public boolean specify(CustomArray array) {
        CustomArrayWarehouse customArrayWarehouse = CustomArrayWarehouse.getInstance();
        Optional<CustomArrayStatistics> optionalStats = customArrayWarehouse.getStatistics(array.getId());
        if (optionalStats.isPresent()) {
            int max = optionalStats.get().getMax();
            return max >= minMax && max <= maxMax;
        }
        return false;
    }
}
