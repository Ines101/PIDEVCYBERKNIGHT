package cyberknight.pidev.dto;

import static javax.persistence.GenerationType.IDENTITY;

import java.time.Instant;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import cyberknight.pidev.model.decision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class reclamationRequest {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long reclamtionId;
	@NotBlank(message = "Subject is required")
	private String subject;
	private String description;
	private Instant created;
	private decision decision;
}
