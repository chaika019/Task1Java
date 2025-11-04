package by.chaika19.task1.sorter.stream;

import by.chaika19.task1.entity.CustomArray;
import by.chaika19.task1.exception.CustomArrayException;

public interface CustomArraySorterStream {
    CustomArray streamSort(CustomArray array) throws CustomArrayException;
}
