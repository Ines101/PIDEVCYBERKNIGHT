package cyberknight.pidev.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Event implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id_e;
	private String Name_e;
	private String Location_e;
	@Temporal (TemporalType.DATE)
	private Date Date_e;
	private int Max_participants_e;
	@ManyToOne
	user admin;
	public Event(long id_e, String name_e, String location_e, Date date_e, int max_participants_e) {
		super();
		this.id_e = id_e;
		Name_e = name_e;
		Location_e = location_e;
		Date_e = date_e;
		Max_participants_e = max_participants_e;
	}
	public Event(String name_e, String location_e, Date date_e, int max_participants_e) {
		super();
		Name_e = name_e;
		Location_e = location_e;
		Date_e = date_e;
		Max_participants_e = max_participants_e;
	}
	public Event() {
		super();
	}
	public long getId_e() {
		return id_e;
	}
	public user getAdmin() {
		return admin;
	}
	public void setAdmin(user admin) {
		this.admin = admin;
	}
	public void setId_e(long id_e) {
		this.id_e = id_e;
	}
	public String getName_e() {
		return Name_e;
	}
	public void setName_e(String name_e) {
		Name_e = name_e;
	}
	public String getLocation_e() {
		return Location_e;
	}
	public void setLocation_e(String location_e) {
		Location_e = location_e;
	}
	public Date getDate_e() {
		return Date_e;
	}
	public void setDate_e(Date date_e) {
		Date_e = date_e;
	}
	public int getMax_participants_e() {
		return Max_participants_e;
	}
	public void setMax_participants_e(int max_participants_e) {
		Max_participants_e = max_participants_e;
	}
	public Event(long id_e, String name_e, String location_e, Date date_e, int max_participants_e, user admin) {
		super();
		this.id_e = id_e;
		Name_e = name_e;
		Location_e = location_e;
		Date_e = date_e;
		Max_participants_e = max_participants_e;
		this.admin = admin;
	}


	
	
	
	

}
