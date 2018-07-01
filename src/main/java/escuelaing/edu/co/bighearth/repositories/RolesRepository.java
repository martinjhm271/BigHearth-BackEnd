/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.repositories;

import escuelaing.edu.co.bighearth.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Carlos Ramirez
 */
public interface RolesRepository extends JpaRepository<Roles,Integer>{
    
}
