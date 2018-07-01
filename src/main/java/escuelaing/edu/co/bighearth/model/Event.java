package escuelaing.edu.co.bighearth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.*;


@Entity
@Table(name = "Event")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Event  implements Serializable{


    private int maxVolunteers;
    private int numberVolunteers;
    private String eventType;
    private String description;
    private Date eventDate;
    private byte[] image;
    private List<Volunteer> volunteers=new ArrayList<>();
    private Organization organization;
    private List<Review> reviews;
    private List<Requirement> requirements;
    public Double latitude;
    public Double longitude;
    private String name;
    private int id;

    public Event(){

    }


    public Event(int id, int maxVolunteers, String eventType, String description, Date eventDate,byte[] image, List<Volunteer> volunteers, Organization organization, List<Review> reviews, List<Requirement> requirements,Double latitude, Double longitude,String name, int numberVolunteers){
        this.id = id;
        this.maxVolunteers = maxVolunteers;
        this.eventType = eventType;
        this.description = description;
        this.eventDate = eventDate;
        this.image = image;
        this.volunteers = volunteers;
        this.organization = organization;
        this.reviews = reviews;
        this.requirements = requirements;
        this.latitude=latitude;
        this.longitude=longitude;
        this.name=name;
        this.numberVolunteers = numberVolunteers;
    }


    @Id
    @GeneratedValue
    @Column(name = "id",  nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    public Date getEventDate() {
        return eventDate;
    }

    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }

    @Column(name = "image")
    public byte[] getImage() {
        return image;
    }

    public void  setImage(byte[] image) { this.image = image;}

    @ManyToMany(mappedBy="myEvents")
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    @Column(name = "type")
    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    @Column(name = "maxvolunteers")
    public int getMaxVolunteers() {
        return maxVolunteers;
    }

    public void setMaxVolunteers(int maxVolunteers) {
        this.maxVolunteers = maxVolunteers;
    }

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="organization")
    @JsonIgnore
    public Organization getOrganization(){
        return this.organization;
    }

    public void setOrganization(Organization organization){
        this.organization=organization;
    }

    @Column(name = "latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @Column(name = "longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @OneToMany(mappedBy= "event", cascade = CascadeType.ALL)
    public List<Review> getReviews() {return reviews; }

    public void setReviews(List<Review> reviews) { this.reviews = reviews; }

    @OneToMany(mappedBy= "event",cascade = CascadeType.ALL)
    public List<Requirement> getRequirements() { return requirements; }

    public void setRequirements(List<Requirement> requirements) { this.requirements = requirements; }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "numbervolunteers")
    public int getNumberVolunteers() {
        return numberVolunteers;
    }

    public void setNumberVolunteers(int numberVolunteers) {
        this.numberVolunteers = numberVolunteers;
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[Event -> id:"+this.id+" name:"+this.name+" maxVolunteers:"+this.maxVolunteers+" numberVolunteers:"+this.numberVolunteers+" type:"+this.eventType+" description:"+this.description+" date:"+this.eventDate+" image:"+this.image+" volunteers:"+this.volunteers+" reviews:"+this.reviews+" requierements:"+this.requirements+" latitude:"+this.latitude+" longitude:"+this.longitude+"]";
    }

}
