/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.repositories;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Carlos Ramirez
 */
public interface VolunteerRepository extends JpaRepository<Volunteer,Integer>{
    @Query("SELECT v.myEvents from Volunteer as v where v.mail.mail=?1")
    public List<Event> getVolunteerListEvent(String email);

    /**
     @Modifying
     @Query("UPDATE Volunteer vt SET vt.name = :v.name,vt.lastname = :v.lastname,vt.gender = :v.gender,vt.borndate = :v.bornDate,vt.state = :v.state,vt.city = :v.city,vt.address = :v.address,vt.description = :v.description,vt.photo = :v.photo,vt.volpassword = :v.password WHERE vt.vol_id = :v.vol_id")
     int updateVolunteer(@Param("v") Volunteer v);
     */

    @Query("SELECT v FROM Volunteer AS v WHERE v.mail.mail=?1")
    public Volunteer getVolunteerbyEmail(String email);

}
