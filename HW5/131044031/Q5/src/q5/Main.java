package q5;

import java.util.ArrayList;
import java.util.Random;

/**
 * Collect data about the average running-time of each algorithm discussed in
 * class.Create arrays of random integers and then measure the time it takes to
 * sort for each algorithm.Repeat this process, for at least 10 different size
 * of arrays and at least 10 times for each array size.
 *
 * @author sefanadir
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ArrayList<Integer> sizeSets = new ArrayList<>();
        sizeSets.add(100);
        sizeSets.add(1000);
        sizeSets.add(5000);
        sizeSets.add(10000);

        Integer[] testArray = null;
        Random rand = new Random();
        long worstcase_1 = 0, worstcase_2 = 0, worstcase_3 = 0, worstcase_4 = 0;
        int index_1 = 0, index_2 = 0, index_3 = 0, index_4 = 0;
        for (int i = 0; i < sizeSets.size(); ++i) {

            testArray = new Integer[sizeSets.get(i)];

            for (int j = 0; j < testArray.length; ++j) {
                testArray[j] = rand.nextInt(5000);
            }

            long start_1 = System.nanoTime();
            HeapSort.sort(testArray);
            long end_1 = System.nanoTime();

            if ((end_1 - start_1) > worstcase_1) {
                worstcase_1 = (end_1 - start_1);
                index_1 = i;
            }

            long start_2 = System.nanoTime();
            InsertionSort.sort(testArray);
            long end_2 = System.nanoTime();

            if ((end_2 - start_2) > worstcase_2) {
                worstcase_2 = (end_2 - start_2);
                index_2 = i;
            }

            long start_3 = System.nanoTime();
            MergeSort.sort(testArray);
            long end_3 = System.nanoTime();

            if ((end_3 - start_3) > worstcase_3) {
                worstcase_3 = (end_3 - start_3);
                index_3 = i;
            }

            long start_4 = System.nanoTime();
            QuickSort.sort(testArray);
            long end_4 = System.nanoTime();

            if ((end_4 - start_4) > worstcase_4) {
                worstcase_4 = (end_4 - start_4);
                index_4 = i;
            }
        }
        System.out.println("Heap Sort Worst Case     : size->" + sizeSets.get(index_1) + " time->" + worstcase_1);
        System.out.println("Insertion Sort Worst Case: size->" + sizeSets.get(index_2) + " time->" + worstcase_2);
        System.out.println("Merge Sort Worst Case    : size->" + sizeSets.get(index_3) + " time->" + worstcase_3);
        System.out.println("Quick Sort Worst Case    : size->" + sizeSets.get(index_4) + " time->" + worstcase_4);
    }

}
