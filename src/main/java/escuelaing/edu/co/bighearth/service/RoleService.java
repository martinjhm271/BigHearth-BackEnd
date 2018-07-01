/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Roles;
import java.util.List;
import org.hibernate.service.spi.ServiceException;

/**
 *
 * @author Carlos Ramirez
 */
public interface RoleService {
    Roles saveRol(Roles rol) throws ServiceException;
    List<Roles> getAllRoles() throws ServiceException;
    RolUser saveRolUser(RolUser rolUser) throws ServiceException;
    List<RolUser> getAllRolesUsers() throws ServiceException;
}
