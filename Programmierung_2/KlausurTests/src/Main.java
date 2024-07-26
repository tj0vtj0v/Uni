import javax.swing.*;
import java.util.*;

public class Main {
    enum OneAndTwo {
        ONE(1),
        TWO(2);

        final int num;

        OneAndTwo(int num) {
            this.num = num;
        }
    }
    Comparator<Integer> comp = new Comparator<>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o1, o2);
        }
    };

    public static void main(String[] args) {
        Auto a;

        a = new Auto();
        System.out.println(Auto.id_num);
        a = new Auto(Auto.id_num);
        System.out.println(Auto.id_num);

        System.out.println(a.getClass());

        LinkedList<Integer> l = new LinkedList<>(List.of(1, 2, 3));
        l.get(0);
        //l.get(3);

        TreeMap<String, Integer> ln = new TreeMap<>();
        ln.put("Mary", 7);
        ln.put("Paul", 13);
        ln.put("Peter", 7);

        System.out.println(ln.firstEntry());

        Iterator it = ln.descendingKeySet().iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}
