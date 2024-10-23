package lineprinter;

public class LinePrinter {
    public static void main(String[] args) {
        String word = "Hallo!";
        String output = "";
        int charLimit = 100;
        int counter = 0;

        String line = "";

        while (output.length() < charLimit) {
            counter++;
            line += word;
            output += line;

            System.out.println(line);
        }

        System.out.println("\nLimit: " + charLimit + " Zeichen.");
        System.out.println("Es wurden " + output.length() + " Zeichen ausgegeben.");
        System.out.println("Es wurden " + counter + " Zeilen ausgegeben.");
        System.out.println("Es wurden " + output.length() / word.length() + " Wörter ausgegeben.");
    }
}
