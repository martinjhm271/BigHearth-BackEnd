/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Roles;
import escuelaing.edu.co.bighearth.model.Volunteer;
import escuelaing.edu.co.bighearth.repositories.RolUserRepository;
import escuelaing.edu.co.bighearth.repositories.RolesRepository;
import escuelaing.edu.co.bighearth.repositories.VolunteerRepository;
import java.util.List;
import javax.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Carlos Ramirez
 */
@Service
public class VolunteerServicePersist implements VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepo;

    @Autowired
    private RolesRepository rolRepo;

    @Autowired
    private RolUserRepository rolUserRepo;

    @Override
    public List<Volunteer> getAllVolunteer() throws ServicesException {
        return volunteerRepo.findAll();
    }

    @Override
    public Volunteer saveVolunteer(Volunteer volunteer) throws ServicesException {
        Roles rolVol = rolRepo.getOne(2);
	RolUser rolUser = new RolUser(volunteer.getMail().getMail(), rolVol);
        rolUserRepo.save(rolUser);
        volunteer.setMail(rolUser);
	return volunteerRepo.save(volunteer);
	/**
        List<RolUser> rol = rolUserRepo.findAll();
        boolean found = false;
        for (RolUser rolUser : rol) {
            if (rolUser.getMail().equals(volunteer.getMail().getMail())) {
                found = true;
            }
        }
        if (found) {
            throw new ServicesException("Ya existe un usuario con el correo!!");
        } else {
            volunteer.setVol_id(volunteerRepo.findAll().size() + 1);
            RolUser rolUser = new RolUser(volunteer.getMail().getMail(), rolVol);
            rolUserRepo.save(rolUser);
            volunteer.setMail(rolUser);
            return volunteerRepo.save(volunteer);
        }
	*/
    }

    @java.lang.Override
    public int updateVolunteer(Volunteer volunteer) throws ServicesException {
        Volunteer volTemp = volunteerRepo.getVolunteerbyEmail(volunteer.getMail().getMail());
        if(volTemp!=null){
            volTemp.setAddress(volunteer.getAddress());
            volTemp.setBornDate(volunteer.getBornDate());
            volTemp.setCity(volunteer.getCity());
            volTemp.setDescription(volunteer.getDescription());
            volTemp.setGender(volunteer.getGender());
            volTemp.setLastname(volunteer.getLastname());
            volTemp.setName(volunteer.getName());
            volTemp.setPassword(volunteer.getPassword());
            volTemp.setState(volunteer.getState());
            volTemp.setVolInterest(volunteer.getVolInterest());
            volunteerRepo.save(volTemp);
            return 0;
        }
        return 1;
    }

    @Override
    public Volunteer getVolunteerById(int id) throws ServicesException {
        return volunteerRepo.getOne(id);
    }

    @Override
    public List<Event> getVolunteerListEvent(String email) throws ServicesException {
        return volunteerRepo.getVolunteerListEvent(email);
    }

    @Override
    public Volunteer getVolunteerByEmail(String email) throws ServicesException {
        return volunteerRepo.getVolunteerbyEmail(email);
    }
}
