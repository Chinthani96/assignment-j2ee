package lk.nsbm.service.custom.impl;

import lk.nsbm.dao.DAOFactory;
import lk.nsbm.dao.custom.KeyDAO;
import lk.nsbm.entity.Key;
import lk.nsbm.entity.User;
import lk.nsbm.service.custom.EncryptionService;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class EncryptionServiceImpl implements EncryptionService {

    private KeyPair keyPair;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private KeyDAO keyDAO;

    public EncryptionServiceImpl() {
        // GENERATE NEW KEYPAIR

        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        /* @see https://www.keylength.com/ */
        keyPairGenerator.initialize(4096);
        keyPair = keyPairGenerator.generateKeyPair();

        publicKey = keyPair.getPublic();
        privateKey = keyPair.getPrivate();

        keyDAO = (KeyDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.KEY);

    }

    private byte[] turnBytes(String data) {
        return data.getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public boolean isDataEqualsWithEncryption(String data, User user) {
        /* // GENERATE NEW KEYPAIR
         KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");

         *//* @see https://www.keylength.com/ *//*
         keyPairGenerator.initialize(4096);
         KeyPair keyPair = keyPairGenerator.generateKeyPair();*/
        try {
//            X509EncodedKeySpec spec = new X509EncodedKeySpec(user.getPublicKey());
//            System.out.println(user.getPublicKey());
            KeyFactory kf = KeyFactory.getInstance("RSA");
//            PublicKey publicKey = kf.generatePublic(spec);
            Key userEncKeys = keyDAO.findById(user.getUsername());

            if (userEncKeys == null) {
                return false;
            }

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(userEncKeys.getPrivateKey());
            PrivateKey privateKey = kf.generatePrivate(spec);

            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);

//            byte[] cipherBytes = cipher.doFinal(user.getPassword().getBytes(StandardCharsets.UTF_8));
            byte[] decryptedCipherBytes = cipher.doFinal(Base64.getDecoder().decode(user.getPassword()));

//            String cipherText = Base64.getEncoder().encodeToString(cipherBytes);
            String decryptcipherText = new String(decryptedCipherBytes, StandardCharsets.UTF_8);

            return data.equals(decryptcipherText);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            e.printStackTrace();
        }


        return false;
    }

    @Override
    public User generateEncryptionValue(User user) {
        try {
            // GENERATE NEW KEYPAIR
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
//             @see https://www.keylength.com/
            keyPairGenerator.initialize(4096);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            publicKey = keyPair.getPublic();
            privateKey = keyPair.getPrivate();

            // ENCRYPTION
            Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPWithSHA-256AndMGF1Padding");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);

            byte[] cipherTextBytes = cipher.doFinal(user.getPassword().getBytes(StandardCharsets.UTF_8));

            // CONVERSION of raw bytes to BASE64 representation
            String cipherText = Base64.getEncoder().encodeToString(cipherTextBytes);
            user.setPassword(cipherText);
//            user.setPublicKey(publicKey.getEncoded());
//            user.setPrivateKey(privateKey.getEncoded());
            keyDAO.save(new Key(user.getUsername(),publicKey.getEncoded(),privateKey.getEncoded()));
            return user;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }
}
