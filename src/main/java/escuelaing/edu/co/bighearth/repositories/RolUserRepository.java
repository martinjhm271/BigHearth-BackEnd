/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.repositories;

import escuelaing.edu.co.bighearth.model.RolUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Carlos Ramirez
 */
public interface RolUserRepository extends JpaRepository<RolUser,String>{
    
}
