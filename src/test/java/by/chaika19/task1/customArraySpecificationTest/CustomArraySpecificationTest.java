package by.chaika19.task1.customArraySpecificationTest;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.repository.CustomArrayRepository;
import by.chaika19.task1.repository.specification.CustomArraySpecification;
import by.chaika19.task1.repository.specification.impl.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CustomArraySpecificationTest {

    private CustomArrayRepository repository;
    private CustomArray arrayA;
    private CustomArray arrayB;
    private CustomArray arrayC;

    @BeforeEach
    void setUp() {
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

    private List<String> getIds(List<CustomArray> list) {
        return list.stream().map(CustomArray::getId).collect(Collectors.toList());
    }

    @Test
    void queryByIdSpecification() {
        CustomArraySpecification spec = new ByIdSpecification(arrayB.getId());
        List<CustomArray> result = repository.query(spec);

        assertEquals(1, result.size(), "Should find exactly one array by ID.");
        assertEquals(arrayB.getId(), result.get(0).getId(), "The found array must be arrayB.");
    }

    @Test
    void queryBySumSpecification() {
        CustomArraySpecification spec = new BySumSpecification(30, 105);
        List<CustomArray> result = repository.query(spec);
        List<String> resultIds = getIds(result);

        assertEquals(2, result.size(), "Should find arrays B (102) and C (35).");
        assertTrue(resultIds.contains(arrayB.getId()));
        assertTrue(resultIds.contains(arrayC.getId()));
    }

    @Test
    void queryByMaxSpecification() {
        CustomArraySpecification spec = new ByMaxSpecification(50, 100);
        List<CustomArray> result = repository.query(spec);
        List<String> resultIds = getIds(result);

        assertEquals(1, result.size(), "Should find only array B (Max=100).");
        assertTrue(resultIds.contains(arrayB.getId()));
    }

    @Test
    void queryByMinSpecification() {
        CustomArraySpecification spec = new ByMinSpecification(0, 10);
        List<CustomArray> result = repository.query(spec);
        List<String> resultIds = getIds(result);

        assertEquals(1, result.size(), "Should find only array A (Min=1).");
        assertTrue(resultIds.contains(arrayA.getId()));
    }

    @Test
    void queryByAverageSpecification() {
        CustomArraySpecification spec = new ByAverageSpecification(10, 30);
        List<CustomArray> result = repository.query(spec);
        List<String> resultIds = getIds(result);

        assertEquals(2, result.size(), "Should find arrays B (Avg=25.5) and C (Avg=17.5).");
        assertTrue(resultIds.contains(arrayB.getId()));
        assertTrue(resultIds.contains(arrayC.getId()));
    }
}
