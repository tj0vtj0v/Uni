package commandlinetools;

import java.util.Scanner;

public class Reverse {
    public static void main(String[] args) {
        if (args.length != 1 || !args[0].equals("-char-by-char") && !args[0].equals("-line-by-line")) {
            System.out.println("Es wurden unpassende Aufrufargumente übergeben.");
            System.exit(1);
        }

        StringBuilder inputLines = new StringBuilder();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            inputLines.append(scanner.nextLine()).append("\n");
        }
        scanner.close();

        StringBuilder outputText = new StringBuilder();
        if (args[0].equals("-char-by-char")) {
            outputText = new StringBuilder(inputLines);
            outputText.reverse();

        } else {
            String[] linesOfText = inputLines.toString().split("\\R");
            for (int i = linesOfText.length -1; i >= 0; i--) {
                outputText.append(linesOfText[i].strip()).append("\n");
            }
        }

        String input = inputLines.toString().strip();
        String output = outputText.toString().strip();
        System.out.println("Eingegebener Text:\n" + input);
        System.out.println("Geänderter Text:\n" + output);
    }
}
