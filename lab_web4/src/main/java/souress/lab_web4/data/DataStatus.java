package souress.lab_web4.data;

public enum DataStatus {
    OK("OK"),

    REQUEST_ERROR("Request error"),
    UNDEFINED_ERROR("Undefined error");

    private final String description;

    DataStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
