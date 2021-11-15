package ee.mihkel;

import java.util.List;

public class GenericsUtil {
    public static <T> T getFirst(List<T> array) {
        if (array.isEmpty()) {
            return null;
        } else {
            return array.get(0);
        }
    }

    public static void printList(List<?> array) {
        for (Object o:array) {
            System.out.println(o);
        }
    }

    public static void sum(List<? extends Number> array) {
        Number sum = 0;
        double realSum = sum.doubleValue();
        for (Number n: array) {
            realSum += n.doubleValue();
        }
        System.out.println(realSum);
    }

    public static void returnNUmber(Number number) {

    }

    public static void sum2(List<? super Long> array) {
        for (Object o : array) {
            System.out.println(o);
        }
    }

    public static <T,U,V> void print(List<? super Long> array,
                                List<? extends Long> array2,
                                 List<T> array3,
                                List<U> array4,
                                 List<V> array5) {
        for (Object o : array) {
            System.out.println(o);
        }
    }
}
