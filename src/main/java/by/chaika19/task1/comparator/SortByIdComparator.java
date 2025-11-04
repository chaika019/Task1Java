package by.chaika19.task1.comparator;

import by.chaika19.task1.entity.CustomArray;

import java.util.Comparator;

public class SortByIdComparator implements Comparator<CustomArray> {
    @Override
    public int compare(CustomArray o1, CustomArray o2) {
        return o1.getId().compareTo(o2.getId());
    }
}
