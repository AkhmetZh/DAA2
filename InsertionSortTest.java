import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InsertionSortTest {

    @Test
    void testEmptyArray() {
        int[] arr = {};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    void testSingleElement() {
        int[] arr = {5};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    void testAlreadySorted() {
        int[] arr = {1, 2, 3, 4, 5};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testReverseSorted() {
        int[] arr = {5, 4, 3, 2, 1};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    void testWithDuplicates() {
        int[] arr = {3, 1, 2, 3, 1};
        InsertionSort.insertionSort(arr);
        assertArrayEquals(new int[]{1, 1, 2, 3, 3}, arr);
    }

    @Test
    void testNullArray() {
        assertThrows(IllegalArgumentException.class, () -> {
            InsertionSort.insertionSort(null);
        });
    }
}
