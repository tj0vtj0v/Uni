package commandlinetools;

import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("-char-by-char") && !args[0].equals("-line-by-line")) {
            System.out.println("Es wurden unpassende Aufrufargumente übergeben.");
            System.exit(1);
        }

        String nextLine;
        StringBuilder inputText = new StringBuilder();
        Scanner scanner = new Scanner(System.in);

        while (scanner.hasNextLine()) {
            nextLine = scanner.nextLine();

            if (nextLine.equals(":wq")) {
                break;
            }

            inputText.append(nextLine).append("\n");
        }
        scanner.close();



        StringBuilder outputText = new StringBuilder();
        if (args[0].equals("-char-by-char")) {
            outputText = inputText.reverse();
        } else {
            String[] linesOfText = inputText.toString().split("\\R");

            for (int i = linesOfText.length -1; i >= 0; i--) {
                outputText.append(linesOfText[i].strip()).append("\n");
            }
        }

        System.out.println(outputText);
    }
}
