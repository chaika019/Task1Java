package task1.by.chaika19.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.specification.CustomArraySpecification;
import task1.by.chaika19.warehouse.CustomArrayWarehouse;

import java.util.*;
import java.util.stream.Collectors;

public class CustomArrayRepository {
    private static final Logger logger = LogManager.getLogger(CustomArrayRepository.class);

    private static CustomArrayRepository instance;

    private final Map<String, CustomArray> arrays = new HashMap<>();

    private CustomArrayRepository() {}

    public CustomArrayWarehouse getWarehouse() {
        return CustomArrayWarehouse.getInstance();
    }

    public List<CustomArray> query(CustomArraySpecification spec) {
        List<CustomArray> allArrays = this.getAll();

        return allArrays.stream()
                .filter(spec::specify)
                .collect(Collectors.toList());
    }

    public static CustomArrayRepository getInstance() {
        if (instance == null) {
            instance = new CustomArrayRepository();
            logger.info("ArrayRepository instance created (Singleton)");
        }
        return instance;
    }

    public Optional<CustomArray> get(String id) {
        return Optional.ofNullable(arrays.get(id));
    }

    public List<CustomArray> getAll() {
        return new ArrayList<>(arrays.values());
    }

    public boolean add(CustomArray array) {
        if (array != null && !arrays.containsKey(array.getId())) {
            CustomArrayWarehouse warehouse = CustomArrayWarehouse.getInstance();
            array.attach(warehouse);
            arrays.put(array.getId(), array);
            warehouse.updateStatistics(array);

            logger.info("Added array ID {} and subscribed Warehouse for updates", array.getId());
            return true;
        }
        logger.warn("Attempted to add null or duplicate array");
        return false;
    }

    public Optional<CustomArray> remove(String id) {
        CustomArray removedArray = arrays.remove(id);

        Optional<CustomArray> result = Optional.ofNullable(removedArray);

        if (result.isPresent()) {
            CustomArrayWarehouse warehouse = CustomArrayWarehouse.getInstance();
            CustomArray array = result.get();

            array.detach(warehouse);

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
