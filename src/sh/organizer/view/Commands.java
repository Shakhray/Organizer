package sh.organizer.view;

/**
 * @author Sherhan
 */
public enum Commands {
    HELP("help", "вывод подсказки по командам"),
    INSERT("insert", "Добавить новую запись"),
    UPDATE("update", "Обносить запись"),
    DELETE("delete", "Удалить запись"),
    LIST("list", "Список всех записей"),
    FIND("find", "Найти запись"),
    EXIT("exit", "Выход из программы");

    private String name;
    private String description;

    Commands(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return name + " - " + description;
    }
}
