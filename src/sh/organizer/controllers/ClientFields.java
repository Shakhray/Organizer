package sh.organizer.controllers;

/**
 * Created by Шерхан on 06.10.2016.
 */
public enum ClientFields {
    ID("id", "id"),
    NAME("name", "Ф.И.О."),
    POSITION("position", "Должность"),
    ORGANIZATION("organization", "Организация"),
    EMAIL("email", "email"),
    PHONES("phones", "телефоны");

    private String fieldName;
    private String fieldNameRu;

    ClientFields(String fieldName, String fieldNameRu) {
        this.fieldName = fieldName;
        this.fieldNameRu = fieldNameRu;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldNameRu() {
        return fieldNameRu;
    }
}
