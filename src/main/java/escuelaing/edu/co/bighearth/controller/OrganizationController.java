/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.controller;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import escuelaing.edu.co.bighearth.model.RolUser;
import escuelaing.edu.co.bighearth.model.Volunteer;
import escuelaing.edu.co.bighearth.security.SHA1;
import escuelaing.edu.co.bighearth.service.OrganizationService;
import escuelaing.edu.co.bighearth.service.RoleService;
import escuelaing.edu.co.bighearth.service.ServicesException;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartRequest;

import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Carlos Ramirez
 */
@RestController
@RequestMapping("organization")
public class OrganizationController {

    @Autowired
    OrganizationService organizationServ;

    @CrossOrigin
    @RequestMapping( value = "/AllOrganization", method = RequestMethod.GET)
    public ResponseEntity<List<Organization>> getRoles(){
        try{
            return ResponseEntity.ok().body( organizationServ.getAllOrganizations());
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Organization> saveOrganization(@RequestBody Organization organization){
        try{
	    if(organization.getPhoto()==null){
	    	byte[] emptyArray = new byte[0];
	        organization.setPhoto(emptyArray);
	    }
            organization.setPassword(SHA1.generateHash(organization.getPassword()));
            return ResponseEntity.ok().body( organizationServ.saveOrganziation(organization));
        }catch(Exception ex){
			System.out.println(ex.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @RequestMapping( value = "/modifyProfileOrg", method = RequestMethod.POST)
    public ResponseEntity<Integer> modifyProfileOrg(@RequestBody Organization organization){
        try{
            organization.setPassword(SHA1.generateHash(organization.getPassword()));
            return ResponseEntity.ok().body(new Integer(organizationServ.updateOrganziation(organization)));
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/{nit}/events", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsOfOrganization(@PathVariable String nit){
        try{
            return ResponseEntity.ok().body( organizationServ.getOrganizationListEvent(Integer.parseInt(nit)));
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<Organization> getOrganizationByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok().body( organizationServ.getOrganizationbyEmail(email));
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/event/{id}", method = RequestMethod.GET)
    public ResponseEntity<Organization> getOrganizationByIdEvent(@PathVariable String id){
        try{
            return ResponseEntity.ok().body( organizationServ.getOrganizationbyIdEvent(Integer.parseInt(id)));
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    /**
    @CrossOrigin
    @RequestMapping(value = "/{email}/image/upload", method = RequestMethod.POST)
    public ResponseEntity<Organization> uploadFile(@RequestBody String base64Image, @PathVariable String email) {
        try {
            Organization o = organizationServ.getOrganizationbyEmail(email);
            o.setPhoto(base64Image);
            return ResponseEntity.ok().body(organizationServ.saveOrganziation(o));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @CrossOrigin
    @RequestMapping(value = "/{email}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getQRCode(@PathVariable String email) {
        try {
            return ResponseEntity.ok().body(organizationServ.getOrganizationbyEmail(email).getPhoto());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */

    @CrossOrigin
    @RequestMapping(value = "/{email}/image/upload", method = RequestMethod.POST)
    public ResponseEntity<Organization> uploadFile(MultipartHttpServletRequest request, @PathVariable String email) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);

                Organization o = organizationServ.getOrganizationbyEmail(email);
                o.setPhoto(StreamUtils.copyToByteArray(file.getInputStream()));

                return ResponseEntity.ok().body(organizationServ.saveOrganziation(o));
            }
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	

    }

    @CrossOrigin
    @RequestMapping(value = "/{email}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getQRCode(@PathVariable String email) {
        try {
            InputStream myInputStream = new ByteArrayInputStream(organizationServ.getOrganizationbyEmail(email).getPhoto());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(new InputStreamResource(myInputStream));
        } catch (ServicesException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	

    }

}
