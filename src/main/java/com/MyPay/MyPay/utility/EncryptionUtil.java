package com.MyPay.MyPay.utility;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncryptionUtil {

    private final StringEncryptor stringEncryptor;

    @Autowired
    public EncryptionUtil(StringEncryptor stringEncryptor) {
        this.stringEncryptor = stringEncryptor;
    }

    public String encrypt(String input) {
        return stringEncryptor.encrypt(input);
    }

    public String decrypt(String encryptedInput) {
        return stringEncryptor.decrypt(encryptedInput);
    }
}
