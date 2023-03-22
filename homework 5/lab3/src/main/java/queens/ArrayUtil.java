package queens;

import java.util.Random;

public class ArrayUtil {

    private static Random random;

    /**
     * Code from method java.util.Collections.shuffle();
     */
    public static void shuffle(int[] array) {
        if (random == null) random = new Random();
        int count = array.length;
        for (int i = count; i > 1; i--) {
            swap(array, i - 1, random.nextInt(i));
        }
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static int moveLeft(int[] array, int start) {
        int a_deleted = array[start-1];
        for (int i = start; i < array.length; i++) {
            array[i - 1] = array[i];
        }
        return a_deleted;
    }

    public static int moveRight(int[] array, int start) {
        int a_deleted = array[array.length-1];
        for (int i = array.length - 1; i > start; --i) {
            array[i] = array[i - 1];
        }
        return a_deleted;
    }
}