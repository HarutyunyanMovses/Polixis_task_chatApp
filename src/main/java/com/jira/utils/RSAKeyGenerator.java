package com.jira.utils;

import java.security.*;
import java.nio.file.*;
import java.util.Base64;

public class RSAKeyGenerator {
    public static void main(String[] args) throws Exception {
        KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
        keyGen.initialize(2048);
        KeyPair pair = keyGen.generateKeyPair();

        String privateKey = Base64.getEncoder().encodeToString(pair.getPrivate().getEncoded());
        String publicKey = Base64.getEncoder().encodeToString(pair.getPublic().getEncoded());

        Path privateKeyPath = Paths.get("src/main/resources/META-INF/resources/privateKey.pem");
        Path publicKeyPath = Paths.get("src/main/resources/META-INF/resources/publicKey.pem");

        Files.createDirectories(privateKeyPath.getParent());
        Files.write(privateKeyPath, privateKey.getBytes());
        Files.write(publicKeyPath, publicKey.getBytes());

        System.out.println("Keys generated successfully in META-INF/resources!");
    }
}
