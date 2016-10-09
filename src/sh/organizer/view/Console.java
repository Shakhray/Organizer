package sh.organizer.view;

import sh.organizer.controllers.ClientFields;
import sh.organizer.controllers.OrganizerController;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author Sherhan
 */
public class Console {
    private OrganizerController organizerController = new OrganizerController();
    private Scanner input = new Scanner(System.in);
    private boolean isItWork = true;

    public void run() {
        printHallo();
        while (isItWork) {
            doWork();
        }
    }

    private void printHallo() {
        System.out.println("Добро пожаловать в Organizer.\nЧтобы начать работу, введите команду help.\n");
    }

    private void doWork() {
        printCommandLine();
        try {
            executeCommand();
        } catch (JAXBException e) {
            System.out.println("Внутренняя ошибка");
        } catch (IllegalArgumentException e) {
            System.out.println("Неверная команда");
        }
    }

    private void printCommandLine() {
        System.out.print("organizer> ");
    }

    private void executeCommand() throws JAXBException {
        switch (command()) {
            case LIST:
                list();
                break;
            case FIND:
                find();
                break;
            case EXIT:
                stopWork();
                break;
            case INSERT:
                insert();
                break;
            case UPDATE:
                update();
                break;
            case DELETE:
                delete();
                break;
            case HELP:
                help();
                break;
        }
    }

    private Commands command() throws IllegalArgumentException {
        return Commands.valueOf(input.nextLine().toUpperCase());
    }

    private void list() throws JAXBException {
        printResult(organizerController.list());
    }

    private void find() throws JAXBException {
        System.out.print("Введите слово для поиска: ");
        printResult(organizerController.find(input.nextLine()));
    }

    private void printResult(String result) {
        System.out.println(result.isEmpty() ? "Записей не найдено" : result);
    }

    private void stopWork() {
        isItWork = false;
    }

    private void insert() throws JAXBException {
        Map<String, String> clientInfo = new HashMap<>();
        fillClientInfoFromConsole(clientInfo);
        organizerController.insert(clientInfo);
    }

    private void update() throws JAXBException {
        String id = readIdFromConsole();
        if (id.isEmpty()) return;

        Map<String, String> clientInfo = new HashMap<>();
        clientInfo.put(ClientFields.ID.getFieldName(), id);
        fillClientInfoFromConsole(clientInfo);

        if (!organizerController.update(clientInfo)) {
            printItemNotFound(id);
        }
    }

    private void fillClientInfoFromConsole(Map<String, String> clientInfo) {
        for (ClientFields field : ClientFields.values()) {
            if (!field.equals(ClientFields.ID)) {
                System.out.print(field.getFieldNameRu() + " = ");
                clientInfo.put(field.getFieldName(), input.nextLine());
            }
        }
    }

    private void delete() throws JAXBException {
        String id = readIdFromConsole();
        if (id.isEmpty()) return;

        if (!organizerController.delete(id)) {
            printItemNotFound(id);
        }
    }

    private String readIdFromConsole() {
        System.out.print("Введите id записи: ");
        String id = input.nextLine();

        if (!isCorrectId(id)) {
            System.out.println("id может быть только числом");
            id = "";
        }

        return id;
    }

    private boolean isCorrectId(String id) {
        try {
            return Integer.valueOf(id) > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void printItemNotFound(String itemId) {
        System.out.println("Запись с id " + itemId + " не найдена.");
    }

    private void help() {
        StringBuilder result = new StringBuilder();
        for (Commands command : Commands.values()) {
            result.append(command.toString());
            result.append("\n");
        }
        System.out.print(result.toString());
    }
}
