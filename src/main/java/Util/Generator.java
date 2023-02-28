package Util;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.List;
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

    public String getRandomString(int length) {
        Random random = new Random();
        String lower = "abcdefghijklmnopqrstuvwxyz";
        String digits = "0123456789";
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
}
