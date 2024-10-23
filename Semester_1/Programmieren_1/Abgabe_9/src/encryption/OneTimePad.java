package encryption;

import java.security.SecureRandom;
import java.util.Scanner;

class OneTimePad {

    /**
     * Erzeugt einen Schlüssel in der angegebenen Länge.
     *
     * @param lengthInBytes Die länge des Schlüssels in Bytes.
     * @return Der Schlüssel.
     */
    byte[] createKey(int lengthInBytes) {
        byte[] key = new byte[lengthInBytes];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        return key;
    }

    /**
     * Diese Methode wandelt das übergebene Byte-Array in einen Binärstring um. Nach je 8 Bits wird ein Leerzeichen
     * eingefügt.
     *
     * @param bytes Das umzuwandelnde Byte-Array.
     * @return Der Binärstring.
     */
    String toBinaryString(byte[] bytes) {
        StringBuilder binarySting = new StringBuilder();
        int[] mask = new int[]{0x80, 0x40, 0x20, 0x10, 0x08, 0x04, 0x02, 0x01};

        for (byte currentByte : bytes) {
            for (int i = 0; i <= 7; i++) {
                binarySting.append((currentByte & mask[i]) >>> (7 - i));
            }
            binarySting.append(" ");
        }

        return binarySting.toString();
    }


    /**
     * Diese Methode verschlüsselt die übergebene Nachricht mit dem Schlüssel. Dabei wird jedes Byte der Nachricht durch
     * den Exklusiv-Oder-Operator mit dem entsprechenden Byte des Schlüssels verknüpft.
     *
     * @param message Die zu verschlüsselnde Nachricht.
     * @param key     Der Schlüssel.
     * @return Die verschlüsselte Nachricht als Byte-Array.
     */
    byte[] encryptMessage(byte[] message, byte[] key) {
        byte[] encryptedMessage = new byte[message.length];

        for (int i = 0; i < message.length; i++) {
            encryptedMessage[i] = (byte) (message[i] ^ key[i]);
        }

        return encryptedMessage;
    }

    /**
     * Diese Methode entschlüsselt die übergebene verschlüsselte Nachricht mit dem Schlüssel. Dabei wird jedes Byte der
     * Nachricht durch den Exklusiv-Oder-Operator mit dem entsprechenden Byte des Schlüssels verknüpft.
     *
     * @param encryptedMessage Die verschlüsselte Nachricht.
     * @param key              Der Schlüssel.
     * @return Die entschlüsselte Nachricht als Byte-Array.
     */
    byte[] decryptMessage(byte[] encryptedMessage, byte[] key) {
        return encryptMessage(encryptedMessage, key);
    }


    /**
     * Diese Methode wandelt den übergebenen Binärstring (mit Leerzeichen zwischen je 8 Bits) in ein Byte-Array um.
     *
     * @param binaryString Der Binärstring.
     * @return Das Byte-Array.
     */
    private byte[] toByteArray(String binaryString) {
        String[] binaryStringArray = binaryString.split(" ");
        byte[] byteArray = new byte[binaryStringArray.length];

        for (int i = 0; i < binaryStringArray.length; i++) {
            byteArray[i] = (byte) Integer.parseInt(binaryStringArray[i], 2);
        }

        return byteArray;
    }

    /**
     * Diese Methode fragt interaktiv nach einer zu verschlüsselnden Nachricht. Es wird ein Schlüssel erzeugt und die
     * Nachricht wird mit dem Schlüssel verschlüsselt.
     * <p>
     * Schlüssel und verschlüsselte Nachricht werden als Binärstring ausgegeben.
     */
    void encryptionInteractive() {
        System.out.println("***Verschlüsseln einer Nachricht***");
        System.out.print("Bitte den zu verschlüsselnden Text eingeben: ");

        byte[] clearTextBytes = new Scanner(System.in).nextLine().strip().getBytes();
        byte[] keyAsBytes = createKey(clearTextBytes.length);

        String keyAsBinaryString = toBinaryString(keyAsBytes);
        String encryptedMessageAsBinaryString = toBinaryString(encryptMessage(clearTextBytes, keyAsBytes));

        System.out.println("Der Schlüssel:\n" + keyAsBinaryString);
        System.out.println("Die verschlüsselte Nachricht:\n" + encryptedMessageAsBinaryString);

    }

    /**
     * Diese Methode fragt interaktiv nach einer verschlüsselten Nachricht und nach einem Schlüssel. Die Nachricht wird
     * mit dem Schlüssel entschlüsselt.
     * <p>
     * Die entschlüsselte Nachricht wird im Klartext ausgegeben.
     */
    void decryptionInteractive() {
        System.out.println("***Entschlüsseln einer Nachricht***");
        Scanner scanner = new Scanner(System.in);

        System.out.print("Bitte den zu entschlüsselnden Binärstring eingeben: ");
        byte[] encryptedMessageAsBytes = toByteArray(scanner.nextLine().strip());

        System.out.print("Bitte den Schlüssel als Binärstring eingeben: ");
        byte[] keyAsBytes = toByteArray(scanner.nextLine().strip());

        String decryptedMessage = new String(decryptMessage(encryptedMessageAsBytes, keyAsBytes));

        System.out.println("Die entschlüsselte Nachricht:\n" + decryptedMessage);
    }
}
