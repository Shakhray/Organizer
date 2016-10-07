package sh.organizer.model.entities;


import javax.xml.bind.annotation.*;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Sherhan
 */
@XmlRootElement(name = "client")
@XmlType(propOrder = {"name", "position", "organization", "email", "phones"})
public class Client {

    private int id;
    private String name;
    private String position;
    private String organization;
    private String email;
    private List<String> phones;

    public Client() {
        id = 0;
        name = "";
        position = "";
        organization = "";
        email = "";
        phones = new LinkedList<>();
    }

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
    @XmlElementWrapper(name = "phones")
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!obj.getClass().getCanonicalName().equals(this.getClass().getCanonicalName())) return false;

        Client otherClient = (Client) obj;
        return id == otherClient.getId()
                && name.equals(otherClient.getName())
                && organization.equals(otherClient.getOrganization())
                && position.equals(otherClient.getPosition())
                && email.equals(otherClient.getEmail())
                && phones.equals(otherClient.getPhones());
    }

    @Override
    public int hashCode() {
        return id * 32 + 12
                + name.hashCode()
                + organization.hashCode()
                + position.hashCode()
                + email.hashCode()
                + phones.hashCode();
    }
}
