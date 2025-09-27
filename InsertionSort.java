import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

/**
 * InsertionSort with optimizations for nearly-sorted data.
 * Includes metrics collection and CLI interface.
 */
public class InsertionSort {

    private static long comparisons = 0;
    private static long swaps = 0;
    private static long arrayAccesses = 0;

    /**
     * Performs optimized insertion sort on the given array.
     * @param arr input array
     * @throws IllegalArgumentException if arr is null
     */
    public static void insertionSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array cannot be null");
        }

        int n = arr.length;

        for (int i = 1; i < n; i++) {
            int current = arr[i];
            arrayAccesses++;
            int j = i - 1;

            comparisons++;
            if (arr[j] <= current) {
                arrayAccesses++;
                continue;
            }

            while (j >= 0) {
                comparisons++;
                arrayAccesses++;
                if (arr[j] > current) {
                    arr[j + 1] = arr[j];
                    arrayAccesses += 2;
                    swaps++;
                    j--;
                } else {
                    break;
                }
            }
            arr[j + 1] = current;
            arrayAccesses++;
        }
    }

    /**
     * Resets metrics for a new run.
     */
    public static void resetMetrics() {
        comparisons = swaps = arrayAccesses = 0;
    }

    public static void printMetrics() {
        System.out.println("Comparisons: " + comparisons);
        System.out.println("Swaps: " + swaps);
        System.out.println("Array Accesses: " + arrayAccesses);
    }

    /**
     * CLI Interface
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter array size (e.g., 10, 100, 1000): ");
        int n = sc.nextInt();

        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(10000);
        }

        System.out.println("Original Array (first 20 elements): " + Arrays.toString(Arrays.copyOf(arr, Math.min(n, 20))));

        resetMetrics();
        long start = System.nanoTime();
        insertionSort(arr);
        long end = System.nanoTime();

        System.out.println("Sorted Array (first 20 elements): " + Arrays.toString(Arrays.copyOf(arr, Math.min(n, 20))));
        printMetrics();
        System.out.println("Execution Time (ms): " + (end - start) / 1_000_000.0);
    }
}
