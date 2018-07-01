/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Roles;
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
public class RoleServicePersist implements RoleService{

    @Autowired
    private RolesRepository rolesRepo;
    
    @Autowired
    private RolUserRepository rolUserRepo;
    
    @Override
    public Roles saveRol(Roles rol) throws ServiceException{
        return rolesRepo.save(rol);
    }

    @Override
    public List<Roles> getAllRoles() throws ServiceException{
        return rolesRepo.findAll();
    }

    @Override
    public RolUser saveRolUser(RolUser rolUser) throws ServiceException{
        return rolUserRepo.save(rolUser);
    }

    @Override
    public List<RolUser> getAllRolesUsers() throws ServiceException{
        return rolUserRepo.findAll();
    }
    
}
