/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Fetch;

/**
 *
 * @author Carlos Ramirez
 */
@Entity
@Table(name = "Rol_User")
public class RolUser implements java.io.Serializable{
    
    @Id
    @Column(name = "mail", nullable = false)
    private String mail;
    
    @Fetch(FetchMode.JOIN)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rol_id", nullable = false)
    private Roles rol_id;

    public RolUser() {
    }

    public RolUser(String mail, Roles rol_id) {
        this.mail = mail;
        this.rol_id = rol_id;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Roles getRol_id() {
        return rol_id;
    }

    public void setRol_id(Roles rol_id) {
        this.rol_id = rol_id;
    }

    @Override
    public String toString(){
        return "[RolUser -> mail: "+mail+", rol: "+rol_id.toString()+"]";
    }
    
}
