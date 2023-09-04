package uk.intenso.hwan.cols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Convenience and readability methods for Lists,Arrays etc.
 */
public class HwColUtils {

    private HwColUtils() {
    }

    /**
     * Attempts to easily convert a collection of Strings or Integers to an Array. More primitives could be added
     * in the future.
     */
    public static <T> T[] toArray(Collection<T> collection) {
        if (collection.toArray()[0].getClass().equals(String.class)) {
            return (T[]) toStringArray((Collection<String>) collection);
        } else if (collection.toArray()[0].getClass().equals(Integer.class)) {
            return (T[]) toIntArray((Collection<Integer>) collection);
        } else {
            throw new RuntimeException("Failed to convert to array type " + collection.toArray()[0].getClass()
                    .getSimpleName() + " not supported");
        }
    }

    public static String[] toStringArray(Collection<String> col) {
        return col.toArray(new String[col.size()]);
    }

    public static Integer[] toIntArray(Collection<Integer> col) {
        return col.toArray(new Integer[col.size()]);
    }

    @SafeVarargs
    public static <T> T[] removeLast(T... arr) {
        return Arrays.copyOf(arr, arr.length - 1);
    }

    @SafeVarargs
    public static <T> T[] removeFirst(T... arr) {
        return Arrays.copyOfRange(arr, 1, arr.length);
    }

    public static <T> List<T> removeFirst(List<T> coll) {
        return coll.subList(1, coll.size());
    }

    public static <T> List<T> removeLast(List<T> coll) {
        return coll.subList(0, coll.size() - 1);
    }

    public static <T> T firstValue(List<T> coll) {
        return coll.get(0);
    }

    public static <T> T lastValue(List<T> coll) {
        return coll.get(coll.size() - 1);
    }

    public static <T> T firstValue(T[] arr) {
        return arr[0];
    }

    public static <T> T lastValue(T[] arr) {
        return arr[arr.length - 1];
    }

    public static <T> List<T> toImmutableList(Collection<T> col) {
        return col.stream().toList();
    }

    public static <T> List<T> toImmutableList(T[] arr) {
        return List.of(arr);
    }
    public static <T> List<T> toMutableList(Collection<T> col) {
        return new ArrayList<>(col.stream().toList());
    }

    public static <T> ArrayList<T> toMutableList(T[] arr) {
        return new ArrayList<>(List.of(arr));
    }

    public static int size(Collection<?> col) {
        return col.size();
    }

    public static int size(Object[] col) {
        return col.length;
    }


}
