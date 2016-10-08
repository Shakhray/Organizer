package sh.organizer.view;

import sh.organizer.controllers.ClientInfo;
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
        while (isItWork) {
            doWork();
        }
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
            case EXIT:
                stopWork();
                break;
            case INSERT:
                insert();
                break;
            case UPDATE:
                update();
                break;
            case HELP:
                help();
                break;
            case DELETE:
                delete();
                break;
            case FIND:
                find();
                break;
        }
    }

    private Commands command() throws IllegalArgumentException {
        return Commands.valueOf(input.nextLine().toUpperCase());
    }

    private void list() throws JAXBException {
        printResult(organizerController.list());
    }

    private void printResult(String result) {
        System.out.println(result.isEmpty() ? "Записей не найдено" : result);
    }

    private void stopWork() {
        isItWork = false;
    }

    private void insert() throws JAXBException {
        Map<String, String> clientInfo = new HashMap<>();
        for (ClientInfo info : ClientInfo.values()) {
            System.out.print(info.getDescription() + "=");
            clientInfo.put(info.getDescription(), input.nextLine());
        }
        organizerController.insert(clientInfo);
    }

    private void update() {

    }

    private void help() {
        StringBuilder result = new StringBuilder();
        for (Commands command : Commands.values()) {
            result.append(command.toString());
            result.append("\n");
        }
        System.out.println(result.toString());
    }

    private void delete() throws JAXBException {
        System.out.print("Введите id записи для удаления: ");
        try {
            organizerController.delete(input.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("id может быть только числом");
            delete();
        }
    }

    private void find() throws JAXBException {
        System.out.print("Введите слово для поиска: ");
        printResult(organizerController.find(input.nextLine()));
    }
}
