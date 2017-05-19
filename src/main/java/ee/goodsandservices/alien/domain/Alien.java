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
    @Column(name = "dna_one")
    private Integer dnaOne;

    @Min(value = 1)
    @Column(name = "dna_two")
    private Integer dnaTwo;

    @Column(name = "avatar")
    private String avatar;

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

    public Integer getDnaOne() {
        return dnaOne;
    }

    public Alien dnaOne(Integer dnaOne) {
        this.dnaOne = dnaOne;
        return this;
    }

    public void setDnaOne(Integer dnaOne) {
        this.dnaOne = dnaOne;
    }

    public Integer getDnaTwo() {
        return dnaTwo;
    }

    public Alien dnaTwo(Integer dnaTwo) {
        this.dnaTwo = dnaTwo;
        return this;
    }

    public void setDnaTwo(Integer dnaTwo) {
        this.dnaTwo = dnaTwo;
    }

    public String getAvatar() {
        return avatar;
    }

    public Alien avatar(String avatar) {
        this.avatar = avatar;
        return this;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
            ", dnaOne='" + getDnaOne() + "'" +
            ", dnaTwo='" + getDnaTwo() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
