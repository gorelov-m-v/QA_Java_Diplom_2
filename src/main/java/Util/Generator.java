package Util;

import Data.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Util.GeneratorConfigs.*;

public class Generator {
    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String DIGITS = "0123456789";
    Random random = new Random();

    public String createRandomString(int length) {
        String combination = LOWER + DIGITS + UPPER;
        char[] password = new char[length];
        for(int i = 0; i < length; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        return new String(password);
    }

    public String getRANDOM_EMAIL() {
        String domain = "@testmail.ru";
        int emailLength = random.nextInt(emailMaxLength - domain.length()) + domain.length();
        String randomEmail = createRandomString(emailLength) + domain;
        return randomEmail.toLowerCase();
    }
    public String getRANDOM_PASSWORD() {
        int passwordLength = random.nextInt(passwordMaxLength - passwordMinLength) + passwordMinLength;
        return createRandomString(passwordLength);
    }
    public String getRANDOM_NAME() {
        int nameLength = random.nextInt(nameMaxLength - nameMinLength) + nameMinLength;
        return createRandomString(nameLength);
    }

    public List<String> getRandomOrderList(int quantity) {
        List<String> result = new ArrayList<>();
        for(int i = 0; i < quantity; i++) {
            result.add(createRandomString(24));
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
        return new User(getRANDOM_EMAIL(), "", getRANDOM_NAME());
    }

    public User createRandomUserData() {
        return new User(getRANDOM_EMAIL(), getRANDOM_PASSWORD(), getRANDOM_NAME());
    }

    public User createUserWithoutName() {
        return new User(getRANDOM_EMAIL(), getRANDOM_PASSWORD(), "");
    }

    public User createUserWithoutEmail() {
        return new User("", getRANDOM_PASSWORD(), getRANDOM_NAME());
    }

    public String getWrongBearerToken() {
        return "Bearer " + createRandomString(80);
    }

    public String getInvalidBearerToken(String actualAccessToken) {
        return actualAccessToken + "fake";
    }

    public String getWrongRefreshToken() {
        return createRandomString(40);
    }
}
