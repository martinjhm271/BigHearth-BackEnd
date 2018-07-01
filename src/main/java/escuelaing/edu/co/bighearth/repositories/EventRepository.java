package escuelaing.edu.co.bighearth.repositories;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;
import org.springframework.data.jpa.repository.JpaRepository;


@Service
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Query("SELECT e.organization FROM Event AS e WHERE e.organization.nit=?1")
    public Organization getOrganizationbyIdEvent(int id);



}
