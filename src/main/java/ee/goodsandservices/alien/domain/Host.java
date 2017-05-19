package ee.goodsandservices.alien.domain;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Host.
 */
@Entity
@Table(name = "host")
public class Host implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @Min(value = 10)
    @Column(name = "dna")
    private Integer dna;

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

    public Host name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDna() {
        return dna;
    }

    public Host dna(Integer dna) {
        this.dna = dna;
        return this;
    }

    public void setDna(Integer dna) {
        this.dna = dna;
    }

    public String getAvatar() {
        return avatar;
    }

    public Host avatar(String avatar) {
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
        Host host = (Host) o;
        if (host.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), host.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Host{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", dna='" + getDna() + "'" +
            ", avatar='" + getAvatar() + "'" +
            "}";
    }
}
