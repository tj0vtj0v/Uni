public class Auto {
    static {
        System.out.println(0);
    }
    static int id_num = 1000;

    {
        System.out.println(2);
    }

    int id;

    Auto() {
        System.out.println(4);
        {
            System.out.println(5);
        }
        id = id_num++;
    }

    Auto(int id) {
        this();
        {
            System.out.println(6);
        }
        this.id = id;
    }

    {
        System.out.println(3);
    }

    static {
        System.out.println(1);
    }
}
