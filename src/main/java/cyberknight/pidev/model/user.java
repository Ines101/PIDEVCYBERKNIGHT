package cyberknight.pidev.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.DynamicUpdate;

import java.time.Instant;

import static javax.persistence.GenerationType.IDENTITY;

@ToString(exclude = {"userId","password","secret"})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class user {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long userId;
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
    private String name;
    private String lastName;
    @Email
    @NotEmpty(message = "Email is required")
    private String email;
    private Instant created;
    private boolean enabled;
    private boolean mfa;
    private String secret;
    @ManyToOne(cascade = CascadeType.ALL)
    private role role;
    private String adress;
    private String number;
    private int hours;
    private Float salary;
}
