/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.repositories;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 *
 * @author Carlos Ramirez
 */
public interface OrganizationRepository extends JpaRepository<Organization,Integer>{

    @Query("SELECT o.myEvents from Organization as o where o.nit=?1")
    public List<Event> getOrganizationListEvent(int NIT);

    /**
     @Modifying
     @Query("UPDATE Organization ot SET ot.commercialName = :o.commercialName,ot.businessName = :o.businessName,ot.state = :o.state,ot.city = :o.city,ot.address = :o.address,ot.description = :o.description,ot.photo = :o.photo,ot.password = :o.password WHERE ot.nit = :o.nit")
     int updateOrganization(@Param("o") Organization o);
     */

    @Query("SELECT o FROM Organization AS o WHERE o.mail.mail=?1")
    public Organization getOrganizationbyEmail(String email);

    @Query("SELECT e.organization FROM Event AS e WHERE e.organization.nit=?1")
    public Organization getOrganizationbyIdEvent(int id);


}
