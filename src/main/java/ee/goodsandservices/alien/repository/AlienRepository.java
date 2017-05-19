package ee.goodsandservices.alien.repository;

import ee.goodsandservices.alien.domain.Alien;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Alien entity.
 */
@SuppressWarnings("unused")
public interface AlienRepository extends JpaRepository<Alien,Long> {

}
