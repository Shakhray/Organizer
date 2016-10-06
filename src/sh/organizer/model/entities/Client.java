package sh.organizer.model.entities;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * @author Sherhan
 */
@XmlRootElement(name = "client")
public class Client {

    private int id;
    private String name;
    private String position;
    private String organization;
    private String email;
    private List<String> phones;

    public int getId() {
        return id;
    }

    @XmlAttribute(name = "id")
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    @XmlElement(name = "name")
    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    @XmlElement(name = "position")
    public void setPosition(String position) {
        this.position = position;
    }

    public String getOrganization() {
        return organization;
    }

    @XmlElement(name = "organization")
    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getEmail() {
        return email;
    }

    @XmlElement(name = "email")
    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getPhones() {
        return phones;
    }

    @XmlElement(name = "phone")
    @XmlElementWrapper
    public void setPhones(List<String> phones) {
        this.phones = phones;
    }

    @Override
    public String toString() {
        String data = "id=" + id + "; name=" + name + "; position=" + position + "; organization=" + organization + "; email=" + email + ", ";
        String phonesStr = "";
        for (String phone : phones) {
            phonesStr += phone + ", ";
        }
        if (!phonesStr.isEmpty()) {
            phonesStr = phonesStr.substring(0, phonesStr.lastIndexOf(", "));
        }
        return data + "phones= " + phonesStr;
    }
}
