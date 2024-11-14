package com.recruitDemo.recruitDemo.util;
import java.util.Random;
import java.util.UUID;

public  class GenerateApiKeys {

    private int size = 32;

    public static String generateApiKey(){

        // create a string of all characters
        String alphabet =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" +
                "0123456789"+
                "abcdefghijklmnopqrstuvwxyz";

        // create random string builder
        StringBuilder sb = new StringBuilder();

        // create an object of Random class
        Random random = new Random();

        // specify length of random string
        int length = 7;

        for(int i = 0; i < length; i++) {

            // generate random index number
            int index = random.nextInt(alphabet.length());

            // get character specified by index
            // from the string
            char randomChar = alphabet.charAt(index);

            // append the character to string builder
            sb.append(randomChar);
        }

        String randomString = sb.toString();

        return randomString;
    }

}
