package utils;

import java.util.Random;

public class UserGenerator {
    public static String generate(String baseUsername) {
        Random randomGenerator = new Random();
        int randomInt = randomGenerator.nextInt(1000);
        return baseUsername + randomInt;
    }
}
