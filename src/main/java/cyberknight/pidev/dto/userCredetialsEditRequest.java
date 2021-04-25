package cyberknight.pidev.dto;

import java.time.Instant;

import cyberknight.pidev.model.decision;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class userCredetialsEditRequest {
    private String email;
    private String adress;
    private String number;
    private String name;
    private String lastName;
}
