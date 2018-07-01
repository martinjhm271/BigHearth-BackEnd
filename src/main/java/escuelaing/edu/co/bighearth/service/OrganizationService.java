/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import java.util.List;

/**
 *
 * @author Carlos Ramirez
 */
public interface OrganizationService {
    List<Organization> getAllOrganizations() throws ServicesException;
    Organization saveOrganziation(Organization organization) throws ServicesException;
    int updateOrganziation(Organization organization) throws ServicesException;
    Organization getOrganizationbyNIT(int NIT) throws ServicesException;;
    List<Event> getOrganizationListEvent(int NIT) throws ServicesException;;
    Organization getOrganizationbyEmail(String email) throws ServicesException;;
    Organization getOrganizationbyIdEvent(int id ) throws ServicesException;;
}
