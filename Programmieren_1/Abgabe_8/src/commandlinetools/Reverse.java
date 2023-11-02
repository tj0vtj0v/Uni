package commandlinetools;

import java.util.Scanner;

public class Reverse {

    public static void main(String[] args) {

        StringBuilder inputText = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextLine()) {
            inputText.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        StringBuilder output = new StringBuilder();

        if (args[0].equals("-char-by-char")) {
            output.append("-char-by-char");
            inputText.reverse();
        } else if (args[0].equals("-line-by-line")) {
            output.append("-line-by-line");
        } else {
            exit(2);
        }
        System.out.println("OK");
        System.out.println(output);

        System.out.println(inputText);
    }

    private static void exit(int code) {
        System.out.println("Es wurden unpassende Aufrufargumente übergeben." + code);
        System.exit(1);
    }
}
