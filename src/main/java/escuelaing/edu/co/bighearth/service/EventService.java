package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.Event;


import java.util.*;

public interface EventService {

    List<Event> getEvents();

    Event getEventById( int eventid );

    Event createEvent(Event event);

    boolean unrolUser(int eventId, String email);

    boolean rolUser(int eventId, String email);

    boolean sendMailEvent(int eventId,List<String> mailInfo);
	
	boolean volunteerInEvent(int eventId, String email);

}