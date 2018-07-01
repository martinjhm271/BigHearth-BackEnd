package escuelaing.edu.co.bighearth.model;

import javax.persistence.*;
import java.util.jar.Attributes;

@Entity
@Table(name = "Requirement")
public class Requirement implements java.io.Serializable{

    private int id;
    private String name;
    private int quantity;
    private Event event;

    public Requirement() {
    }

    public Requirement(int id, String name, int quantity, Event event) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.event = event;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    @Column(name = "name")
    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    @Column(name = "quantity")
    public int getQuantity() { return quantity; }

    public void setQuantity(int quality) { this.quantity = quality; }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="event_id", referencedColumnName="id")
    })
    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }
}
