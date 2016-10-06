package sh.organizer.view;

/**
 * @author Sherhan
 */
public enum Commands {
    HELP("вывод подсказки по командам"),
    INSERT("Добавить новую запись"),
    UPDATE("Обносить запись"),
    DELETE("Удалить запись"),
    LIST("Список всех записей"),
    FIND("Найти запись"),
    EXIT("Выход из программы");

    private String description;

    Commands(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
