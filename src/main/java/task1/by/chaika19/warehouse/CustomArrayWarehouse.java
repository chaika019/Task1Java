package task1.by.chaika19.warehouse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.entity.CustomArrayStatistics;
import task1.by.chaika19.exception.CustomArrayException;
import task1.by.chaika19.observer.CustomArrayObserver;
import task1.by.chaika19.service.classic.CustomArrayCalculator;
import task1.by.chaika19.service.classic.impl.CustomArrayCalculatorImpl;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CustomArrayWarehouse implements CustomArrayObserver {
    private static final Logger logger = LogManager.getLogger(CustomArrayWarehouse.class);

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

    private Map<String, CustomArrayStatistics> statisticsMap = new HashMap<>();

    public Optional<CustomArrayStatistics> getStatistics(String id) {
        return Optional.ofNullable(statisticsMap.get(id));
    }

    @Override
    public void parameterChanged(CustomArray customArray) {
        updateStatistics(customArray);
    }

    public void updateStatistics(CustomArray customArray) {
        if(customArray == null) {
            logger.warn("CustomArray is null");
            return;
        }

        CustomArrayCalculator calculator = new CustomArrayCalculatorImpl();
        String id = customArray.getId();
        try {
            int min = calculator.findMin(customArray);
            int max = calculator.findMax(customArray);
            int sum = calculator.calculateSum(customArray);
            double average = calculator.calculateAverage(customArray);

            CustomArrayStatistics statistics = new CustomArrayStatistics(min, max, sum, average);
            statisticsMap.put(id, statistics);

            logger.info("Updated statistics for array ID {}: {}", id, statistics);
        } catch (CustomArrayException e) {
            logger.error("Error calculating statistics for array ID {}. Removing statistics", id, e);
            statisticsMap.remove(id);
        }
    }

    public void removeStatistics(String arrayId) {
        if (statisticsMap.remove(arrayId) != null) {
            logger.info("Removed statistics for array ID {}", arrayId);
        }
    }
}
