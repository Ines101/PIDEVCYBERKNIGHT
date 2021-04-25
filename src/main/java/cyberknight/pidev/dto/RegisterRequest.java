package cyberknight.pidev.dto;


import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
	private String username;
	@Email
    private String email;	
    private String password;
    //private boolean mfa;
    private String adress;
    private String number;
    private int hours;
    private Float salary;
    private String name;
    private String lastName;
}
