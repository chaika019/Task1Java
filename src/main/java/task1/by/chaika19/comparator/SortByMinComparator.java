package task1.by.chaika19.comparator;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.Comparator;
import java.util.Optional;

public class SortByMinComparator implements Comparator<CustomArray> {
    @Override
    public int compare(CustomArray o1, CustomArray o2) {
        CustomArrayWarehouse customArrayWarehouse = CustomArrayWarehouse.getInstance();

        Optional<CustomArrayStatistics> stats1 = customArrayWarehouse.getStatistics(o1.getId());
        Optional<CustomArrayStatistics> stats2 = customArrayWarehouse.getStatistics(o2.getId());

        if (stats1.isEmpty() || stats2.isEmpty()) {
            return 0;
        }

        return Integer.compare(stats1.get().getMin(), stats2.get().getMin());
    }
}
