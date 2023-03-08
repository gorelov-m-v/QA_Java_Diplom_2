package Util;

import Data.User;
import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {
    String lower = "abcdefghijklmnopqrstuvwxyz";
    String digits = "0123456789";
    Faker faker = new Faker();
    Random random = new Random();

    public String getRANDOM_EMAIL() {
        return faker.number().digits(4) + (faker.name().lastName() + "@testmail.ru").toLowerCase();
    }
    public String getRANDOM_PASSWORD() {
        return faker.number().digits(9);
    }
    public String getRANDOM_NAME() {
        return faker.name().username();
    }

    public String getRandomString(int length) {
        String combination = lower + digits;
        char[] password = new char[length];
        for(int i = 0; i < length; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        String result = new String(password);
        return result;
    }

    public List<String> getRandomList(int quantity) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < quantity; i++) {
            result.add(getRandomString(24));
        }
        return result;
    }

    public List<String> getOrderList(List<String> ingredientsList) {
        int size = new Random().nextInt(ingredientsList.size());
        List<String> orderList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int index = new Random().nextInt(ingredientsList.size());
            orderList.add(ingredientsList.get(index));
        }
        return orderList;
    }

    public User createUserWithoutPassword() {
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomName = faker.name().firstName();
        return new User(randomEmail, "", randomName);
    }

    public User createRandomUserData() {
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomPassword = faker.number().digits(8);
        String randomName = faker.name().firstName();
        return new User(randomEmail, randomPassword, randomName);
    }

    public User createUserWithoutName() {
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomPassword = faker.number().digits(8);
        return new User(randomEmail, randomPassword, "");
    }

    public User createUserWithoutEmail() {
        String randomPassword = faker.number().digits(8);
        String randomName = faker.name().firstName();
        return new User("", randomPassword, randomName);
    }

    public String getWrongBearerToken() {
        String combination = lower + digits;
        char[] password = new char[40];
        for(int i = 0; i < 40; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        String result = "Bearer " + new String(password);
        return result;
    }

    public String getInvalidBearerToken(String actualAccessToken) {
        return actualAccessToken + "fake";
    }

    public String getWrongRefreshToken() {
        String combination = lower + digits;
        char[] password = new char[40];
        for(int i = 0; i < 40; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        String result = new String(password);
        return result;
    }
}
