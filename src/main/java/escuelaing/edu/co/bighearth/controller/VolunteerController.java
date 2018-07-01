/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escuelaing.edu.co.bighearth.controller;

import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import escuelaing.edu.co.bighearth.model.Volunteer;
import escuelaing.edu.co.bighearth.security.SHA1;
import escuelaing.edu.co.bighearth.service.ServicesException;
import escuelaing.edu.co.bighearth.service.VolunteerService;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

import javax.sql.rowset.serial.SerialBlob;

/**
 *
 * @author Carlos Ramirez
 */
@RestController
@RequestMapping("volunteer")
public class VolunteerController {

    @Autowired
    VolunteerService volunteerServ;

    @CrossOrigin
    @RequestMapping( value = "/AllVolunteers", method = RequestMethod.GET)
    public ResponseEntity<List<Volunteer>> getRoles(){
        try{
            return ResponseEntity.ok().body( volunteerServ.getAllVolunteer());
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( method = RequestMethod.POST)
    public ResponseEntity<Volunteer> saveVolunteer(@RequestBody Volunteer volunteer){
        try{
            volunteer.setPassword(SHA1.generateHash(volunteer.getPassword()));
            return ResponseEntity.ok().body( volunteerServ.saveVolunteer(volunteer));
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/modifyProfileVol", method = RequestMethod.PUT)
    public ResponseEntity<Integer> modifyProfileVol(@RequestBody Volunteer v){
        try{
            v.setPassword(SHA1.generateHash(v.getPassword()));
            return ResponseEntity.ok().body(new Integer(volunteerServ.updateVolunteer(v)));
        }catch(Exception ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/{email}/events", method = RequestMethod.GET)
    public ResponseEntity<List<Event>> getEventsOfVolunteer(@PathVariable String email){
        try{
            return ResponseEntity.ok().body( volunteerServ.getVolunteerListEvent(email));
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin
    @RequestMapping( value = "/{email}", method = RequestMethod.GET)
    public ResponseEntity<Volunteer> getVolunteerByEmail(@PathVariable String email){
        try{
            return ResponseEntity.ok().body( volunteerServ.getVolunteerByEmail(email));
        }catch(ServicesException ex){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	/**
    @CrossOrigin
    @RequestMapping(value = "/{email}/image/upload", method = RequestMethod.POST)
    public ResponseEntity<Volunteer> uploadFile(@RequestBody String base64Image, @PathVariable String email) {
        try {
                Volunteer v =volunteerServ.getVolunteerByEmail(email);
                v.setPhoto(base64Image);
                return ResponseEntity.ok().body(volunteerServ.saveVolunteer(v));
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    @CrossOrigin
    @RequestMapping(value = "/{email}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<String> getQRCode(@PathVariable String email) {
        try {
            return ResponseEntity.ok().body(volunteerServ.getVolunteerByEmail(email).getPhoto());
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    */



    @CrossOrigin
    @RequestMapping(value = "/{email}/image/upload", method = RequestMethod.POST)
    public ResponseEntity<Volunteer> uploadFile(MultipartHttpServletRequest request, @PathVariable String email) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);

                Volunteer v =volunteerServ.getVolunteerByEmail(email);
                v.setPhoto(StreamUtils.copyToByteArray(file.getInputStream()));

                return ResponseEntity.ok().body(volunteerServ.saveVolunteer(v));
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
	return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @CrossOrigin
    @RequestMapping(value = "/{email}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getQRCode(@PathVariable String email) {
        try {
            InputStream myInputStream = new ByteArrayInputStream(volunteerServ.getVolunteerByEmail(email).getPhoto());
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("image/png"))
                    .body(new InputStreamResource(myInputStream));
        } catch (ServicesException ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}