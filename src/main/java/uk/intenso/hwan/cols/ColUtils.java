package uk.intenso.hwan.cols;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class ColUtils {

    private ColUtils() {
    }

    public static <T> List<T> toList(Collection<T> col) {
        return col.stream().toList();
    }

    public static <T> List<T> toList(T[] arr) {
        return List.of(arr);
    }


    public static <T> T[] toArray(Collection<T> list) {
        if (list.toArray()[0].getClass().equals(String.class)) {
            return (T[]) toStringArray((Collection<String>) list);
        }
        else if (list.toArray()[0].getClass().equals(Integer.class)) {
            return (T[]) toIntArray((Collection<Integer>) list);
        }
        else {
            throw new RuntimeException("Fialed t convert to array");
        }
    }

    public static String[] toStringArray(Collection<String> col) {
        return col.toArray(new String[0]);
    }

    public static Integer[] toIntArray(Collection<Integer> col) {
        return col.toArray(new Integer[0]);
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
        return coll.subList(0, coll.size()-1);
    }

    public static <T> T firstValue(List<T> coll) {
        return coll.get(0);
    }

    public static <T> T lastValue(List<T> coll) {
        return coll.get(coll.size()-1);
    }

    public static <T> T firstValue(T[] arr) {
        return arr[0];
    }

    public static <T> T lastValue(T[] arr) {
        return arr[arr.length-1];
    }

    public static int size(Collection<?> col) { return col.size(); }

    public static int size(Object[] col) { return col.length; }



}
