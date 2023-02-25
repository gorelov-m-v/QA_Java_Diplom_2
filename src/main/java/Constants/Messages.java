package Constants;

public class Messages {
    private String USER_ALREADY_EXISTS = "User already exists";
    public String getUSER_ALREADY_EXISTS() {
        return USER_ALREADY_EXISTS;
    }
    private String CREATING_USER_WITHOUT_FIELD = "Email, password and name are required fields";
    public String getCREATING_USER_WITHOUT_FIELD() {
        return CREATING_USER_WITHOUT_FIELD;
    }
    private String EMAIL_PASSWORD_INCORRECT= "email or password are incorrect";
    public String getEMAIL_PASSWORD_INCORRECT() {
        return EMAIL_PASSWORD_INCORRECT;
    }
    private String YOU_SHOULD_BE_AUTHORISED = "You should be authorised";
    public String getYOU_SHOULD_BE_AUTHORISED() {
        return YOU_SHOULD_BE_AUTHORISED;
    }
    private String USER_EMAIL_ALREADY_EXISTS = "User with such email already exists";
    public String getUSER_EMAIL_ALREADY_EXISTS() {
        return USER_EMAIL_ALREADY_EXISTS;
    }
}
