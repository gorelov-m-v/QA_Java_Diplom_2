import com.github.javafaker.Faker;

public class User {
    private String email;
    private String password;
    private String name;


    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }
    public User() {
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public User createRandomUserData() {
        Faker faker = new Faker();
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomPassword = faker.number().digits(8);
        String randomName = faker.name().firstName();
        return new User(randomEmail, randomPassword, randomName);
    }

    public User createUserWithoutName() {
        Faker faker = new Faker();
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomPassword = faker.number().digits(8);
        return new User(randomEmail, randomPassword, "");
    }

    public User createUserWithoutEmail() {
        Faker faker = new Faker();
        String randomPassword = faker.number().digits(8);
        String randomName = faker.name().firstName();
        return new User("", randomPassword, randomName);
    }

    public User createUserWithoutPassword() {
        Faker faker = new Faker();
        String randomEmail = (faker.name().lastName() + faker.number().digits(4) + "@testmail.ru").toLowerCase();
        String randomName = faker.name().firstName();
        return new User(randomEmail, "", randomName);
    }
}
