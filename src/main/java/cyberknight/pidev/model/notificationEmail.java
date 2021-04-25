package cyberknight.pidev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class notificationEmail {
    private String subject;
    private String recipient;
    private String body;
}
