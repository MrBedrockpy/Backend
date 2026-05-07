package ru.neocode.neocode.util;

import lombok.experimental.UtilityClass;

import java.util.Random;

@UtilityClass
public class HashUtil {

    private static final String ALPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static final Random RANDOM = new Random();
    private static final int LENGTH = 32;

    public String generateHash() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < LENGTH; i++) s.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        return s.toString();
    }
}
