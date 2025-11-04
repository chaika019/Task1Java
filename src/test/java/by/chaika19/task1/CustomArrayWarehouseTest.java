package by.chaika19.task1;

import by.chaika19.task1.entity.CustomArrayStatistics;
import by.chaika19.task1.warehouse.CustomArrayWarehouse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayWarehouseTest {

    private CustomArrayWarehouse warehouse;
    private final String TEST_ID_1 = "test-id-1";

    private CustomArrayStatistics stats1;

    @BeforeEach
    public void setUp() throws NoSuchFieldException, IllegalAccessException {
        Field instanceField = CustomArrayWarehouse.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);

        warehouse = CustomArrayWarehouse.getInstance();

        Field mapField = CustomArrayWarehouse.class.getDeclaredField("statisticsMap");
        mapField.setAccessible(true);
        Map<?, ?> map = (Map<?, ?>) mapField.get(warehouse);
        map.clear();

        stats1 = new CustomArrayStatistics(100, 50, 5, 10.0);
    }

    @Test
    public void testGetInstance_IsSingleton() {
        CustomArrayWarehouse anotherInstance = CustomArrayWarehouse.getInstance();
        assertSame(warehouse, anotherInstance, "CustomArrayWarehouse must be a singleton");
    }

    @Test
    public void testAddGetRemoveCycle() {
        warehouse.updateStatistics(TEST_ID_1, stats1);
        Optional<CustomArrayStatistics> retrievedStats = warehouse.getStatistics(TEST_ID_1);

        assertTrue(retrievedStats.isPresent(), "Statistics must be found after addition");
        assertEquals(stats1, retrievedStats.get(), "Retrieved statistics must match added ones");

        warehouse.removeStatistics(TEST_ID_1);
        retrievedStats = warehouse.getStatistics(TEST_ID_1);

        assertTrue(retrievedStats.isEmpty(), "Statistics must be absent after removal");
    }

    @Test
    public void testUpdateStatistics_Overwrite() {
        warehouse.updateStatistics(TEST_ID_1, stats1);

        CustomArrayStatistics updatedStats = new CustomArrayStatistics(999, 1000, -100, 50.5);

        warehouse.updateStatistics(TEST_ID_1, updatedStats);

        Optional<CustomArrayStatistics> retrievedStats = warehouse.getStatistics(TEST_ID_1);

        assertEquals(updatedStats, retrievedStats.get(), "The statistics object must be successfully overwritten");
    }

    @Test
    public void testEdgeCases() {
        Optional<CustomArrayStatistics> retrievedStats = warehouse.getStatistics("non-existent-id");
        assertTrue(retrievedStats.isEmpty(), "Getting a non-existent ID must return empty Optional");

        assertDoesNotThrow(() -> warehouse.removeStatistics("fake-id"),
                "Removing a non-existent ID should not throw an exception");
    }
}
