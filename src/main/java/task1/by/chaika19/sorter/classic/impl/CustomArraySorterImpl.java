package task1.by.chaika19.sorter.classic.impl;

import task1.by.chaika19.entity.CustomArray;
import task1.by.chaika19.sorter.classic.CustomArraySorter;

public class CustomArraySorterImpl implements CustomArraySorter {
    @Override
    public CustomArray bubbleSort(CustomArray array) {
        int[] arr = array.getArray();
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            if (!swapped) break;
        }
        return CustomArray.builder()
                .withArray(arr)
                .build();
    }

    @Override
    public CustomArray sortInsertion(CustomArray array) {
        int[] arr = array.getArray();
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        return CustomArray.builder()
                .withArray(arr)
                .build();
    }

    @Override
    public CustomArray sortQuick(CustomArray array) {
        int[] arr = array.getArray();
        quickSortRecursive(arr, 0, arr.length - 1);
        return CustomArray.builder()
                .withArray(arr)
                .build();
    }

    public void quickSortRecursive(int[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);


            quickSortRecursive(array, low, pi - 1);
            quickSortRecursive(array, pi + 1, high);
        }
    }

    public int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;
        for (int j = low; j < high; j++) {
            if (array[j] < pivot) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
