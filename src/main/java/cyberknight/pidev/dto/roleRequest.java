package cyberknight.pidev.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class roleRequest {
	private Long roleId;
	private String roleName;
}
