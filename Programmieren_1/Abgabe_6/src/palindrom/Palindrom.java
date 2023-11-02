package palindrom;

public class Palindrom {
    public static void main(String[] args) {
        System.out.println(palindrom("Lagerregal"));
    }
    private static boolean palindrom(String word) {
        String wordToCheck = word.toLowerCase();

        for (int index = 0; index <= wordToCheck.length() / 2; index++) {
            if (wordToCheck.charAt(index) != wordToCheck.charAt(wordToCheck.length() - (index + 1))) {
                return false;
            }
        }
        return true;
    }
}
