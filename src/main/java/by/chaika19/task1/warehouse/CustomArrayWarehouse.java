package by.chaika19.task1.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.chaika19.task1.entity.CustomArrayStatistics;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomArrayWarehouse {
    private static final Logger logger = LogManager.getLogger(CustomArrayWarehouse.class);

    private final Map<String, CustomArrayStatistics> statisticsMap = new HashMap<>();

    private static CustomArrayWarehouse instance;

    private CustomArrayWarehouse() {
    }

    public static CustomArrayWarehouse getInstance() {
        if (instance == null) {
            instance = new CustomArrayWarehouse();
            logger.info("ArrayWarehouse instance created (Singleton)");
        }
        return instance;
    }

    public Optional<CustomArrayStatistics> getStatistics(String id) {
        return Optional.ofNullable(statisticsMap.get(id));
    }

    public void updateStatistics(String id, CustomArrayStatistics stats) {
        statisticsMap.put(id, stats);
    }

    public void removeStatistics(String arrayId) {
        if (statisticsMap.remove(arrayId) != null) {
            logger.info("Removed statistics for array ID {}", arrayId);
        }
    }
}
