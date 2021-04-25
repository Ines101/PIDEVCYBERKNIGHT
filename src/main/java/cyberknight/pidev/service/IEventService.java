package cyberknight.pidev.service;

import java.io.ByteArrayInputStream;
import java.util.List;

import org.springframework.data.repository.query.Param;

import cyberknight.pidev.model.Event;
import cyberknight.pidev.model.user;



public interface IEventService {
	List<Event> retrieveAllEvents();
	
	Event addEvent(Event e);
		
		void deleteEvent(Long id_e);
		
		Event updateEvent(Event e);
		
		Event retrieveEvent(String id_e);
		public user affectAdmintoevent();
		List<Event> SearchEventByName(String Name_e);
		List<Event> SearchEventByAdmin(user admin);
		public List<Event> orderByAscendingdate();
		public List<Event> orderByDescendingdate();
		public ByteArrayInputStream generateexel();
		
}
