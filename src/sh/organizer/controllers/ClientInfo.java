package sh.organizer.controllers;

/**
 * Created by Шерхан on 06.10.2016.
 */
public enum ClientInfo {
    ID("id"),
    NAME("name"),
    POSITION("position"),
    ORGANIZATION("organization"),
    EMAIL("email"),
    PHONES("phones");

    private String description;

    ClientInfo(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
