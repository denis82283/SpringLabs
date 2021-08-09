package en.app.springlabs.Domain;

import org.jetbrains.annotations.NotNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="usr")
public class User {
    @NotNull
    @NotEmpty(message = "Firstname cannot be empty")
    @Column(name="firstname")
    private String firstname;
    @NotNull
    @NotEmpty(message = "Lastname cannot be empty")
    @Column(name="lastname")
    private String lastname;
    @Id
    @NotNull
    @NotEmpty(message = "Email cannot be empty")
    @Column(name="email")
    private String email;
    @NotNull
    @NotEmpty(message = "Password cannot be empty")
    @Column(name="password")
    private String password;
    private String matchingPassword;

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
