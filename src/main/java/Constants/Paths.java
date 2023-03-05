package Constants;

public class Paths {
    private final String CREATE_USER_PATH = "/api/auth/register";
    public String getCREATE_USER_PATH() {
        return CREATE_USER_PATH;
    }
    private final String LOGIN_USER_PATH = "/api/auth/login";
    public String getLOGIN_USER_PATH() {
        return LOGIN_USER_PATH;
    }
    private final String DELETE_USER_PATH = "/api/auth/user";
    public String getDELETE_USER_PATH() {
        return DELETE_USER_PATH;
    }
    private final String CHANGE_USER_DATA_PATH = "/api/auth/user";
    public String getCHANGE_USER_DATA_PATH() {
        return CHANGE_USER_DATA_PATH;
    }
    private final String GET_INGREDIENTS = "/api/ingredients";
    public String getGET_INGREDIENTS() {
        return GET_INGREDIENTS;
    }
    private final String CREATE_ORDER = "/api/orders";
    public String getCREATE_ORDER() {
        return CREATE_ORDER;
    }

    private final String GET_ORDER = "/api/orders";
    public String getGET_ORDER() {
        return GET_ORDER;
    }
}
