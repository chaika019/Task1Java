package by.chaika19.task1.observer.impl;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.entity.CustomArrayStatistics;
import by.chaika19.task1.exception.CustomArrayException;
import by.chaika19.task1.observer.CustomArrayObserver;
import by.chaika19.task1.service.classic.CustomArrayCalculator;
import by.chaika19.task1.service.classic.impl.CustomArrayCalculatorImpl;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomArrayStatisticsUpdater implements CustomArrayObserver {
    private static final Logger logger = LogManager.getLogger(CustomArrayStatisticsUpdater.class);

    private static CustomArrayStatisticsUpdater instance;

    private final CustomArrayWarehouse warehouse = CustomArrayWarehouse.getInstance();
    private final CustomArrayCalculator calculator = new CustomArrayCalculatorImpl();

    public static CustomArrayStatisticsUpdater getInstance() {
        if (instance == null) {
            instance = new CustomArrayStatisticsUpdater();
            logger.info("CustomArrayStatisticsUpdater instance created (Singleton)");
        }
        return instance;
    }
    @Override
    public void parameterChanged(CustomArray customArray) {
        if(customArray == null) {
            logger.warn("CustomArray is null");
            return;
        }

        String id = customArray.getId();
        try {
            int min = calculator.findMin(customArray);
            int max = calculator.findMax(customArray);
            int sum = calculator.calculateSum(customArray);
            double average = calculator.calculateAverage(customArray);

            CustomArrayStatistics statistics = new CustomArrayStatistics(min, max, sum, average);
            warehouse.updateStatistics(id, statistics);

            logger.info("Updated statistics for array ID {}: {}", id, statistics);
        } catch (CustomArrayException e) {
            logger.error("Error calculating statistics for array ID {}. Removing statistics.", id, e);
            warehouse.removeStatistics(id);
        }
    }
}
