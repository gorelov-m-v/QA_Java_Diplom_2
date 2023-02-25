package Util;

import com.github.javafaker.Faker;

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
}
