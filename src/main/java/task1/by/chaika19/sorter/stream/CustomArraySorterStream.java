package task1.by.chaika19.sorter.stream;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.exception.CustomArrayException;

public interface CustomArraySorterStream {
    CustomArray streamSort(CustomArray array) throws CustomArrayException;
}
