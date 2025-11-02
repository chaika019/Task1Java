package task1.by.chaika19.specification;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.Optional;

public class ByAverageSpecification implements CustomArraySpecification {
    private final int minAvg;
    private final int maxAvg;

    public ByAverageSpecification(int minAvg, int maxAvg) {
        this.minAvg = minAvg;
        this.maxAvg = maxAvg;
    }

    @Override
    public boolean specify(CustomArray array) {
        CustomArrayWarehouse customArrayWarehouse = CustomArrayWarehouse.getInstance();
        Optional<CustomArrayStatistics> optionalStats = customArrayWarehouse.getStatistics(array.getId());
        if (optionalStats.isPresent()) {
            double avg = optionalStats.get().getAverage();
            return avg >= minAvg && avg <= maxAvg;
        }
        return false;
    }
}
