/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Carlos Ramirez
 */
@Entity
@Table(name = "Roles")
public class Roles implements java.io.Serializable{
    
    @Id
    @GeneratedValue
    @Column(name = "rol_id", unique = true, nullable = false)
    private int rolid;
    
    @Column(name = "name", unique = true, nullable = false, length = 15)
    private String name;

    public Roles() {
    }

    public Roles(int rolid, String name) {
        this.rolid = rolid;
        this.name = name;
    }

    public int getRolid() {
        return rolid;
    }

    public void setRolid(int rolid) {
        this.rolid = rolid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "RolId[id: "+Integer.toString(rolid)+" ,name: "+name+"]";
    } 
    
}
