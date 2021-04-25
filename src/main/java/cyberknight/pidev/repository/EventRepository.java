package cyberknight.pidev.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import cyberknight.pidev.model.Event;
import cyberknight.pidev.model.user;


@Repository
public interface EventRepository extends CrudRepository<Event, Long>{
	@Query("SELECT e FROM Event e WHERE e.Name_e= :Name_e")
	List<Event> SearchEventByName(@Param("Name_e") String Name_e);
	@Query("SELECT u FROM Event u WHERE u.admin= :admin")
	List<Event> SearchEventByAdmin(@Param("admin") user admin);
	@Query("from Event order by Date_e asc")
	public List<Event> orderByAscendingdate();
	
	@Query("from Event order by Date_e desc")
	public List<Event> orderByDescendingdate();

}
