package escuelaing.edu.co.bighearth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import java.sql.Blob;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Organization")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Organization implements java.io.Serializable{

    private static final long serialVersionUID = 2544678631302988300L;

    @Column(name = "commercialname", nullable = false)
    private String commercialName;

    @Column(name = "businessname", nullable = false)
    private String businessName;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @JsonProperty(access = Access.WRITE_ONLY)
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "mail", nullable = false)
    private RolUser mail;

   
    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @Column(name = "orgpassword", nullable = false)
    private String password;

    @Id
    @Column(name = "nit", nullable = false)
    private int nit;

    @Column(name = "volunteersmade", nullable = false)
    private int volunteersMade;

    @OneToMany(mappedBy = "organization")
    private List<Event> myEvents;

    public Organization() {
    }

    public Organization(String commercialName, String businessName, String state, String city, String address, String description, RolUser mail, byte[] photo, String password, int nit, int volunteersMade, List<Event> myEvents) {
        this.commercialName = commercialName;
        this.businessName = businessName;
        this.state = state;
        this.city = city;
        this.address = address;
        this.description = description;
        this.mail = mail;
        this.photo = photo;
        this.password = password;
        this.nit = nit;
        this.volunteersMade = volunteersMade;
        this.myEvents = myEvents;
    }

    public String getCommercialName() {
        return commercialName;
    }

    public void setCommercialName(String commercialName) {
        this.commercialName = commercialName;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RolUser getMail() {
        return mail;
    }

    public void setMail(RolUser mail) {
        this.mail = mail;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNit() {
        return nit;
    }

    public void setNit(int nit) {
        this.nit = nit;
    }

    public int getVolunteersMade() {
        return volunteersMade;
    }

    public void setVolunteersMade(int volunteersMade) {
        this.volunteersMade = volunteersMade;
    }

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name="Organization_nit", referencedColumnName="nit", nullable=false)
    })
    public List<Event> getMyEvents() { return myEvents; }

    public void setMyEvents(List<Event> myEvents) { this.myEvents = myEvents; }

    @Override
    public String toString(){
        return "[Organization -> commercialName: "+commercialName+", businessName: "+businessName+", state: "+state+", city: "+city+", address: "+address+", mail: "+mail.toString()+", password: "+password+", photo: "+photo+", NIT: "+Integer.toString(nit)+", volunteersMade: "+Integer.toString(volunteersMade)+"]";
    }
}