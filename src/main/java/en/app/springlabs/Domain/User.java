package en.app.springlabs.Domain;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="usr")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String username;
    private String password;
    private boolean active;

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name="user_id"))
    @Enumerated()
    private Set<Role> roles;
}
