package souress.lab_web4.auth;

public enum AuthStatus {
    OK("OK"),
    USER_ALREADY_EXISTS("Логин занят, выберите другой"),
    TOO_SHORT_PASSWORD("Слишком короткий пароль"),
    TOO_SHORT_LOGIN("Слишком короткий логин"),
    NO_USER_FOUND("Пользователь не найден"),
    WRONG_PASSWORD("Проверьте правильность введенных логина и/или пароля"),
    REQUEST_ERROR("Request error"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    AuthStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
