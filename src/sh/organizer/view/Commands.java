package sh.organizer.view;

/**
 * @author Sherhan
 */
public enum Commands {
    HELP("help", "вывод подсказки по командам"),
    INSERT("insert", "Добавить новую запись. Введите insert и нажмите клавишу Enter. Затем впишите заначения в предлогаеммое поле и нажмите клавишу Enter. Телефонные номерра вводятся через запятую."),
    UPDATE("update", "Обносить запись. Введите update и нажмите клавишу Enter. Затем введите id записи, которую хотитте обновить, и по очереди новые значения полей. Телефонные номерра вводятся через запятую."),
    DELETE("delete", "Удалить запись. Введите delete и нажмите клавишу Enter. Затем введите id записи, которую хотите удалить."),
    LIST("list", "Список всех записей. Введите list и нажмите клавишу Enter."),
    FIND("find", "Найти запись. Введите find и нажмите клавишу Enter. Затем введите слово для поиска."),
    EXIT("exit", "Выход из программы. Введите exit и нажмите клавишу Enter.");

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
