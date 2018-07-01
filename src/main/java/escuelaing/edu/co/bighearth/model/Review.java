/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 *
 * @author Carlos Ramirez
 */
@Entity
@Table(name = "Review")
public class Review implements java.io.Serializable{
    private int id;
    private String comment;
    private int score;
    private Event event;

    public Review() {
    }

    public Review(int id, String comment, int score, Event event) {
        this.id = id;
        this.comment = comment;
        this.score = score;
        this.event = event;
    }

    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "score")
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    @ManyToOne
    @JoinColumns({
            @JoinColumn(name="event_id", referencedColumnName="id")
    })
    public Event getEvent() { return event; }

    public void setEvent(Event event) { this.event = event; }
    
}
