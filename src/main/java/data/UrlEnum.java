package data;

import jakarta.servlet.annotation.WebServlet;

public enum UrlEnum {
    REDIRECT("redirect:"),
    HOME_URL("/home"),
    DEFAULT_URL("/"),
    USER_SIGNUP("/user/signup"),
    USER_LIST("/user/userList"),
    USER_DETAIL("/user/list"),
    USER_LOGIN("/user/login"),
    USER_LOGOUT("/user/logout"),
    USER_UPDATE_FORM("/user/updateForm"),
    USER_UPDATE("/user/update"),
    LOGIN_FAILED("/user/login_failed"),
    JSP(".jsp");
    private final String url;
    UrlEnum(String url) {
        this.url = url;
    }
    public String getUrl() {return url;};
}
