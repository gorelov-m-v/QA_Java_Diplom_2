package Util;

import com.github.javafaker.Faker;

import java.util.Random;

public class Generator {
    Faker faker = new Faker();
    private String RANDOM_EMAIL = faker.number().digits(4) + (faker.name().lastName() + "@testmail.ru").toLowerCase();
    public String getRANDOM_EMAIL() {
        return RANDOM_EMAIL;
    }
    private String RANDOM_PASSWORD = faker.number().digits(9);
    public String getRANDOM_PASSWORD() {
        return RANDOM_PASSWORD;
    }
    private String RANDOM_NAME = faker.name().username();
    public String getRANDOM_NAME() {
        return RANDOM_NAME;
    }

    public String getRANDOM_TOKEN() {
        Random random = new Random();
        String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
        String combination = upper + lower + digits;
        char[] password = new char[30];
        for(int i = 0; i < 30; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        String result = "Bearer " + new String(password);
        return result;
    }
}
