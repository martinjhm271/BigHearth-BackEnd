package escuelaing.edu.co.bighearth.service;

import escuelaing.edu.co.bighearth.model.*;
import escuelaing.edu.co.bighearth.mail.Mail;
import escuelaing.edu.co.bighearth.repositories.EventRepository;
import escuelaing.edu.co.bighearth.repositories.OrganizationRepository;
import escuelaing.edu.co.bighearth.repositories.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;


@Service
public class EventServiceImpl implements EventService{

    @Autowired
    private EventRepository eventRepo;
    @Autowired
    private OrganizationRepository orgRepo;
    @Autowired
    private VolunteerRepository volunteerRepo;



    public EventServiceImpl() { }
    
    



    @Override
    public List<Event> getEvents()
    {
        List<Event> events = eventRepo.findAll();
        return events;
    }

    @Override
    public Event getEventById( int eventId ) {

        return eventRepo.getOne(eventId);
    }

    @Override
    public Event createEvent( Event event) {
        //System.out.println("el evento a guardar: "+event.toString());
        Event eventemp=eventRepo.save(event);
        //Organization org = orgRepo.getOrganizationbyEmail(event.getOrganization().getMail().getMail());
        //org.getMyEvents().add(event);
        //orgRepo.save(org);
        return eventemp;
    }


    @Override
    public boolean sendMailEvent(int eventId, List<String> mailInfo) {
        List<String> emails=new ArrayList<>();
        //for(Volunteer v :eventRepo.getOne(eventId).getVolunteers()){
          //  emails.add(v.getMail().getMail());
        //}
        emails.add("cristian.pinzon@mail.escuelaing.edu.co");
        return Mail.sendEmail(emails,mailInfo);
    }

    @Override
    public boolean unrolUser(int eventId, String email) {
        Event eventemp = eventRepo.getOne(eventId);
        Volunteer voltemp = volunteerRepo.getVolunteerbyEmail(email);
        eventemp.getVolunteers().remove(voltemp);
        eventRepo.save(eventemp);
        voltemp.getMyEvents().remove(eventemp);
        volunteerRepo.save(voltemp);
        return true;
    }
	
	@Override
    public boolean volunteerInEvent(int eventId, String email) {
        Event eventemp = eventRepo.getOne(eventId);
        Volunteer voltemp = volunteerRepo.getVolunteerbyEmail(email);
        boolean bandera=false;
		for(Volunteer v : eventemp.getVolunteers()){
			if(v.getVol_id()==voltemp.getVol_id()){
				bandera=true;
				break;
			}
		}
        return bandera;
    }

    @Override
    public boolean rolUser(int eventId, String email) {
        try{
            Event eventemp = eventRepo.getOne(eventId);
            Volunteer voltemp = volunteerRepo.getVolunteerbyEmail(email);
            if(eventemp.getVolunteers().size() < eventemp.getMaxVolunteers() && !eventemp.getVolunteers().contains(voltemp) && !voltemp.getMyEvents().contains(eventemp)){
                eventemp.getVolunteers().add(voltemp);
                eventRepo.save(eventemp);
                voltemp.getMyEvents().add(eventemp);
                volunteerRepo.save(voltemp);
                return true;
            }else{
                return false;
            }
        }catch(Exception e) {
            return false;
        }
    }


}
