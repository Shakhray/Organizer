package sh.organizer.model.parsers;

import javax.xml.bind.JAXBException;
import java.io.File;

/**
 * @author Sherhan
 */
public interface Parser {
    Object getObject(File file, Class c) throws JAXBException;

    void saveObject(File file, Object o) throws JAXBException;
}
