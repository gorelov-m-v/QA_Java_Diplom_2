package Constants;

public class Paths {
    private String CREATE_USER_PATH = "/api/auth/register";
    public String getCREATE_USER_PATH() {
        return CREATE_USER_PATH;
    }
    private String LOGIN_USER_PATH = "/api/auth/login";
    public String getLOGIN_USER_PATH() {
        return LOGIN_USER_PATH;
    }
    private String DELETE_USER_PATH = "/api/auth/user";
    public String getDELETE_USER_PATH() {
        return DELETE_USER_PATH;
    }
    private String CHANGE_USER_DATA_PATH = "/api/auth/user";
    public String getCHANGE_USER_DATA_PATH() {
        return CHANGE_USER_DATA_PATH;
    }
    private String GET_INGREDIENTS = "/api/ingredients";
    public String getGET_INGREDIENTS() {
        return GET_INGREDIENTS;
    }
    private String CREATE_ORDER = "/api/orders";
    public String getCREATE_ORDER() {
        return CREATE_ORDER;
    }
}
