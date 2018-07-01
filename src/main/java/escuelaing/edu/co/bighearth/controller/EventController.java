package escuelaing.edu.co.bighearth.controller;


import escuelaing.edu.co.bighearth.model.Event;
import escuelaing.edu.co.bighearth.model.Organization;
import escuelaing.edu.co.bighearth.service.EventService;
import escuelaing.edu.co.bighearth.service.OrganizationService;
import escuelaing.edu.co.bighearth.service.ServicesException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.sql.rowset.serial.SerialBlob;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;


@RestController
@RequestMapping( "event" )
public class EventController {

    @Autowired
    private EventService eventService;

    @Autowired
    private OrganizationService organizationService;

    @CrossOrigin
    @RequestMapping( value = "/AllEvent", method = RequestMethod.GET)
    public List<Event> getEvents(){
        return eventService.getEvents();
    }

    @CrossOrigin
    @RequestMapping(value = "/createEvent/{NIT}",method = RequestMethod.POST)
    public Event createEvent(@RequestBody Event event,@PathVariable int NIT ) throws ServicesException {
        System.out.println(event.toString());
		System.out.println(NIT);
		try {
            event.setOrganization(organizationService.getOrganizationbyNIT(NIT));
	    byte[] emptyArray = new byte[0];
	    event.setImage(emptyArray);
        } catch (Exception e) {
			e.getMessage();
        }
        return eventService.createEvent(event);
    }

    @CrossOrigin
    @RequestMapping(value="/unrol/{eventId}/{email}",method = RequestMethod.POST )
    public boolean unrolUser(@PathVariable String eventId,@PathVariable String email){
        return eventService.unrolUser(Integer.parseInt(eventId),email);
    }
	
	@CrossOrigin
    @RequestMapping(value="/volunteerInEvent/{eventId}/{email}",method = RequestMethod.POST )
    public boolean volunteerInEvent(@PathVariable String eventId,@PathVariable String email){
		System.out.println(email);
		System.out.println(eventId);
		boolean ans=eventService.volunteerInEvent(Integer.parseInt(eventId),email);
        return ans;
    }

    @CrossOrigin
    @RequestMapping(value="/rol/{eventId}/{email}",method = RequestMethod.POST )
    public boolean rolUser(@PathVariable String eventId,@PathVariable String email){
        return eventService.rolUser(Integer.parseInt(eventId),email);
    }


    @CrossOrigin
    @RequestMapping( value = "/{idEvent}", method = RequestMethod.GET)
    public Event getEventById(@PathVariable String idEvent){
        return eventService.getEventById(Integer.parseInt(idEvent));
    }


    @CrossOrigin
    @RequestMapping(value="/sendMailEvent/{idEvent}",method = RequestMethod.POST )
    public boolean sendMailEvent(@PathVariable String idEvent,@RequestBody List<String> mailInfo){
        return eventService.sendMailEvent(Integer.parseInt(idEvent),mailInfo);
    }

    @CrossOrigin
    @RequestMapping(value = "/{eventid}/image/upload", method = RequestMethod.POST)
    public ResponseEntity uploadFile(MultipartHttpServletRequest request, @PathVariable String eventid) {

        try {
            Iterator<String> itr = request.getFileNames();

            while (itr.hasNext()) {
                String uploadedFile = itr.next();
                MultipartFile file = request.getFile(uploadedFile);


		Event e =eventService.getEventById(Integer.parseInt(eventid));
                e.setImage(StreamUtils.copyToByteArray(file.getInputStream()));

                return ResponseEntity.ok().body(eventService.createEvent(e));
            }
        } catch (Exception e) {
            return new ResponseEntity<>("{}", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
	
    @CrossOrigin
    @RequestMapping(value = "/{eventid}/image", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<InputStreamResource> getQRCode(@PathVariable String eventid) {
        InputStream myInputStream = new ByteArrayInputStream(eventService.getEventById(Integer.parseInt(eventid)).getImage());
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("image/png"))
                .body(new InputStreamResource(myInputStream));
    }



}