package cyberknight.pidev.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "Categoryproduct")
public class Categoryproduct {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idCategory")
	private Long id;

	@NotBlank(message = "Fill name")
	@Column(name = "Categoryname")
	private String Categoryname;

	public String getCategoryname() {
		return Categoryname;
	}

	public void setCategoryname(String categoryname) {
		Categoryname = categoryname;
	}

	public Categoryproduct(@NotBlank(message = "Fill name") String categoryname) {
		super();
		Categoryname = categoryname;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Categoryproduct() {

	}

}
