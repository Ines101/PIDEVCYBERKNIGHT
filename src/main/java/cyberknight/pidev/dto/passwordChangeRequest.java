package cyberknight.pidev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class passwordChangeRequest {
	private String password;
	private String passwordCheck;
}
