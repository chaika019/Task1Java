package task1.by.chaika19.specification;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.Optional;

public class ByMinSpecification implements CustomArraySpecification {
    private final int minMin;
    private final int maxMin;

    public ByMinSpecification(int minMax, int maxMax) {
        this.minMin = minMax;
        this.maxMin = maxMax;
    }

    @Override
    public boolean specify(CustomArray array) {
        CustomArrayWarehouse customArrayWarehouse = CustomArrayWarehouse.getInstance();
        Optional<CustomArrayStatistics> optionalStats = customArrayWarehouse.getStatistics(array.getId());
        if (optionalStats.isPresent()) {
            int min = optionalStats.get().getMin();
            return min >= minMin && min <= maxMin;
        }

        return false;
    }
}
