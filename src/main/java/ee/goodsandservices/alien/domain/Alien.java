package ee.goodsandservices.alien.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Alien.
 */
@Entity
@Table(name = "alien")
public class Alien implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Min(value = 1)
    @Column(name = "dna")
    private Integer dna;

    @Column(name = "avatar_url")
    private String avatarUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Alien name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDna() {
        return dna;
    }

    public Alien dna(Integer dna) {
        this.dna = dna;
        return this;
    }

    public void setDna(Integer dna) {
        this.dna = dna;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public Alien avatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
        return this;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Alien alien = (Alien) o;
        if (alien.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), alien.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Alien{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dna='" + getDna() + "'" +
            ", avatarUrl='" + getAvatarUrl() + "'" +
            "}";
    }
}
