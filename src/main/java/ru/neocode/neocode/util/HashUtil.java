package ru.neocode.neocode.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class HashUtil {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();

    public String generateHash(int length) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < length; i++) s.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        return s.toString();
    }
}
