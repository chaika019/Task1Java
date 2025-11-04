package by.chaika19.task1.repository;

import by.chaika19.task1.observer.impl.CustomArrayStatisticsUpdater;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.specification.CustomArraySpecification;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;

import java.util.*;
import java.util.stream.Collectors;

public class CustomArrayRepository {
    private static final Logger logger = LogManager.getLogger(CustomArrayRepository.class);

    private static CustomArrayRepository instance;

    private final List<CustomArray> arrays = new ArrayList<>();

    private final CustomArrayStatisticsUpdater updater = CustomArrayStatisticsUpdater.getInstance();

    private CustomArrayRepository() {}

    public static CustomArrayRepository getInstance() {
        if (instance == null) {
            instance = new CustomArrayRepository();
            logger.info("ArrayRepository instance created (Singleton)");
        }
        return instance;
    }

    public boolean add(CustomArray array) {
        if (array == null) {
            logger.warn("Attempted to add null array");
            return false;
        }

        boolean alreadyExists = arrays.stream()
                .anyMatch(existingArray -> existingArray.getId().equals(array.getId()));

        if (!alreadyExists) {
            array.attach(updater);
            arrays.add(array);
            updater.parameterChanged(array);

            logger.info("Added array ID {} and subscribed Warehouse for updates", array.getId());
            return true;
        }

        logger.warn("Attempted to add duplicate array with ID {}", array.getId());
        return false;
    }

    public Optional<CustomArray> get(String id) {
        return arrays.stream()
                .filter(array -> array.getId().equals(id))
                .findFirst();
    }

    public List<CustomArray> getAll() {
        return new ArrayList<>(arrays);
    }

    public List<CustomArray> query(CustomArraySpecification spec) {
        List<CustomArray> allArrays = this.getAll();

        return allArrays.stream()
                .filter(spec::specify)
                .collect(Collectors.toList());
    }

    public Optional<CustomArray> remove(String id) {
        Optional<CustomArray> result = get(id);

        if (result.isPresent()) {
            CustomArray array = result.get();

            arrays.remove(array);

            CustomArrayWarehouse warehouse = CustomArrayWarehouse.getInstance();
            array.detach(updater);
            warehouse.removeStatistics(id);
            logger.info("Removed array with ID {} and successfully detached Warehouse", id);
        } else {
            logger.warn("Attempted to remove non-existent array with ID {}", id);
        }
        return result;
    }

    public List<CustomArray> sort(Comparator<CustomArray> comparator) {
        List<CustomArray> sortedList = this.getAll();
        sortedList.sort(comparator);
        return sortedList;
    }
}
