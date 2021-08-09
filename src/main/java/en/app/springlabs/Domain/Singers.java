package en.app.springlabs.Domain;

import javax.persistence.*;

@Entity
@Table(name = "singers")
public class Singers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Long id;

    @Column(name="name", nullable=false)
    private String name;
    @Column(name="surname", nullable=false)
    private String surname;
    @Column(name="musical_group")
    private String musical_group;
    @Column(name="pseudonym")
    private String pseudonym;

    public Singers() {
    }

    public Singers(String name, String surname, String musical_group, String pseudonym) {
        this.name = name;
        this.surname = surname;
        this.musical_group = musical_group;
        this.pseudonym = pseudonym;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getMusical_group() {
        return musical_group;
    }

    public void setMusical_group(String musical_group) {
        this.musical_group = musical_group;
    }

    public String getPseudonym() {
        return pseudonym;
    }

    public void setPseudonym(String pseudonym) {
        this.pseudonym = pseudonym;
    }
}
