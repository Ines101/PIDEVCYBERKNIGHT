package cyberknight.pidev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import static javax.persistence.GenerationType.IDENTITY;

@ToString(exclude = {"roleId"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class role {
	@Id
    @GeneratedValue(strategy = IDENTITY)
    private Long roleId;
    @NotBlank(message = "Role Name is required")
    private String roleName;

}
