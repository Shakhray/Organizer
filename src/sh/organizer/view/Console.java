package sh.organizer.view;

import sh.organizer.controllers.OrganizerController;

import javax.xml.bind.JAXBException;
import java.util.Scanner;

/**
 * @author Sherhan
 */
public class Console {
    private OrganizerController organizerController = new OrganizerController();
    private Printer printer = new Printer();
    private Scanner input = new Scanner(System.in);

    public void run() {
        while (true) {
            try {
                printer.printCommandLine();
                switch (command()) {
                    case LIST:
                        list();
                        break;
                }
            } catch (JAXBException e) {
                System.out.println("Внутренняя ошибка");
            } catch (IllegalArgumentException e) {
                System.out.println("Неверная команда");
            }
        }
    }

    private Commands command() throws IllegalArgumentException {
        return Commands.valueOf(input.nextLine().toUpperCase());
    }

    private void list() throws JAXBException {
        printer.printClients(organizerController.list());
    }

}
