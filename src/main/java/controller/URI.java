package controller;

public enum URI {

    SIGNUP("/user/signup"),
    HOME("/"),
    USER_LIST("/user/userList"),
    LIST("/user/list"),
    LOGIN("/user/login"),
    LOGIN_FAILED("/user/login_failed"),
    LOGOUT("/user/logout"),
    UPDATE_FORM("/user/updateForm"),
    JSP(".jsp"),
    REDIRECT("redirect:");

    private final String path;

    URI(String path) {
        this.path = path;
    }

    public String getPath() {
        return this.path;
    }

    public String redirect(){
        return "redirect:" + this.path;
    }

    public String jsp(){
        return this.path + ".jsp";
    }

}
