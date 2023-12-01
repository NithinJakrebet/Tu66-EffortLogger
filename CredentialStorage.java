package asuEffortLogger1;

import java.util.HashMap;
import java.util.Map;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class CredentialStorage {

    // In a real application, you would use a secure storage solution (e.g., database) instead of a Map
    private Map<String, String> credentialDatabase;
    private static final String STORAGE_FILE = "credentialStorage.dat";
    
    public CredentialStorage() {
        // Initialize the credential database (in-memory representation, replace with a database in a real app)
        loadCredentials();
    }
    
    private void loadCredentials() {
        try {
            File file = new File(STORAGE_FILE);
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    credentialDatabase = (Map<String, String>) ois.readObject();
                }
            } else {
                credentialDatabase = new HashMap<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
            credentialDatabase = new HashMap<>();
        }
    }

    /**
     * Store the user's credentials securely.
     *
     * @param username           The username of the user.
     * @param encryptedPassword  The encrypted password of the user.
     */
    private void saveCredentials() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STORAGE_FILE))) {
            oos.writeObject(credentialDatabase);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void storeCredentials(String username, String encryptedPassword) {
        credentialDatabase.put(username.trim(), encryptedPassword);
        saveCredentials();
        System.out.println("Credentials stored securely for user: " + username);
        System.out.println("Current contents of credentialDatabase: " + credentialDatabase);
    }



    /**
     * Retrieve the encrypted password for a given username.
     *
     * @param username  The username of the user.
     * @return The encrypted password associated with the username, or null if not found.
     */
    public String retrieveCredentials(String username) {
    	System.out.println("Attempting to retrieve credentials for user: " + username);
        System.out.println("Current contents of credentialDatabase before retrieval: " + credentialDatabase);


        String encryptedPassword = credentialDatabase.get(username.trim());
        if (encryptedPassword == null) {
            System.out.println("No stored password found for the user: " + username);
            return null;
        } else {
            System.out.println("Credentials retrieved for user: " + username);
            System.out.println("Retrieved encrypted password: " + encryptedPassword);
            return encryptedPassword;
        }
    }
    /**
     * Generate a random salt.
     *
     * @return The generated salt.
     */
    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return salt;
    }

    /**
     * Hash the password using PBKDF2 with a random salt.
     *
     * @param password The password to be hashed.
     * @return The hashed password.
     */
    
    public String hashPassword(String password, byte[] salt) {
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            byte[] hash = factory.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(salt) + "$" + Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
