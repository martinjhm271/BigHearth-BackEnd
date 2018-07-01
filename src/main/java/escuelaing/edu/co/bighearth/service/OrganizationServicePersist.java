/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Roles;
import escuelaing.edu.co.bighearth.repositories.EventRepository;
import escuelaing.edu.co.bighearth.repositories.OrganizationRepository;
import escuelaing.edu.co.bighearth.repositories.RolUserRepository;
import escuelaing.edu.co.bighearth.repositories.RolesRepository;
import java.util.List;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Ramirez
 */
@Service
public class OrganizationServicePersist implements OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepo;

    @Autowired
    private RolesRepository rolRepo;

    @Autowired
    private RolUserRepository rolUserRepo;

    @Autowired
    private EventRepository eventRepo;

    @Override
    public List<Organization> getAllOrganizations() throws ServiceException {
        List<Organization> orgs = organizationRepo.findAll();
        return orgs;
    }

    @Override
    public Organization saveOrganziation(Organization organization) throws ServicesException {
	Roles rolOrg = rolRepo.getOne(1);
	RolUser rolUser = new RolUser(organization.getMail().getMail(), rolOrg);
        rolUserRepo.save(rolUser);
        organization.setMail(rolUser);
	return organizationRepo.save(organization);
	/**
	Roles rolOrg = rolRepo.getOne(1);
        List<RolUser> roles = rolUserRepo.findAll();
        boolean found = false;
        for (RolUser role : roles) {
            if(role.getMail().equals(organization.getMail().getMail())){
                found = true;
            }
        }
        if(found){
            throw new ServicesException("Ya existe un usuario con el correo!!");
        }else{
            RolUser rolUser = new RolUser(organization.getMail().getMail(), rolOrg);
            rolUserRepo.save(rolUser);
            organization.setMail(rolUser);
            return organizationRepo.save(organization);
        }
	*/

    }

    @Override
    public int updateOrganziation(Organization organization) throws ServicesException {
        Organization orgTemp = organizationRepo.getOrganizationbyEmail(organization.getMail().getMail());
        if(orgTemp!=null){
            orgTemp.setAddress(organization.getAddress());
            orgTemp.setBusinessName(organization.getBusinessName());
            orgTemp.setCity(organization.getCity());
            orgTemp.setCommercialName(organization.getCommercialName());
            orgTemp.setDescription(organization.getDescription());
            orgTemp.setPassword(organization.getPassword());
            orgTemp.setState(organization.getState());
            organizationRepo.save(orgTemp);
            return 0;
        }
        return 1;
    }

    @Override
    public Organization getOrganizationbyNIT(int NIT)throws ServicesException {
        return organizationRepo.getOne(NIT);
    }

    @Override
    public List<Event> getOrganizationListEvent(int NIT) throws ServicesException{
        return organizationRepo.getOrganizationListEvent(NIT);
    }
    @Override
    public Organization getOrganizationbyEmail(String email)throws ServicesException {
        return organizationRepo.getOrganizationbyEmail(email);
    }

    @Override
    public Organization getOrganizationbyIdEvent(int id) throws ServicesException {
        return eventRepo.getOrganizationbyIdEvent(id);
    }
}
