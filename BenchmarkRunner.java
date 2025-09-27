import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static Random random = new Random();

    // Генерация разных входных массивов
    private static int[] generateRandomArray(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(1_000_000);
        }
        return arr;
    }

    private static int[] generateSortedArray(int n) {
        int[] arr = generateRandomArray(n);
        Arrays.sort(arr);
        return arr;
    }

    private static int[] generateReverseArray(int n) {
        int[] arr = generateSortedArray(n);
        for (int i = 0; i < n / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - 1 - i] = tmp;
        }
        return arr;
    }

    private static int[] generateNearlySortedArray(int n) {
        int[] arr = generateSortedArray(n);
        // немного перемешаем (swap ~1% элементов)
        int swaps = Math.max(1, n / 100);
        for (int i = 0; i < swaps; i++) {
            int idx1 = random.nextInt(n);
            int idx2 = random.nextInt(n);
            int tmp = arr[idx1];
            arr[idx1] = arr[idx2];
            arr[idx2] = tmp;
        }
        return arr;
    }

    private static long benchmark(int[] arr) {
        long start = System.nanoTime();
        InsertionSort.insertionSort(arr);
        return (System.nanoTime() - start) / 1_000_000; // в миллисекундах
    }

    public static void main(String[] args) {
        int[] sizes = {100, 1000, 10000, 100000};
        String[] types = {"random", "sorted", "reverse", "nearly_sorted"};

        try (FileWriter writer = new FileWriter("benchmark_results.csv")) {
            writer.write("n,input_type,time_ms\n");

            for (int n : sizes) {
                for (String type : types) {
                    int[] arr = switch (type) {
                        case "random" -> generateRandomArray(n);
                        case "sorted" -> generateSortedArray(n);
                        case "reverse" -> generateReverseArray(n);
                        case "nearly_sorted" -> generateNearlySortedArray(n);
                        default -> throw new IllegalArgumentException("Unknown type");
                    };

                    long time = benchmark(arr);
                    writer.write(n + "," + type + "," + time + "\n");
                    System.out.printf("n=%d, type=%s, time=%dms%n", n, type, time);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
