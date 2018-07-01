/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.controller;

import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Roles;
import escuelaing.edu.co.bighearth.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Carlos Ramirez
 */
@RestController
@RequestMapping( "roles" )
public class RolesController {
    
    @Autowired
    private RoleService roleServ;
    
    @CrossOrigin
    @RequestMapping( value = "/Roles", method = RequestMethod.GET)
    public ResponseEntity<List<Roles>> getAllRoles(){
        return ResponseEntity.ok().body(roleServ.getAllRoles());
    }
    
    @CrossOrigin
    @RequestMapping( value = "/saveRoles", method = RequestMethod.POST)
    public ResponseEntity<Roles> saveRoles(Roles rol){
        return ResponseEntity.ok().body(roleServ.saveRol(rol));
    }
    
    @CrossOrigin
    @RequestMapping( value = "/RolesUser", method = RequestMethod.GET)
    public ResponseEntity<List<RolUser>> getRolesUser(){
        return ResponseEntity.ok().body( roleServ.getAllRolesUsers());
    }
    
    @CrossOrigin
    @RequestMapping( value = "/saveRoleUser", method = RequestMethod.POST)
    public ResponseEntity<RolUser> saveRoleUser(@RequestBody RolUser rolUser){
        return ResponseEntity.ok().body( roleServ.saveRolUser(rolUser));
    }
}
