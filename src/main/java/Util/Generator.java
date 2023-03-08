package Util;

import Data.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static Util.GeneratorConfigs.*;

public class Generator {
    String lower = "abcdefghijklmnopqrstuvwxyz";
    String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String digits = "0123456789";
    Random random = new Random();

    public String createRandomString(int length) {
        String combination = lower + digits + upper;
        char[] password = new char[length];
        for(int i = 0; i < length; i++) {
            password[i] = combination.charAt(random.nextInt(combination.length()));
        }
        String result = new String(password);
        return result;
    }

    public String getRANDOM_EMAIL() {
        String domain = "@testmail.ru";
        int emailLength = random.nextInt(emailMaxLength - domain.length()) + domain.length();
        String randomEmail = createRandomString(emailLength) + domain;
        return randomEmail.toLowerCase();
    }
    public String getRANDOM_PASSWORD() {
        int passwordLength = random.nextInt(passwordMaxLength - passwordMinLength) + passwordMinLength;
        String randomPassword = createRandomString(passwordLength);
        return randomPassword;
    }
    public String getRANDOM_NAME() {
        int nameLength = random.nextInt(nameMaxLength - nameMinLength) + nameMinLength;
        String randomName = createRandomString(nameLength);
        return randomName;
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
