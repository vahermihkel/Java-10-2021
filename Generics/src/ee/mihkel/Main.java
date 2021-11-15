package ee.mihkel;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Threads thread1 = new Threads(0);
        Threads thread2 = new Threads(1);
        Threads thread3 = new Threads(2);
        Threads thread4 = new Threads(3);

        List<String> strings = Arrays.asList("üks","Kaks","Kolm");
        List<Integer> integers = Arrays.asList(1,2,3);
        List<Long> longs = Arrays.asList(1L,2L,3L);
        List<Number> numbers = Arrays.asList(1,2,3L);
        List<Boolean> booleans = Arrays.asList(true,false,true);

        System.out.println(GenericsUtil.getFirst(strings));
        System.out.println(GenericsUtil.getFirst(integers));

        System.out.println();

        GenericsUtil.printList(longs);
        GenericsUtil.printList(strings);

        System.out.println();

        GenericsUtil.sum(longs);
        GenericsUtil.sum(numbers);
      //  GenericsUtil.sum(booleans);

        GenericsUtil.returnNUmber(longs.get(0));
        GenericsUtil.returnNUmber(numbers.get(0));
      //  GenericsUtil.returnNUmber(booleans.get(0));

      //  GenericsUtil.sum2(integers);
        GenericsUtil.sum2(longs);
      //  GenericsUtil.sum2(numbers);
    }
}
