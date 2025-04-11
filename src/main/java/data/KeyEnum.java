package data;

public enum KeyEnum {
    USER_SESSION_KEY("user"),
    USER_LIST_KEY("users"),
    USER_ID("userId"),
    USER_PASSWORD("password"),
    USER_EMAIL("email"),
    USER_NAME("name");
    final String key;
    KeyEnum(String key){
        this.key = key;
    }

    @Override
    public String toString() {
        return key;
    }
}
