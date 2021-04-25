package cyberknight.pidev.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString(exclude = {"reclamtionId"})
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class reclamation {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long reclamtionId;
	@NotBlank(message = "Subject is required")
	private String subject;
	private String description;
	private Instant created;
	private decision decision;
    @ManyToOne(cascade = CascadeType.ALL)
	private user user;

}
