package classic_computer_science_problems.small_problems;

import java.security.SecureRandom;

public class UnbreakableEncryption {
    private static byte[] randomKey(int length) {
        byte[] dummy = new byte[length];
        SecureRandom random = new SecureRandom();
        random.nextBytes(dummy);
        return dummy;
    }

    public static KeyPair encrypt(String original) {
        byte[] originalBytes = original.getBytes();
        byte[] dummyKey = randomKey(originalBytes.length);
        byte[] encryptedBytes = new byte[originalBytes.length];
        for (int i = 0; i < originalBytes.length; i++) {
            encryptedBytes[i] = (byte) (originalBytes[i] ^ dummyKey[i]);
        }
        return new KeyPair(dummyKey, encryptedBytes);
    }

    public static String decrypt(KeyPair kp) {
        byte[] decrypted = new byte[kp.key1.length];
        for (int i = 0; i < kp.key1.length; i++) {
            // XOR every byte
            decrypted[i] = (byte) (kp.key1[i] ^ kp.key2[i]);
        }
        return new String(decrypted);
    }

    public static void main(String[] args) {
        KeyPair kp = encrypt("One Time Pad!!!");
        String result = decrypt(kp);
        System.out.println(result);
    }
}

