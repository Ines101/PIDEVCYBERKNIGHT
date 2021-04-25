package cyberknight.pidev.service;

import java.io.ByteArrayInputStream;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import cyberknight.pidev.exception.TokenException;
import cyberknight.pidev.model.Event;
import cyberknight.pidev.model.user;
import cyberknight.pidev.repository.EventRepository;
import cyberknight.pidev.repository.userRepository;



@Service
public class EventServiceImpl implements IEventService  {

	@Autowired
	EventRepository eventRepository ;
	@Autowired
	userRepository adminRepository ;
	@Override
	public List<Event> retrieveAllEvents() {
		// TODO Auto-generated method stub
		return (List<Event>) eventRepository.findAll();
	}

	@Override
	public Event addEvent(Event e) {
		// TODO Auto-generated method stub
		e.setAdmin(affectAdmintoevent());
		return eventRepository.save(e);
	}

	@Override
	public void deleteEvent(Long id_e) {
		// TODO Auto-generated method stub
		eventRepository.deleteById((long) id_e);

		
	}

	@Override
	public Event updateEvent(Event e) {
		// TODO Auto-generated method stub
		e.setAdmin(affectAdmintoevent());
		return eventRepository.save(e);
	}

	@Override
	public Event retrieveEvent(String id_e) {
		// TODO Auto-generated method stub
		return eventRepository.findById(Long.parseLong(id_e)).orElse(null);

	}

	@Override
	public user affectAdmintoevent() {
		// TODO Auto-generated method stub
//		Event event= eventRepository.findById((long)id_e).orElse(null);
//		user admin= adminRepository.findById((long)id_u).orElse(null);
//		event.setAdmin(admin);
//		 eventRepository.save(event);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username = "";
		if (principal instanceof UserDetails) {
			username = ((UserDetails) principal).getUsername();
		} else {
			username = principal.toString();
		}
		Optional<user> opUser = adminRepository.findByUsername(username);
		opUser.orElseThrow(() -> new TokenException("User not found"));
		user user = new user();
		return user = opUser.get();
		
		
		
	}

	@Override
	public List<Event> SearchEventByName(String Name_e) {
		// TODO Auto-generated method stub
		return eventRepository.SearchEventByName(Name_e) ;
	}

	@Override
	public List<Event> SearchEventByAdmin(user admin) {
		// TODO Auto-generated method stub
		return eventRepository.SearchEventByAdmin(admin) ;
	}

	@Override
	public List<Event> orderByAscendingdate() {
		// TODO Auto-generated method stub
		return eventRepository.orderByAscendingdate();
	}

	@Override
	public List<Event> orderByDescendingdate() {
		// TODO Auto-generated method stub
		return eventRepository.orderByDescendingdate();
	}

	@Override
	public ByteArrayInputStream generateexel() {
		// TODO Auto-generated method stub
		List<Event> events = (List<Event>) eventRepository.findAll();

	    ByteArrayInputStream in = ExcelHelper.productsToExcel(events);
	    return in;
	}

}
