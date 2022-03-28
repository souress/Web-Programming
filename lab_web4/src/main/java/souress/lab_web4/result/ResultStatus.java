package souress.lab_web4.result;

public enum ResultStatus {
    OK("OK"),
    EMPTY("Результатов не найдено!"),
    REQUEST_ERROR("Request error"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    ResultStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
