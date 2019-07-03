package q4;

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
        sizeSets.add(200);
        sizeSets.add(400);
        sizeSets.add(800);
        sizeSets.add(1000);
        sizeSets.add(2000);
        sizeSets.add(6000);
        sizeSets.add(12000);
        sizeSets.add(26000);
        sizeSets.add(40000);

        Integer[] testArray = null;
        Random rand = new Random();
        long average_1 = 0, average_2 = 0, average_3 = 0, average_4 = 0;
        for (int i = 0; i < sizeSets.size(); ++i) {
            System.out.println("---------" + (i + 1) + "---------");
            testArray = new Integer[sizeSets.get(i)];

            for (int j = 0; j < testArray.length; ++j) {
                testArray[j] = rand.nextInt(5000);
            }
            System.out.println("Heap Sort");
            long start_1 = System.nanoTime();
            HeapSort.sort(testArray);
            long end_1 = System.nanoTime();
            average_1 += (end_1 - start_1);
            System.out.println((i + 1) + ". run time: " + (end_1 - start_1));

            System.out.println("Insertion Sort");
            long start_2 = System.nanoTime();
            InsertionSort.sort(testArray);
            long end_2 = System.nanoTime();
            average_2 += (end_2 - start_2);
            System.out.println((i + 1) + ". run time: " + (end_2 - start_2));

            System.out.println("Merge Sort");
            long start_3 = System.nanoTime();
            MergeSort.sort(testArray);
            long end_3 = System.nanoTime();
            average_3 += (end_3 - start_3);
            System.out.println((i + 1) + ". run time: " + (end_3 - start_3));

            System.out.println("Quick Sort");
            long start_4 = System.nanoTime();
            QuickSort.sort(testArray);
            long end_4 = System.nanoTime();
            average_4 += (end_4 - start_4);
            System.out.println((i + 1) + ". run time: " + (end_4 - start_4));

        }

        System.out.println("\nHeap Sort Average Time: " + average_1 / sizeSets.size());
        System.out.println("Insertion Sort Average Time: " + average_2 / sizeSets.size());
        System.out.println("Merge Sort Average Time: " + average_3 / sizeSets.size());
        System.out.println("Quick Sort Average Time: " + average_4 / sizeSets.size());
    }
}
