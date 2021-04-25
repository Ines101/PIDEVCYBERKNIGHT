package cyberknight.pidev.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@DynamicUpdate
@Table(name = "Product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotBlank(message = "Fill name")
	@Column(name = "name")
	private String name;

	@Column(name = "barcode")
	private String barcode;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private Categoryproduct category;

	@Column(name = "stock")
	private Integer stock;

	@Column(name = "price")
	private Float price;

	@Column(name = "materiel")
	private String materiel;

	@Column(name = "rating")
	private Float rating;

	@Column(name = "likes")
	private Integer likes;

	public Product() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public Categoryproduct getCategory() {
		return category;
	}

	public void setCategory(Categoryproduct category) {
		this.category = category;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Float getPrice() {
		return price;
	}

	public void setPrice(Float price) {
		this.price = price;
	}

	public String getMateriel() {
		return materiel;
	}

	public void setMateriel(String materiel) {
		this.materiel = materiel;
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(Float rating) {
		this.rating = rating;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Product(Long id, @NotBlank(message = "Fill name") String name, String barcode, Categoryproduct category,
			Integer stock, Float price, String materiel, Float rating, Integer likes) {
		super();
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.category = category;
		this.stock = stock;
		this.price = price;
		this.materiel = materiel;
		this.rating = rating;
		this.likes = likes;
	}

}
