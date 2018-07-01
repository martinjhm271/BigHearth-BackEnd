package escuelaing.edu.co.bighearth.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "Volunteer")
public class Volunteer  implements java.io.Serializable {

    private static final long serialVersionUID = -4224112129753973637L;

    @Id
    @Column(name = "vol_id", nullable = false,unique = true)
    private Integer vol_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "borndate", nullable = false)
    private Date bornDate;

    @Column(name = "hours", nullable = false)
    private int hours;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "city", nullable = false)
    private String city;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "volunteersmade", nullable = false)
    private int volunteerMade;

    @Column(name = "photo", nullable = false)
    private byte[] photo;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(name = "mail", nullable = false)
    private RolUser mail;

    @Column(name = "volpassword", nullable = false)
    private String password;

    @Column(name = "interest", nullable = false)
    private String volInterest;

    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JsonIgnore
    @JoinTable(name = "Volunteer_Event", joinColumns = {
            @JoinColumn(name = "volunteer_id",nullable = false, updatable = false) },
            inverseJoinColumns = { @JoinColumn(name = "event_id",nullable = false, updatable = false) })
    private List<Event> myEvents;


    public Volunteer() {
    }


    public Volunteer(Integer vol_id, String name, String lastname, String gender, Date bornDate, int hours, String state, String city, String address, String description, int volunteerMade, byte[] photo, RolUser mail, String password, String volInterest, List<Event> myEvents) {
        this.vol_id = vol_id;
        this.name = name;
        this.lastname = lastname;
        this.gender = gender;
        this.bornDate = bornDate;
        this.hours = hours;
        this.state = state;
        this.city = city;
        this.address = address;
        this.description = description;
        this.volunteerMade = volunteerMade;
        this.photo = photo;
        this.mail = mail;
        this.password = password;
        this.volInterest = volInterest;
        this.myEvents = myEvents;
    }

    public Integer getVol_id() {
        return vol_id;
    }

    public void setVol_id(Integer vol_id) {
        this.vol_id = vol_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
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

    public int getVolunteerMade() {
        return volunteerMade;
    }

    public void setVolunteerMade(int volunteerMade) {
        this.volunteerMade = volunteerMade;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public RolUser getMail() {
        return mail;
    }

    public void setMail(RolUser mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getVolInterest() {
        return volInterest;
    }

    public void setVolInterest(String volInterest) {
        this.volInterest = volInterest;
    }


    public List<Event> getMyEvents() { return this.myEvents; }

    public void setMyEvents(List<Event> myEvents) { this.myEvents = myEvents; }

    /*
    public boolean confirmEventInscription(int idEvent){
        for (VolunteerEvent event: this.getVolEvents() ) {
            if(event.getEvent().getEventId().getId() == idEvent){
                return true;
            }
        }
        return false;
    }*/

    @Override
    public String toString(){
        return "[Volunteer -> name: "+name+", id: "+Integer.toString(vol_id)+", lastname: "+lastname+", gender: "+gender+", hours: "+Integer.toString(hours)+", state: "+state+", city: "+city+", address: "+address+", description: "+description+", volunteersmade: "+Integer.toString(volunteerMade)+", photo: "+photo+", volPassword: "+password+", interest: "+volInterest+"]";
    }
}
