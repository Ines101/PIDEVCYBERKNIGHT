package cyberknight.pidev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cyberknight.pidev.model.Event;
import cyberknight.pidev.model.user;
import cyberknight.pidev.service.IEventService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;

@RequestMapping("/event")
@RestController

public class EventRestController {
	@Autowired
	IEventService eventService;

	// http://localhost:8086/pidev/servlet/retrieve-all-Events
	@GetMapping("/retrieve-all-Events")
	@ResponseBody
	public List<Event> getEvents() {
		List<Event> list = eventService.retrieveAllEvents();
		return list;
	}

	// http://localhost:8086/pidev/servlet/retrieve-Event/{event-id}
	@GetMapping("/retrieve-Event/{event-id}")
	@ResponseBody
	public Event retrieveEvent(@PathVariable("event-id") String id_e) {
		return eventService.retrieveEvent(id_e);
	}

	// Ajouter : http://localhost:8086/pidev/servlet/add-Event
	@PostMapping("/add-Event")
	@ResponseBody
	public Event addEvent(@RequestBody Event e) {
		Event event = eventService.addEvent(e);
		return event;
	}

	// http://localhost:8086/pidev/servlet/remove-Event/{event-id}
	@DeleteMapping("remove-Event/{event-id}")
	@ResponseBody
	public void removeEvent(@PathVariable("event-id") Long id_e) {
		eventService.deleteEvent(id_e);
	}

	// http://localhost:8086/pidev/servlet/modify-Event
	@PutMapping("/modify-Event")
	@ResponseBody
	public Event modifyEvent(@RequestBody Event e) {
		return eventService.updateEvent(e);
	}

	// http://localhost:8086/pidev/servlet/add-admin-a-event/{user-id}/{event-id}

//	@PutMapping("/add-admin-a-event/{user-id}/{event-id}")
//	@ResponseBody
//	public void affectAdmintoEvent(@PathVariable("user-id") int id_u, @PathVariable("event-id") int id_e) {
//		eventService.affectAdmintoevent(id_u, id_e);
//	}

	// recherche par nom
	// http://localhost:8086/pidev/servlet/search-Event/{event-name}
	@GetMapping("/search-Event/{event-name}")
	@ResponseBody
	public List<Event> SearchEventByName(@PathVariable("event-name") String Name_e) {
		return eventService.SearchEventByName(Name_e);
	}

	// recherche par admin
	// http://localhost:8086/pidev/servlet/search-Event-Admin/{admin}
	@GetMapping("/search-Event-Admin/{admin}")
	@ResponseBody
	public List<Event> SearchEventByAdmin(@PathVariable("admin") user admin) {
		return eventService.SearchEventByAdmin(admin);
	}
	// trie asc
	// http://localhost:8086/pidev/servlet/retrieve-all-Events-asc

	@GetMapping("/retrieve-all-Events-asc")
	@ResponseBody
	public List<Event> orderByAscendingdate() {
		List<Event> list = eventService.orderByAscendingdate();
		return list;
	}
	// trie desc
	// http://localhost:8086/pidev/servlet/retrieve-all-Events-desc

	@GetMapping("/retrieve-all-Events-desc")
	@ResponseBody
	public List<Event> orderByDescendingdate() {
		List<Event> list = eventService.orderByDescendingdate();
		return list;
	}
	// generate fichier exel
	// http://localhost:8086/pidev/servlet/excel

	@GetMapping("/excel")
	@ResponseBody
	public ResponseEntity<Resource> getFile() {
		String filename = "products.xlsx";
		InputStreamResource file = new InputStreamResource(eventService.generateexel());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
				.contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
	}

}
