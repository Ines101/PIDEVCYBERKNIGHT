package cyberknight.pidev.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

@Entity
@DynamicUpdate
@Table(name = "Ads")
public class Ads {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdAds")
	private Long id;

	@NotBlank(message = "Fill Product name")
	@Column(name = "productname")
	private String productname;

	@Column(name = "Startdate")
	private Date startdate;

	@Column(name = "Enddate")
	private Date Enddate;
	@NotBlank(message = "Add ad banner")
	@Column(name = "Adbanner")
	private String banner;

	@Column(name = "Status")
	private String Status;

	public user getUser() {
		return user;
	}

	public void setUser(user user) {
		this.user = user;
	}

	@ManyToOne(cascade = CascadeType.ALL)
	private user user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public Date getStartdate() {
		return startdate;
	}

	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	public Date getEnddate() {
		return Enddate;
	}

	public void setEnddate(Date enddate) {
		Enddate = enddate;
	}

	public String getBanner() {
		return banner;
	}

	public void setBanner(String banner) {
		this.banner = banner;
	}

	public Ads(@NotBlank(message = "Fill Product name") String productname, Date startdate, Date enddate,
			@NotBlank(message = "Add ad banner") String banner, String status) {
		super();
		this.productname = productname;
		this.startdate = startdate;
		Enddate = enddate;
		this.banner = banner;
		Status = status;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Ads() {
		Status = "En cours";

	}

}
