/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Volunteer;
import java.util.List;

/**
 *
 * @author Carlos Ramirez
 */
public interface VolunteerService {
    List<Volunteer> getAllVolunteer() throws ServicesException;
    Volunteer saveVolunteer(Volunteer volunteer) throws ServicesException;
    int updateVolunteer(Volunteer volunteer) throws ServicesException;
    Volunteer getVolunteerById(int id) throws ServicesException;
    List<Event> getVolunteerListEvent(String email) throws ServicesException;
    Volunteer getVolunteerByEmail(String email) throws ServicesException;
}
