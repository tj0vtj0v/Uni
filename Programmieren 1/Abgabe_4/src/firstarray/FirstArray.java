package firstarray;

public class FirstArray {
    public static void main(String[] args) {
        int size = 100;

        String[] strings = new String[size];

        strings[0] = "Anfang";
        strings[size - 1] = "Ende";

        for (int i = 1; i < size - 1; i++) {
            strings[i] = "Mitte";
        }

        for (String item : strings) {
            System.out.println(item);
        }
    }
}
