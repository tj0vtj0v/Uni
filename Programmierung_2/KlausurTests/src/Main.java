import java.util.Comparator;
import java.util.Objects;

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
    }
}
