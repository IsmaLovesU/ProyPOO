/**
 * Universidad del Valle de Guatemala
 * Programación Orinetada a Objetos 
 * Sección: 10
 * Ing. Kimberly Barrera
 * Proyecto - Pillas
*/

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * Clase para cifrado y descifrado AES.
 * Proporciona métodos para cifrar y descifrar datos de forma segura utilizando el algoritmo AES.
 */
public class AESUtil {
    
    /**
     * El algoritmo de cifrado utilizado (AES).
     */
    private static final String ALGORITHM = "AES";

    /**
     * La clave secreta se utiliza para el cifrado y descifrado.
     * Esta clave debe tener una longitud de 16 bytes (128 bits).
     */
    private static final byte[] keyValue = "MotoMamibrrrrrrr".getBytes(); 

    /**
     * Cifra los datos proporcionados utilizando el cifrado AES.
     *
     * @param data Los datos en texto plano que se desean cifrar.
     * @return Los datos cifrados como una cadena codificada en Base64.
     * @throws Exception Si ocurre un error durante el proceso de cifrado.
     */
    public static String encrypt(String data) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedData);
    }

    /**
     * Descifra los datos cifrados proporcionados utilizando el descifrado AES.
     *
     * @param encryptedData Los datos cifrados como una cadena codificada en Base64.
     * @return Los datos descifrados en texto plano.
     * @throws Exception Si ocurre un error durante el proceso de descifrado.
     */
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec key = new SecretKeySpec(keyValue, ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decodedValue = Base64.getDecoder().decode(encryptedData);
        byte[] decryptedData = cipher.doFinal(decodedValue);
        return new String(decryptedData, "UTF-8");
    }
}