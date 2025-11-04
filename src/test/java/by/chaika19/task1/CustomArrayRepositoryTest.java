package by.chaika19.task1;

import by.chaika19.task1.comparator.SortBySumComparator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.repository.CustomArrayRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class CustomArrayRepositoryTest {

    private CustomArrayRepository repository;
    private CustomArray arrayA;
    private CustomArray arrayB;
    private CustomArray arrayC;
    @BeforeEach
    void setUp()  {
        repository = CustomArrayRepository.getInstance();

        arrayA = CustomArray.builder().withArray(new int[]{1, 10, 3}).build();
        arrayB = CustomArray.builder().withArray(new int[]{-5, 5, 100, 2}).build();
        arrayC = CustomArray.builder().withArray(new int[]{15, 20}).build();

        repository.add(arrayA);
        repository.add(arrayB);
        repository.add(arrayC);
    }

    @AfterEach
    void tearDown() {
        repository.remove(arrayA.getId());
        repository.remove(arrayB.getId());
        repository.remove(arrayC.getId());
    }

    @Test
    void testGetInstance_IsSingleton() {
        CustomArrayRepository anotherRepository = CustomArrayRepository.getInstance();
        assertSame(repository, anotherRepository, "Repository must be a Singleton instance");
    }

    @Test
    void testAdd_SuccessfulAdd() {
        assertEquals(3, repository.getAll().size(), "Repository size should be 3 after setup");

        CustomArray arrayD = CustomArray.builder().withArray(new int[]{1, 2}).build();
        boolean result = repository.add(arrayD);

        assertTrue(result, "Add operation should succeed for a new array");
        assertEquals(4, repository.getAll().size(), "Repository size should increase to 4");

        repository.remove(arrayD.getId());
    }

    @Test
    void testAdd_DuplicateFails() {
        boolean result = repository.add(arrayA);

        assertFalse(result, "Add operation should fail for a duplicate ID");
        assertEquals(3, repository.getAll().size(), "Repository size should remain 3");
    }

    @Test
    void testGet_SuccessfulRetrieval() {
        Optional<CustomArray> found = repository.get(arrayB.getId());

        assertTrue(found.isPresent(), "Should find array B by its ID.");
        assertEquals(arrayB.getId(), found.get().getId(), "The found array must be array B");
    }

    @Test
    void testGet_UnsuccessfulRetrieval() {
        Optional<CustomArray> found = repository.get("non-existent-id");

        assertFalse(found.isPresent(), "Should not find an array with a non-existent ID");
    }

    @Test
    void testGetAll() {
        List<CustomArray> allArrays = repository.getAll();

        assertEquals(3, allArrays.size(), "Should retrieve all 3 arrays added in setUp");

        List<String> ids = allArrays.stream().map(CustomArray::getId).toList();
        assertTrue(ids.contains(arrayA.getId()));
        assertTrue(ids.contains(arrayB.getId()));
        assertTrue(ids.contains(arrayC.getId()));
    }

    @Test
    void testRemove_SuccessfulRemoval() {
        Optional<CustomArray> removed = repository.remove(arrayC.getId());

        assertTrue(removed.isPresent(), "Remove should return Optional with the removed array");
        assertEquals(2, repository.getAll().size(), "Repository size should decrease to 2");

        Optional<CustomArray> found = repository.get(arrayC.getId());
        assertFalse(found.isPresent(), "The removed array should not be found anymore");
    }

    @Test
    void testRemove_UnsuccessfulRemoval() {
        Optional<CustomArray> removed = repository.remove("another-non-existent-id");

        assertFalse(removed.isPresent(), "Remove should return empty Optional for non-existent ID");
        assertEquals(3, repository.getAll().size(), "Repository size should remain 3");
    }

    @Test
    void testSort() {
        Comparator<CustomArray> sumComparator = new SortBySumComparator();
        List<CustomArray> sortedList = repository.sort(sumComparator);

        assertEquals(3, sortedList.size(), "Sorted list should contain 3 elements");

        assertEquals(arrayA.getId(), sortedList.get(0).getId(), "First element should be A (Sum=14)");
        assertEquals(arrayC.getId(), sortedList.get(1).getId(), "Second element should be C (Sum=35)");
        assertEquals(arrayB.getId(), sortedList.get(2).getId(), "Third element should be B (Sum=102)");
    }
}
