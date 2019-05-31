package lk.nsbm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lk.nsbm.shared.annotations.IdValue;

@JsonIgnoreProperties(value = "_id")
public class Key {
    @IdValue(name = "username")
    private String username;
    private byte[] privateKey;
    private byte[] publicKey;

    public Key() {
    }

    public Key(String username, byte[] publicKey, byte[] privateKey) {
        this.username = username;
        this.privateKey = privateKey;
        this.publicKey = publicKey;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public byte[] getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(byte[] privateKey) {
        this.privateKey = privateKey;
    }

    public byte[] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(byte[] publicKey) {
        this.publicKey = publicKey;
    }
}
